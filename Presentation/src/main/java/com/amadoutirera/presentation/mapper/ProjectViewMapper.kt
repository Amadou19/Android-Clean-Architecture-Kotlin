package com.amadoutirera.presentation.mapper

import com.amadoutirera.domain.model.Project
import com.amadoutirera.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : Mapper<ProjectView, Project> {


    override fun mapToView(type: Project): ProjectView {

        return ProjectView(
            id = type.id, name = type.name, fullName = type.fullName, startCount = type.startCount,
            dateCreated = type.dateCreated, ownerName = type.ownerName,
            ownerAvatar = type.ownerAvatar, isBookmarked = type.isBookmarked
        )
    }


}