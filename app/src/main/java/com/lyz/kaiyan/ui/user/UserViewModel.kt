package com.lyz.kaiyan.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var text = MutableLiveData<String>().apply {
        value = "This is User Fragment!"
    }

    fun setText(text: String){
        this.text.value = text
    }

//    val selected = MutableLiveData<String>()
//
//    fun select(item: String) {
//        selected.value = item
//    }
}