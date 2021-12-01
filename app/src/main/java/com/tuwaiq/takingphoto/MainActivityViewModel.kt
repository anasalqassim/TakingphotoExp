package com.tuwaiq.takingphoto

import androidx.lifecycle.ViewModel
import java.io.File

class MainActivityViewModel : ViewModel() {

    private val repo = Repo.getInstance()

    fun getPhotoFile(model: Model):File = repo.getPhotoFile(model)


}