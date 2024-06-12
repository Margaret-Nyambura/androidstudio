package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.hello.databinding.ActivityMainBinding
import com.example.hello.databinding.ActivitySecondBirdBinding
import com.squareup.picasso.Picasso

class SecondBirdActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPrev.setOnClickListener {
            finish()
        }

        binding.btnNext2.setOnClickListener {
            val intent = Intent(this, ThirdBirdActivity::class.java)
            startActivity(intent)
        }

       Picasso
           .get()
           .load("data:image/jpeg;base64,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")
           .into(binding.imageView7)
    }

}