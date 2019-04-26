package com.idiotleon.tutorialdagger2withkotlin.essentials

import android.util.Log
import javax.inject.Inject

class DAO @Inject constructor(){
    fun store(data: String?)  = Log.d("DAO", "Storing: ${data}")
}