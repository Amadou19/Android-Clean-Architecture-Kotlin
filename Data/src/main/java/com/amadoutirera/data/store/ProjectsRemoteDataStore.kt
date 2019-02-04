package com.amadoutirera.data.store

import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.data.repository.ProjectsDataStore
import com.amadoutirera.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(private val projectsRemote: ProjectsRemote): ProjectsDataStore {



    /*----------       -----------*/
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }



    /*----------       -----------*/
    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("This operation inst't supported her...")
    }




    /*----------       -----------*/
    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("This operation inst't supported her...")
    }




    /*----------       -----------*/
    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("This operation inst't supported her...")
    }


    /*----------       -----------*/
    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This operation inst't supported her...")
    }



    /*----------       -----------*/
    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This operation inst't supported her...")
    }



}