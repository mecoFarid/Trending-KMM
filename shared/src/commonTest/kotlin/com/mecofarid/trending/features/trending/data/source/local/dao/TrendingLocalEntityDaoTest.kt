package com.mecofarid.trending.features.trending.data.source.local.dao

import com.mecofarid.trending.libs.db.sqldelight.TrendingDatabase
import com.mecofarid.trending.mocks.anyList
import com.mecofarid.trending.mocks.feature.repo.anyTrendingLocalEntity
import com.mecofarid.trending.mocks.randomInt
import com.mecofarid.trending.utils.db.sqldelight.testSqlDelightDriver
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals


// Fixme. Could not test in Android, iOS test are not working, only tests on JVM are working
open class TrendingLocalEntityDaoTest {

    private val sqlDriver by lazy { testSqlDelightDriver() }
    val db = TrendingDatabase(sqlDriver)
    private val dao by  lazy {
         TrendingLocalEntityDao(db)
    }

    @AfterTest
    fun tearDown() {
        sqlDriver.close()
    }

    @Test
    fun assert_all_removed_and_new_data_inserted() {
        val expectedData = anyList { anyTrendingLocalEntity() }.sorted()

        repeat(randomInt(1, 10)){
            dao.deleteAllTrendingAndInsert(anyList { anyTrendingLocalEntity() })
        }

        dao.deleteAllTrendingAndInsert(expectedData)
        val actualData = dao.getAllTrending().sorted()

        assertEquals(expectedData, actualData)
    }
}