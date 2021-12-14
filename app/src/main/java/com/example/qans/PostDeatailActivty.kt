package com.example.qans

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qans.databinding.ActivityPostDetailBinding

class PostDeatailActivty :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View 만들기
        val binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButtom.setOnClickListener {
            finish()
        }


        var data = intent.getSerializableExtra("data") as Post

        binding.title.text = data.title
        binding.user.text = data.writer
        binding.photo.setImageResource(data.image)
        binding.mainText.text = data.mainText
    }
}