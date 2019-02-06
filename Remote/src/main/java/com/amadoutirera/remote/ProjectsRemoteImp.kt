package com.amadoutirera.remote

import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.data.repository.ProjectsRemote
import com.amadoutirera.remote.mapper.ProjectsResponseModelMapper
import com.amadoutirera.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImp @Inject constructor(private val service: GithubTrendingService,
                                            private val mapper: ProjectsResponseModelMapper): ProjectsRemote {




    override fun getProjects(): Observable<List<ProjectEntity>> {
        return  service.searchRepository("language:kotlin", "star", "desc")
            .map {
                it.items.map {
                   mapper.mapFromModel(it)
               }
            }
    }


}