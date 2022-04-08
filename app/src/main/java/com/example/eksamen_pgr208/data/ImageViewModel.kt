package com.example.eksamen_pgr208.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Alle filer under data-folderen er skrevet ved hjelp av denne videoen:
// https://www.youtube.com/watch?v=lwAvI3WDXBY
// anbefaler å se den

class ImageViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Image>>
    private val repo: ImageRepo

    init {
        val imageDao = ImageDatabase.getDataBase(application).imageDao()
        repo = ImageRepo(imageDao)
        readAllData = repo.readAllData
    }

    fun addImage(image: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addImage(image)
        }
    }


}