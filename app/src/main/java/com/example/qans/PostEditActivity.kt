package com.example.qans

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qans.databinding.ActivityPostEditBinding

class PostEditActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postDBHelper = SqliteHelper(this, "post", null,1)
        binding.btnFinish.setOnClickListener {
            if(binding.editTitle.text.isBlank()){
                Toast.makeText(this, "제목을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            else if(binding.editCategory.text.isBlank()){
                Toast.makeText(this, "카테고리을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            else if(binding.editId.text.isBlank()){
                Toast.makeText(this, "아이디을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            else if(binding.editMaintext.text.isBlank()){
                Toast.makeText(this, "내용을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            else{
                val title = binding.editTitle.text.toString()
                val category = binding.editCategory.text.toString()
                val writer = binding.editId.text.toString()
                val mainText = binding.editMaintext.text.toString()
                Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show()

                postDBHelper.insertMemo(Post(99, category,title,writer,mainText, null))
                Log.d("dbHelper", "Edit : {${postDBHelper.getCount()}}")
                finish()
            }
        }

    }
}