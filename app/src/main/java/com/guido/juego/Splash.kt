package com.guido.juego

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.guido.juego.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cuentaatras()
    }

    private fun cuentaatras() {
        Handler(Looper.myLooper()!!).postDelayed(5000){
            startActivity(Intent(this,MainActivity::class.java))

        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
