package com.amadoutirera.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.domain.interactor.bookmark.GetBookmarkedProjects
import com.amadoutirera.domain.model.Project
import com.amadoutirera.presentation.mapper.ProjectViewMapper
import com.amadoutirera.presentation.model.ProjectView
import com.amadoutirera.presentation.state.Resource
import com.amadoutirera.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowsBookMarkProjectViewModel @Inject constructor(private val projectViewMapper: ProjectViewMapper,
                                                        private val getBookmarkedProjects: GetBookmarkedProjects): ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()





    /**- - - -Get Bookmark Project State - - -**/
    fun getBookmarkProjectState(): LiveData<Resource<List<ProjectView>>> =liveData



    /**- - - -Fetch Bookmark Project - - -**/
    fun fetchBookmarkProject(){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))

        getBookmarkedProjects.execute(GetBookmarkedProjectsObserver())
    }


    inner class GetBookmarkedProjectsObserver : DisposableObserver<List<Project>>() {

        override fun onNext(listProjects: List<Project>) {

            liveData.postValue(Resource(ResourceState.LOADING, listProjects.map { projectViewMapper.mapToView(it) }, null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }




    /**- - - - - - - - - - - - **/
    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()

    }



}