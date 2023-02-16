package com.example.mvvm.core

import android.widget.ImageView

fun ImageView.loadFromResource(resourceName: String){
    val resId = resources.getIdentifier(resourceName,"drawable", context.packageName)
    setImageResource(resId)

}