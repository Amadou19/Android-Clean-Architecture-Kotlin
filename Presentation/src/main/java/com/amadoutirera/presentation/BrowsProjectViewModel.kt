package com.amadoutirera.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.domain.interactor.bookmark.BookmarkProject
import com.amadoutirera.domain.interactor.bookmark.UnBookMarkProject
import com.amadoutirera.domain.interactor.browse.GetProjects
import com.amadoutirera.domain.model.Project
import com.amadoutirera.presentation.mapper.ProjectViewMapper
import com.amadoutirera.presentation.model.ProjectView
import com.amadoutirera.presentation.state.Resource
import com.amadoutirera.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class BrowsProjectViewModel @Inject constructor(private val getProjects: GetProjects,
                                                private val projectViewMapper: ProjectViewMapper,
                                                private val bookMarkProject: BookmarkProject,
                                                private val unBookMarkProject: UnBookMarkProject): ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()






    /**- - - -Get Project State - - -**/
    fun getProjectState(): LiveData<Resource<List<ProjectView>>> = liveData



    /**- - - - Fetch Project - - - -**/
    fun fetchProject(){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectSubscriber())
    }

    inner class ProjectSubscriber: DisposableObserver<List<Project>>() {

        override fun onNext(listprojects: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, listprojects.map { projectViewMapper.mapToView(it) },null))
        }
        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }


    /**- - - - Bookmark Project - - - -**/
    fun bookmarkProject(projectId: String){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookMarkProject.execute(BookmarkProjectSubscriber(), BookmarkProject.Params.forProject(projectId))
    }

    inner class BookmarkProjectSubscriber : DisposableCompletableObserver(){

        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }


    /**- - - - UnBookmark Project - - - -**/
    fun unBookmarkProject(projectId: String){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unBookMarkProject.execute(UnBookmarkProjectSubscriber(), UnBookMarkProject.Params.forProject(projectId))
    }

    inner class UnBookmarkProjectSubscriber : DisposableCompletableObserver(){

        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }






    /**- - - - - - - - - - - - **/
    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

}

