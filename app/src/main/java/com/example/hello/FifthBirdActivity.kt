package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.hello.databinding.ActivityFifthBirdBinding

class FifthBirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityFifthBirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBirdBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnPrev4.setOnClickListener { finish()
        }

    }
}