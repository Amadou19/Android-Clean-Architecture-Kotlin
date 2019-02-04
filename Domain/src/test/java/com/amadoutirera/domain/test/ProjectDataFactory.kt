package com.amadoutirera.domain.test

import com.amadoutirera.domain.model.Project
import java.util.*

object ProjectDataFactory {


    fun randomUuid(): String{
        return UUID.randomUUID().toString()
    }


    fun radomBoolean(): Boolean{
        return Math.random() < 0.5
    }


    /*---------- return Project      -------------*/
    fun makeProject(): Project{

        return Project(randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(), randomUuid(),
            radomBoolean())
    }


    /*---------- return Project List      -----------*/
    fun makeProjectList(count: Int):List<Project>{

        val project = mutableListOf<Project>()
        repeat(count){
            project.add(makeProject())
        }
        return project
    }

}