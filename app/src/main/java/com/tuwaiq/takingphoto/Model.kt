package com.tuwaiq.takingphoto

data class Model(var id:Int = 0) {

    val photoFileName:String
        get() = "IMG$id.jpg"



}