package com.amadoutirera.domain.interactor.browse

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.interactor.ObservableUseCase
import com.amadoutirera.domain.model.Project
import com.amadoutirera.domain.repository.ProjectsRepository
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

open class GetProjects @Inject constructor(private val projectsRepository: ProjectsRepository,
                                        private val postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){


    /*--------       -------------*/
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }




}