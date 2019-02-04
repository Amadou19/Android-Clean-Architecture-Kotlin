package com.amadoutirera.domain.repository

import com.amadoutirera.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable


interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>
    fun getBookmarkProject(projectId: String): Completable
    fun unBokmarkProject(projectId: String): Completable
    fun getBookmarkProjects(): Observable<List<Project>>

}