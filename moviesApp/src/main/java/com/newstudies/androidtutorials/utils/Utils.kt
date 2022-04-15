package com.newstudies.androidtutorials.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.newstudies.androidtutorials.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable{

        return CircularProgressDrawable(context).apply {
            strokeWidth = 8F
            centerRadius = 40F
            start()
        }
    }

    fun getMetaDataInfo(applicationContext: Context) : ApplicationInfo{
        return applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
    }

    fun getUrl(applicationContext: Context):String {
        return getMetaDataInfo(applicationContext).metaData["imageUrlValue"].toString()
    }

    fun getKey(applicationContext: Context) : String{
       return getMetaDataInfo(applicationContext).metaData["keyValue"].toString()
    }

    fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
        val options = RequestOptions().placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }

    @BindingAdapter("android:imageUrl")
    fun loadImage(view:ImageView, url:String?){
        val  uri = getUrl(view.context) + url
        view.loadImage(uri, getProgressDrawable(view.context))
    }



