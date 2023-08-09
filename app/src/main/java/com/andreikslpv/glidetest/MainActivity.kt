package com.andreikslpv.glidetest

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.itemImage)

        val trustAllCertificates = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
        })

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCertificates, SecureRandom())

        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()

        val factory = OkHttpUrlLoader.Factory(okHttpClient)

        Glide.get(this).registry.replace(GlideUrl::class.java, InputStream::class.java, factory)

        Glide.with(this)
            .load("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=172593&type=card")
            .fitCenter()
            .into(image)

    }
}