package com.amadoutirera.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapTOEntity(domain: D): E

}