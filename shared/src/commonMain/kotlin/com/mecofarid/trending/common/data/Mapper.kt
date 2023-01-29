package com.mecofarid.trending.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}

class VoidMapper<I, O> : Mapper<I, O> {
    override fun map(input: I): O {
        throw UnsupportedOperationException()
    }
}

class ListMapper<I, O>(
    private val mapper: Mapper<I, O>
): Mapper<List<I>, List<O>> {
    override fun map(input: List<I>): List<O> = input.map { mapper.map(it) }
}
