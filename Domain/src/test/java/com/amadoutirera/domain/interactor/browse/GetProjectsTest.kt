package com.amadoutirera.domain.interactor.browse


import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.model.Project
import com.amadoutirera.domain.repository.ProjectsRepository
import com.amadoutirera.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetProjectsTest(){

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }





    /*----------       -----------*/
    @Test
    fun getProjectscompletes(){

        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }



 /*----------       -----------*/
    @Test
    fun getProjectsReturnData(){
     val projects = ProjectDataFactory.makeProjectList(2)
     stubGetProjects(Observable.just(projects))
     val testObserver = getProjects.buildUseCaseObservable().test()
     testObserver.assertValue(projects)
    }


    /*----------       -----------*/
    fun stubGetProjects(observable: Observable<List<Project>>){

        whenever(projectsRepository.getProjects())
            .thenReturn(observable)
    }





}