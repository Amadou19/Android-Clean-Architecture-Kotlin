package com.amadoutirera.domain.interactor.bookmark

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.interactor.browse.GetProjects
import com.amadoutirera.domain.model.Project
import com.amadoutirera.domain.repository.ProjectsRepository
import com.amadoutirera.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class GetBookmarkedProjectsTest {

    lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }




    /*----------       -----------*/
    @Test
    fun getBookmarkdProjectCompletes(){

        stubGetProject(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    /*----------       -----------*/
    @Test
    fun getBookmarkProjectReturn(){

        val bookMarkProject= ProjectDataFactory.makeProjectList(2)
        stubGetProject(Observable.just(bookMarkProject))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertValue(bookMarkProject)
    }



    /*----------       -----------*/
    fun stubGetProject(observable: Observable<List<Project>>){
        whenever(projectsRepository.getBookmarkProjects())
            .thenReturn(observable)
    }



}