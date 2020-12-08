package com.example.android.lasttorture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.android.lasttorture.data.db.AnimalDbModel
import com.example.android.lasttorture.view.DetailsFragmentArgs

class DetailsViewModel() : ViewModel() {

    private val _image: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String>
        get() = _image

    private val _breed: MutableLiveData<String> = MutableLiveData()
    val breed: LiveData<String>
    get() = _breed

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String>
        get() = _name

    private val _age: MutableLiveData<String> = MutableLiveData()
    val age: LiveData<String>
        get() = _age

    private val _desc: MutableLiveData<String> = MutableLiveData()
    val desc: LiveData<String>
        get() = _desc

    fun getImage(args: DetailsFragmentArgs) {
        _image.value = args.animal.photoFullUrl
    }

    fun getBreed(args: DetailsFragmentArgs) {
        _breed.value = args.animal.breed
    }

    fun getName(args: DetailsFragmentArgs) {
        _name.value = args.animal.name
    }

    fun getAge(args: DetailsFragmentArgs) {
        _age.value = args.animal.age
    }

    fun getDesc(args: DetailsFragmentArgs) {
        _desc.value = args.animal.description
    }

}