package com.amadoutirera.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amadoutirera.cache.db.ProjectConstants
import com.amadoutirera.domain.model.Project


@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject (@PrimaryKey val id: String,
                          @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID)
                          val name: String,
                          val fullName: String,
                          val startCount: String,
                          val dateCreated: String,
                          val ownerName: String,
                          val ownerAvatar: String,
                          @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED)
                          var isBookmarked: Boolean)