package com.amadoutirera.data.repository

import com.amadoutirera.data.model.ProjectEntity
import io.reactivex.Observable


interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>

}