package com.ivangladko.SearchPhotoApp

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.ui.images.DetailImageViewModel
import com.ivangladko.SearchPhotoApp.ui.fragment.FragmentImage
import com.ivangladko.SearchPhotoApp.ui.images.MainActivityViewModel
import com.ivangladko.SearchPhotoApp.ui.images.WebViewActivityViewModel



class PhotoAdapter(val photos: List<Result>): RecyclerView.Adapter<PhotoViewHolder>() {

    fun getImage(): ArrayList<String> {
        val imageList = ArrayList<String>()
        for(photo in photos){
            imageList.add(photo.thumbnail.toString())
        }
        return imageList
    }

    fun getPositionInt(): ArrayList<Int>{
        val positionArray = ArrayList<Int>()
        for(photo in photos){
            photo.position?.let { positionArray.add(it.toInt()) }
        }
        return positionArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        return holder.bind(photos[position])

    }
}


class PhotoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    private val photo:ImageView = itemView.findViewById(R.id.google_photo)
    private val title:TextView = itemView.findViewById(R.id.nameofSite)
    private val btn: Button = itemView.findViewById(R.id.button_webView)
    private val btnInfo: ImageButton = itemView.findViewById(R.id.btn_info)

    private var googlePhoto: String = ""
    private var googleUrl: String = ""
    private var googleTitle: String =""
    private var positionInt: Int = 0

    init {
        btn.setOnClickListener{
            val i = Intent(btn.context, WebViewActivityViewModel::class.java)
            i.putExtra("googleUrl", googleUrl.toString())
            btn.context.startActivity(i)
        }
    }

    init{
        photo.setOnClickListener{
            val i = Intent(photo.context, FragmentImage::class.java)
            i.putExtra("ThisPositionImage", positionInt)
            val b = ActivityOptions.makeSceneTransitionAnimation((photo.context as MainActivityViewModel))
            photo.context.startActivity(i, b.toBundle())
        }
    }


    init{
        btnInfo.setOnClickListener {
            val i = Intent(btnInfo.context, DetailImageViewModel::class.java)
            i.putExtra("googlePhoto", googlePhoto.toString())
            i.putExtra("googleTitle", googleTitle.toString())
            i.putExtra("googleSource", title.text.toString())
            i.putExtra("targetUrl", googleUrl.toString())
            btnInfo.context.startActivity(i)
        }

    }

    @SuppressLint("SetTextI18n")
    fun bind(photos: Result) {
        Glide.with(itemView.context).load("${photos.thumbnail}").into(photo)
        title.text = "Source: " +  photos.source
        googlePhoto = photos.thumbnail.toString()
        googleUrl = photos.link.toString()
        googleTitle = "Title: " +  photos.title.toString()
        positionInt = photos.position!!

    }
}

