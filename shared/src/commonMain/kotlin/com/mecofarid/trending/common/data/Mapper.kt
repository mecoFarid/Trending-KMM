package com.mecofarid.trending.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
