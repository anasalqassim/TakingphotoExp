package com.tuwaiq.takingphoto

import android.content.Context
import java.io.File

class Repo private constructor(context: Context) {

    private val fileDir = context.applicationContext.filesDir

    fun getPhotoFile(model: Model):File = File(fileDir , model.photoFileName)


    companion object{
       private var INSTANCE:Repo? = null

        fun initialize(context: Context){
        if (INSTANCE == null){
            INSTANCE = Repo(context)
         }
        }

        fun getInstance():Repo = INSTANCE ?: throw IllegalStateException("you must initialize your repo")


    }

}