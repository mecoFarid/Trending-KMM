package com.mecofarid.trending.mocks

import kotlin.random.Random


const val ANY_ITEMS_COUNT = 10

fun randomString(length: Int = ANY_ITEMS_COUNT): String {
    val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + ' '
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun randomLong(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE) = Random.nextLong(min, max)

fun randomInt(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int = Random.nextInt(min, max)

fun <T> anyList(
    length: Int = randomInt(min = 1, max = ANY_ITEMS_COUNT),
    itemFactory: () -> T
): List<T> = buildList {
    repeat(length) {
        add(itemFactory())
    }
}