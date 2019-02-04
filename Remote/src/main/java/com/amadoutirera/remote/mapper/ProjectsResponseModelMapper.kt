package com.amadoutirera.remote.mapper

import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.remote.model.ProjectModel

class ProjectsResponseModelMapper: modelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {

        return ProjectEntity(id = model.id,
            name = model.name,
            fullName = model.fullName,
            startCount = model.startCount,
            dateCreated = model.dateCreated,
            ownerName = model.owner.ownerName,
            ownerAvatar = model.owner.ownerAvatar, isBookmarked = false)
    }


}