package com.amadoutirera.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Config(@PrimaryKey(autoGenerate = true) var id: Int = -1, val lastCacheTime: Long)