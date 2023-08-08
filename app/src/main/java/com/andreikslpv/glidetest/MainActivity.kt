package com.andreikslpv.glidetest

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.itemImage)

//        image.load("https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=172593&type=card")

//        Glide.with(this)
//            .load("https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=172593&type=card")
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    println("AAA ${e?.rootCauses}")
//                    return true
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    println("AAA onResourceReady")
//                    return true
//                }
//
//            })
//            .into(image)

        val photoUri = Uri.fromFile(File("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=172593&type=card"))

        Glide.with(this)
            .load(photoUri)
            .centerCrop()
            .into(image)
    }
}