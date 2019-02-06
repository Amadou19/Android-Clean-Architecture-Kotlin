package com.amadoutirera.domain.interactor.bookmark

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.interactor.CompletableUseCase
import com.amadoutirera.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

open class BookmarkProject @Inject constructor(private val projectsRepository: ProjectsRepository,
                                               private val postExecutionThread: PostExecutionThread): CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {





    public override fun buildUseCasCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectsRepository.getBookmarkProject(params.projectId)
    }





    data class Params constructor(val projectId: String){
        companion object {
            fun forProject(projectId: String): Params{
                return Params(projectId)
            }
        }
    }





}