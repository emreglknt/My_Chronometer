package com.example.kronometre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kronometre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonStart.setOnClickListener {
            binding.chronometerTv.startChronometer()
            binding.buttonStart.visibility = View.GONE
            binding.btnPause.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))

        }


        binding.btnPause.setOnClickListener {
            binding.chronometerTv.pauseChronometer()
            binding.btnPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))

        }


        binding.btnreset.setOnClickListener {
           binding.chronometerTv.resetChronometer()
            binding.chronometerTv.stop()
            binding.btnPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))

        }


    }
}