package com.amadoutirera.remote.mapper

interface modelMapper <M, out E> {

    fun mapFromModel(model: M): E

}