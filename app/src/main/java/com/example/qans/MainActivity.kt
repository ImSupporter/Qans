package com.example.qans

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qans.databinding.ActivityMainBinding
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var postList = arrayListOf<Post>(
        Post(1,"유머","헬스장 빌런 특","aiden18","Test1",R.drawable.sample_image_barbel),
        Post(2,"헬스","헬스장 추천 좀","aiden18","Test2",R.drawable.sample_image_gym),
        Post(3,"음식","득근득근에 좋은 음식","aiden18","Test3",R.drawable.sample_image_chicken),
        Post(4,"눈바디","몸평 해주세요","aiden18","Test4",R.drawable.sample_image_body),
        Post(5,"유머","헬스장 빌런 특","aiden18","Test1",R.drawable.sample_image_barbel),
        Post(6,"헬스","헬스장 추천 좀","aiden18","Test2",R.drawable.sample_image_gym),
        Post(7,"음식","득근득근에 좋은 음식","aiden18","Test3",R.drawable.sample_image_chicken),
        Post(8,"음식","득근득근에 좋은 음식","aiden18","Test3",R.drawable.sample_image_chicken)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = PostAdapter(postList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PostAdapter(postList)
        binding.recyclerView.addItemDecoration(PostDecoration(this))

    }

    inner class PostDecoration(val context : Context) : RecyclerView.ItemDecoration(){
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {   // 항목이 배치되기 전 호출되는 함수(배경)
            super.onDraw(c, parent, state)
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {   // 항목이 배치된 후 호출(항목위에 이미지가 그려짐)
            super.onDrawOver(c, parent, state)
        }

        override fun getItemOffsets(   // 항목당 한번씩 나타나는 함수(각 아이템을 꾸밀 때 사용
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.set(5,5,5,5)
        }


    }
}