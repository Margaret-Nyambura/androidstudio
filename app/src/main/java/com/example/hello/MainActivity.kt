package com.example.hello

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hello.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener {
            val intent = Intent(this,SecondBirdActivity::class.java)
            startActivity(intent)
        }

    Picasso
        .get()
        .load("data:image/jpeg;base64,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")
        .into(binding.imageView)

        }
    }
