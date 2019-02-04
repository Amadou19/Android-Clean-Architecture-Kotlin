package com.amadoutirera.domain.interactor.bookmark

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.model.Project
import com.amadoutirera.domain.repository.ProjectsRepository
import com.amadoutirera.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkProjectTest{
    lateinit var unBookMarkProject: UnBookMarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        unBookMarkProject = UnBookMarkProject(projectsRepository, postExecutionThread)
    }




    /*----------       -----------*/
    @Test
    fun unBookmarkdProjectCompletes(){

        stubunBookmarkProject(Completable.complete())
        val testObserver = unBookMarkProject.buildUseCasCompletable(UnBookMarkProject.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }


    /*----------       -----------*/
    @Test(expected = IllegalArgumentException::class)
    fun unBookmarkProjectThrowsExeption(){
        unBookMarkProject.buildUseCasCompletable().test()
    }




    /*----------       -----------*/
    fun stubunBookmarkProject(completable: Completable){
        whenever(projectsRepository.unBokmarkProject(any()))
            .thenReturn(completable)
    }

}

