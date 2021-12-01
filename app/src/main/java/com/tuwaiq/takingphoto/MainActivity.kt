package com.tuwaiq.takingphoto

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.tuwaiq.takingphoto.utils.getScaledBitmap
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var openCamBtn:Button
    private lateinit var imageView: ImageView

    private lateinit var photoFile:File
    private lateinit var photoUri:Uri

    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }



    private val getPermissionLuncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){

    }

    private val takePhotoLunchr = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ){
        if (it){
            updateImageview()
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = Model()

        photoFile = viewModel.getPhotoFile(model)

        photoUri = FileProvider.getUriForFile(
            this,
            "com.tuwaiq.takingphoto",
            photoFile
        )


        openCamBtn = findViewById(R.id.openCamBtn)
        imageView = findViewById(R.id.imageView)

        openCamBtn.setOnClickListener {
            if (PackageManager.PERMISSION_GRANTED == baseContext?.let {
                    ContextCompat.checkSelfPermission(it,Manifest.permission.CAMERA)
                }){
                takePhotoLunchr.launch(photoUri)

            }else{
                getPermissionLuncher.launch(Manifest.permission.CAMERA)
            }
        }




    }

    fun updateImageview(){
        if (photoFile.exists()){
            val bitmap = getScaledBitmap(photoFile.path,this)
            imageView.setImageBitmap(bitmap)
        }else {
            imageView.setImageBitmap(null)
        }

    }





}