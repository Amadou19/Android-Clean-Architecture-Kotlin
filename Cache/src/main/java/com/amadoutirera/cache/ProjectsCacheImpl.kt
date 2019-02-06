package com.amadoutirera.cache

import com.amadoutirera.cache.db.ProjectDatabase
import com.amadoutirera.cache.mapper.CacheProjectMapper
import com.amadoutirera.cache.model.Config
import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(private val projectDatabase: ProjectDatabase,
                                            private val cacheProjectMapper: CacheProjectMapper):ProjectsCache {





    override fun cleanProjects(): Completable {
        return Completable.defer {
            projectDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }




    override fun saveProject(projects: List<ProjectEntity>): Completable {

        return Completable.defer{
            projectDatabase.cachedProjectsDao().insertProjects(
                projects.map {
                    cacheProjectMapper.mapToCached(it)
                }
            )
            Completable.complete()
        }
    }




    override fun getProjects(): Observable<List<ProjectEntity>> {

        return projectDatabase.cachedProjectsDao().getProjects()
            .toObservable()
            .map {
                it.map{cacheProjectMapper.mapFromCached(it)
                }
        }
    }




    override fun getBookmarkProjects(): Observable<List<ProjectEntity>> {

        return projectDatabase.cachedProjectsDao().getBookmarkedProjects()
            .toObservable()
            .map {
                it.map{cacheProjectMapper.mapFromCached(it)
                }
            }
    }



    override fun setProjectAsBookmarked(projectId: String): Completable {

        return Completable.defer {
            projectDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }




    override fun setProjectAsNoBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }




    override fun arProjectCached(): Single<Boolean> {
        return projectDatabase.cachedProjectsDao().getProjects().isEmpty
            .map { !it }
    }





    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer{
            projectDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }





    override fun iseProjectsCacheExpired(): Single<Boolean> {
        val currenTime = System.currentTimeMillis()
        val expirationTime = (60*10*100).toLong()
        return projectDatabase.configDao().getConfig()
            .single((Config(lastCacheTime = 0))).
                map {
                    currenTime - it.lastCacheTime > expirationTime
                }
    }



}