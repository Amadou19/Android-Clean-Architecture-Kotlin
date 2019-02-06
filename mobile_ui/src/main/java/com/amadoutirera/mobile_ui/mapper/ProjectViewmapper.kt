package com.amadoutirera.mobile_ui.mapper

import com.amadoutirera.mobile_ui.model.Project
import com.amadoutirera.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewmapper @Inject constructor() : ViewMapper<ProjectView, Project > {


    override fun mapToView(presentation: ProjectView): Project {

        return Project(
            id = presentation.id, name = presentation.name, fullName = presentation.fullName, startCount = presentation.startCount,
            dateCreated = presentation.dateCreated, ownerName = presentation.ownerName,
            ownerAvatar = presentation.ownerAvatar, isBookmarked = presentation.isBookmarked
        )
    }
}