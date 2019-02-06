package com.amadoutirera.data.store

import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.data.repository.ProjectsCache
import com.amadoutirera.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheDataStore @Inject constructor( private val projectsCache: ProjectsCache): ProjectsDataStore{




    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }




    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProject(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }




    override fun clearProjects(): Completable {
        return projectsCache.cleanProjects()
    }



    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkProjects()
    }



    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }



    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNoBookmarked(projectId)
    }


}