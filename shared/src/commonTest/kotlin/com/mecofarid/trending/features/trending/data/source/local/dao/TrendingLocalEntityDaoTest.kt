package com.mecofarid.trending.features.trending.data.source.local.dao

import com.mecofarid.trending.libs.db.sqldelight.TrendingDatabase
import com.mecofarid.trending.mocks.anyList
import com.mecofarid.trending.mocks.feature.repo.anyTrendingLocalEntity
import com.mecofarid.trending.mocks.randomInt
import com.mecofarid.trending.utils.db.sqldelight.testSqlDelightDriver
import kotlin.test.Test
import kotlin.test.assertEquals


// Fixme. Neither Android nor iOS test are working
open class TrendingLocalEntityDaoTest {

    private val dao by lazy {
        TrendingLocalEntityDao(TrendingDatabase(testSqlDelightDriver()).trendingEntityQueries)
    }

    @Test
    fun assert_all_removed_and_new_data_inserted() {
        val expectedData = anyList { anyTrendingLocalEntity() }

        repeat(randomInt(1, 10)){
            dao.deleteAllTrendingAndInsert(anyList { anyTrendingLocalEntity() })
        }
        dao.deleteAllTrendingAndInsert(expectedData)
        val actualData = dao.getAllTrending()

        assertEquals(expectedData, actualData)
    }
}