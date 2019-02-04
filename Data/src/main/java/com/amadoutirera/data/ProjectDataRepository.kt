package com.amadoutirera.data

import com.amadoutirera.data.mapper.ProjectMapper
import com.amadoutirera.data.repository.ProjectsCache
import com.amadoutirera.data.store.ProjectDataStoreFactory
import com.amadoutirera.domain.model.Project
import com.amadoutirera.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject



class ProjectDataRepository @Inject constructor(private val mapper: ProjectMapper, private val cache: ProjectsCache,
                                                private val factory: ProjectDataStoreFactory): ProjectsRepository{


    override fun getProjects(): Observable<List<Project>> {

        return Observable.zip(cache.arProjectCached().toObservable(), cache.iseProjectsCacheExpired().toObservable(),

            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCache , isExpired -> Pair(areCache, isExpired) })
            .flatMap {
                factory.getDatastore(it.first, it.second).getProjects()
            }
            .flatMap { projects ->
                factory.getCacheDataStore()
                .saveProjects(projects)
                .andThen(Observable.just(projects))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }


    override fun getBookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unBokmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().map {
            it.map {
                mapper.mapFromEntity(it)
            }
        }
    }

}