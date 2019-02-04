package com.amadoutirera.data.store

import com.amadoutirera.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectDataStoreFactory @Inject constructor(private val projectsCacheDataStore: ProjectsCacheDataStore, private val projectsRemoteDataStore: ProjectsRemoteDataStore){


    open fun getDatastore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataStore{

        return if (projectsCached && !cacheExpired){
            projectsCacheDataStore
        }
        else{
            projectsRemoteDataStore
        }
    }




    fun getCacheDataStore(): ProjectsDataStore{
        return projectsCacheDataStore
    }



}