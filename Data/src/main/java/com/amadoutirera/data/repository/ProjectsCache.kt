package com.amadoutirera.data.repository

import com.amadoutirera.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {

    fun cleanProjects(): Completable

    fun saveProject(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNoBookmarked(projectId: String): Completable

    fun arProjectCached():Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun iseProjectsCacheExpired(): Single<Boolean>

}