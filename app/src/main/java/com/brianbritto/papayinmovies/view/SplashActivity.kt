package com.brianbritto.papayinmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.brianbritto.papayinmovies.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationHello = AnimationUtils.loadAnimation(this,
            R.anim.zoom_back_in
        )
        logo_imageview.startAnimation(animationHello)

        val animationMensaje = AnimationUtils.loadAnimation(this,
            R.anim.push_down_in
        )
        mensaje_textview.startAnimation(animationMensaje)

        init()
        startMainActivityUsingHandler()
    }

    private fun startMainActivityUsingHandler() {
        handler.postDelayed(runnable, 5000)
    }

    private fun init() {
        handler = Handler()
        runnable = Runnable {
            openMainActivity()
        }
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
        handler.removeMessages(0)
    }
}
