package com.amadoutirera.remote.service

import com.amadoutirera.remote.model.ProjectModel
import com.amadoutirera.remote.model.ProjectsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepository(@Query("q")query: String,
                         @Query("sort") sortBy: String,
                         @Query("order") orderBy: String): Observable<ProjectsResponseModel>

}