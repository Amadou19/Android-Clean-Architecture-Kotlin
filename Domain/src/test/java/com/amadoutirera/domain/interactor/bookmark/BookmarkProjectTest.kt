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

class BookmarkProjectTest{
    lateinit var bookmarkedProject: BookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkedProject = BookmarkProject(projectsRepository, postExecutionThread)
    }




    /*----------       -----------*/
    @Test
    fun bookmarkdProjectCompletes(){

        stubGetProject(Completable.complete())
        val testObserver = bookmarkedProject.buildUseCasCompletable(BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }


    /*----------       -----------*/
    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsExeption(){
        bookmarkedProject.buildUseCasCompletable().test()
    }




    /*----------       -----------*/
    fun stubGetProject(completable: Completable){
        whenever(projectsRepository.getBookmarkProject(any()))
            .thenReturn(completable)
    }

}

