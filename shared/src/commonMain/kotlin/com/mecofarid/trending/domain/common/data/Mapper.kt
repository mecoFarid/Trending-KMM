package com.mecofarid.trending.domain.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
