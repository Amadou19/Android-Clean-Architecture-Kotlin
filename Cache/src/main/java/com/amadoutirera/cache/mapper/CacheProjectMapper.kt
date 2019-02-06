package com.amadoutirera.cache.mapper

import com.amadoutirera.cache.model.CachedProject
import com.amadoutirera.data.model.ProjectEntity

class CacheProjectMapper : CacheMapper<CachedProject, ProjectEntity> {


    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(type.id, type.name, type.fullName, type.startCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked)
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(type.id, type.name, type.fullName, type.startCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked)
    }

}