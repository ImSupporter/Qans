package com.example.qans

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qans.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val postDBHelper = SqliteHelper(this, "post", null,1)
    private lateinit var binding : ActivityMainBinding
    private lateinit var myAdapter : PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchButton = binding.searchButton

        //검색 버튼 이벤트
        searchButton.setOnClickListener {

        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = PostAdapter(this, postDBHelper.selectMemo())
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.addItemDecoration(PostDecoration(this))

        floatingEdit.setOnClickListener {
            val intent = Intent(this, PostEditActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("dbHelper", "Main : {${postDBHelper.getCount()}}")
        myAdapter.setData(postDBHelper.selectMemo())
        myAdapter.notifyDataSetChanged()

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