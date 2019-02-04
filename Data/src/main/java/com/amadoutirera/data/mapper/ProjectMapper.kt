package com.amadoutirera.data.mapper

import com.amadoutirera.data.model.ProjectEntity
import com.amadoutirera.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity,Project>{


    override fun mapFromEntity(entity: ProjectEntity): Project {

        return Project(id = entity.id, name = entity.name, fullName = entity.fullName, startCount = entity.startCount,
            dateCreated = entity.dateCreated, owanerName = entity.ownerName,owanerAvatar = entity.ownerAvatar, isBookmarked = entity.isBookmarked)
    }

    override fun mapTOEntity(domain: Project): ProjectEntity {
        return ProjectEntity(id = domain.id, name = domain.name, fullName = domain.fullName, startCount = domain.startCount,
            dateCreated = domain.dateCreated, ownerName = domain.owanerName,ownerAvatar = domain.owanerAvatar, isBookmarked = domain.isBookmarked)    }


}