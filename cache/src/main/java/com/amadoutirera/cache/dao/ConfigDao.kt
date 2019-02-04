package com.amadoutirera.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amadoutirera.cache.db.ConfigConstants
import com.amadoutirera.cache.model.Config
import io.reactivex.Flowable


@Dao
abstract class ConfigDao{

    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}