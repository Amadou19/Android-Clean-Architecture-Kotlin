package com.amadoutirera.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amadoutirera.cache.dao.CachedProjectsDao
import com.amadoutirera.cache.dao.ConfigDao
import com.amadoutirera.cache.model.CachedProject
import com.amadoutirera.cache.model.Config
import javax.inject.Inject





@Database(entities = arrayOf(CachedProject::class, Config::class), version = 1)
abstract class ProjectDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao


    companion object {

        private var INSTANCE: ProjectDatabase? = null
        private var lock = Any()

        fun getInstances(context: Context): ProjectDatabase{
            if (INSTANCE == null){
                synchronized(lock){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ProjectDatabase::class.java,
                            "projects.db"
                        ).build()
                    }
                    return INSTANCE as ProjectDatabase
                }
            }
            return INSTANCE as ProjectDatabase
        }
    }


}