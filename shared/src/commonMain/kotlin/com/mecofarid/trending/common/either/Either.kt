package com.mecofarid.trending.common.either

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Taken from
 * https://github.com/arrow-kt/arrow/blob/main/arrow-libs/core/arrow-core/src/commonMain/kotlin/arrow/core/predef.kt
 */

sealed class Either<out LEFT, out RIGHT> {

    @OptIn(ExperimentalContracts::class)
    fun isLeft(): Boolean {
        contract { returns(true) implies (this@Either is Left<LEFT>) }
        return this@Either is Left<LEFT>
    }

    @OptIn(ExperimentalContracts::class)
    fun isRight(): Boolean {
        contract { returns(true) implies (this@Either is Right<RIGHT>) }
        return this@Either is Right<RIGHT>
    }

    /**
     * Transform an [Either] into a value of [C].
     * Alternative to using `when` to fold an [Either] into a value [C].
     *
     * ```
     * fun test() {
     *   Either.Right(1)
     *     .fold({ fail("Cannot be left") }, { it + 1 }) shouldBe 2
     *
     *   Either.Left(RuntimeException("Boom!"))
     *     .fold({ -1 }, { fail("Cannot be right") }) shouldBe -1
     * }
     * ```
     *
     * @param ifLeft transform the [Either.Left] type [LEFT] to [C].
     * @param ifRight transform the [Either.Right] type [RIGHT] to [C].
     * @return the transformed value [C] by applying [ifLeft] or [ifRight] to [LEFT] or [RIGHT] respectively.
     */
    @OptIn(ExperimentalContracts::class)
    inline fun <C> fold(ifLeft: (left: LEFT) -> C, ifRight: (right: RIGHT) -> C): C {
        contract {
            callsInPlace(ifLeft, InvocationKind.AT_MOST_ONCE)
            callsInPlace(ifRight, InvocationKind.AT_MOST_ONCE)
        }
        return when (this) {
            is Right -> ifRight(value)
            is Left -> ifLeft(value)
        }
    }

    /**
     * Swap the generic parameters [LEFT] and [RIGHT] of this [Either].
     *
     * ```
     * fun test() {
     *   Either.Left("left").swap() shouldBe Either.Right("left")
     *   Either.Right("right").swap() shouldBe Either.Left("right")
     * }
     * ```
     */
    fun swap(): Either<RIGHT, LEFT> =
        fold({ Right(it) }, { Left(it) })

    /**
     * Map, or transform, the right value [RIGHT] of this [Either] to a new value [C].
     *
     * ```
     * fun test() {
     *   Either.Right(12).map { _: Int ->"flower" } shouldBe Either.Right("flower")
     *   Either.Left(12).map { _: Nothing -> "flower" } shouldBe Either.Left(12)
     * }
     * ```
     */
    inline fun <C> map(f: (right: RIGHT) -> C): Either<LEFT, C> =
        flatMap { Right(f(it)) }

    /**
     * Map, or transform, the left value [LEFT] of this [Either] to a new value [C].
     *
     * ```
     * fun test() {
     *  Either.Right(12).mapLeft { _: Nothing -> "flower" } shouldBe Either.Right(12)
     *  Either.Left(12).mapLeft { _: Int -> "flower" }  shouldBe Either.Left("flower")
     * }
     * ```
     */
    inline fun <C> mapLeft(f: (LEFT) -> C): Either<C, RIGHT> =
        fold({ Left(f(it)) }, { Right(it) })

    /**
     * Performs the given [action] on the encapsulated [RIGHT] value if this instance represents [Either.Right].
     * Returns the original [Either] unchanged.
     *
     * ```st.matchers.shouldBe
     *
     * fun test() {
     *   Either.Right(1).onRight(::println) shouldBe Either.Right(1)
     * }
     * ```
     */
    inline fun onRight(action: (right: RIGHT) -> Unit): Either<LEFT, RIGHT> =
        also { if (it.isRight()) action(it.value) }

    /**
     * Performs the given [action] on the encapsulated [LEFT] if this instance represents [Either.Left].
     * Returns the original [Either] unchanged.
     *
     * ```
     * fun test() {
     *   Either.Left(2).onLeft(::println) shouldBe Either.Left(2)
     * }
     * ```
     */
    inline fun onLeft(action: (left: LEFT) -> Unit): Either<LEFT, RIGHT> =
        also { if (it.isLeft()) action(it.value) }

    /**
     * Returns the encapsulated value [RIGHT] if this instance represents [Either.Right]
     * or `null` if it is [Either.Left].
     *
     * ```
     * fun test() {
     *   Either.Right(12).getOrNull() shouldBe 12
     *   Either.Left(12).getOrNull() shouldBe null
     * }
     * ```
     */
    fun getOrNull(): RIGHT? = getOrElse { null }

    /**
     * The left side of the disjoint union, as opposed to the [Right] side.
     */
    data class Left<out A> constructor(val value: A) : Either<A, Nothing>() {
        override fun toString(): String = "Either.Left($value)"
    }

    /**
     * The right side of the disjoint union, as opposed to the [Left] side.
     */
    data class Right<out B> constructor(val value: B) : Either<Nothing, B>() {
        override fun toString(): String = "Either.Right($value)"
    }

    override fun toString(): String = fold(
        { "Either.Left($it)" },
        { "Either.Right($it)" }
    )
}

/**
 * Map, or transform, the right value [B] of this [Either] into a new [Either] with a right value of type [C].
 * Returns a new [Either] with either the original left value of type [A] or
 * the newly transformed right value of type [C].
 *
 * @param f The function to bind across [Right].
 */
inline fun <A, B, C> Either<A, B>.flatMap(f: (right: B) -> Either<A, C>): Either<A, C> =
    when (this) {
        is Either.Right -> f(this.value)
        is Either.Left -> this
    }

/**
 * Get the right value [B] of this [Either],
 * or compute a [default] value with the left value [A].
 *
 * ```
 * fun test() {
 *   Either.Left(12).getOrElse { it + 5 } shouldBe 17
 * }
 * ```
 */
inline fun <A, B> Either<A, B>.getOrElse(default: (A) -> B): B =
    fold(default) { it }

fun <A> A.left(): Either<A, Nothing> = Either.Left(this)

fun <A> A.right(): Either<Nothing, A> = Either.Right(this)

@Suppress("TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <reified Left: Throwable, Right> asEither(block: () -> Right): Either<Left, Right> =
    try {
        Either.Right(block())
    }catch (e: Throwable) {
        if (e is Left)
            Either.Left(e)
        else
            throw e
    }
