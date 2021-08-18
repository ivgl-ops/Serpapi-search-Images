package com.ivangladko.SearchPhotoApp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.ui.FullScreenImageViewModel
import com.ivangladko.SearchPhotoApp.ui.WebViewActivityViewModel


class PhotoAdapter(val photos: List<Result>): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return PhotoViewHolder(view)
    }


    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        return holder.bind(photos[position])

    }
}

class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val photo:ImageView = itemView.findViewById(R.id.google_photo)
    private val title:TextView = itemView.findViewById(R.id.google_title)
    private val btn: Button = itemView.findViewById(R.id.button_webView)
    private val overview:TextView = itemView.findViewById(R.id.google_overview)
    private var googlePhoto: String = ""
    private var googleUrl: String = ""
    init {
        btn.setOnClickListener{
            val i = Intent(btn.context, WebViewActivityViewModel::class.java)
            i.putExtra("googleUrl", googleUrl.toString())
            btn.context.startActivity(i)
        }
    }
    init{
        photo.setOnClickListener {
            val i = Intent(photo.context, FullScreenImageViewModel::class.java)
            i.putExtra("googlePhoto", googlePhoto.toString())
            photo.context.startActivity(i)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(photos: Result) {
        Glide.with(itemView.context).load("${photos.thumbnail}").into(photo)
        title.text =  photos.title
        overview.text ="Site: " + photos.source
        googlePhoto = photos.thumbnail.toString()
        googleUrl = photos.link.toString()
    }
}

