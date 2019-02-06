package com.amadoutirera.presentation.state

class Resource<out T> constructor(val status: ResourceState, val data : T?, message: String?) {
}