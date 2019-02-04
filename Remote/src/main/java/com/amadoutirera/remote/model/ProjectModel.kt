package com.amadoutirera.remote.model

import com.google.gson.annotations.SerializedName

class ProjectModel (val id: String,
                    val name: String,
                    @SerializedName("full_Nam")val fullName: String,
                    @SerializedName("stargazers_count")val startCount: String,
                    @SerializedName("created_at")val dateCreated: String,
                    val owner: OwnerModel
)