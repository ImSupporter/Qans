package com.example.qans

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.qans.databinding.ItemImageBinding
import com.example.qans.databinding.ItemNoimageBinding
import java.lang.RuntimeException

class PostAdapter(private val context: Context, private var postList: MutableList<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder> (){
    var helper:SqliteHelper? = null
    // 뷰홀더가 처음 생성될 때
    // viewType의 값은 getItemViewType의 리턴값
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            Post.IMAGE_TYPE->{
                val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ImageViewHolder(binding)
            }
            Post.TEXT_TYPE->{
                val binding = ItemNoimageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                TextViewHolder(binding)
            }
            else->throw RuntimeException("ViewHolder Error")
        }
    }

    //재활용해주는 곳 값을 넣어주는
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = postList[position]
        when(item.type){
            Post.TEXT_TYPE->(holder as TextViewHolder).bind(item)
            Post.IMAGE_TYPE->(holder as ImageViewHolder).bind(item)
        }

    }

    // 리스트의 개수를 넣어준다.
    override fun getItemCount(): Int {
        return postList.size
    }

    override fun getItemViewType(position: Int): Int {
        return postList[position].type
    }

    public fun setData(posts : MutableList<Post>){
        postList = posts
    }

    inner class ImageViewHolder(private val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item :Post){
            // View에 데이터 넣어주기
            val pos = bindingAdapterPosition
            binding.apply {
                cardImage.setImageResource(item.image?:R.drawable.ic_launcher_background)
                writer.text = item.writer
                category.text = item.category
                title.text = item.title
                time.text = "24분전"
                mainText.text = item.mainText
            }

            //
            binding.root.setOnClickListener{
                Log.d("ClickPos", "Pos : $pos")
                Intent(context,PostDeatailActivty::class.java).apply {
                    putExtra("data",item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
    inner class TextViewHolder(private val binding: ItemNoimageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item :Post){
            // View에 데이터 넣어주기
            val pos = bindingAdapterPosition
            binding.apply {
                writer.text = item.writer
                category.text = item.category
                title.text = item.title
                time.text = "24분전"
                mainText.text = item.mainText
            }

            //
            binding.root.setOnClickListener{
                Log.d("ClickPos", "Pos : $pos")
                Intent(context,PostDeatailActivty::class.java).apply {
                    putExtra("data",item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }


}