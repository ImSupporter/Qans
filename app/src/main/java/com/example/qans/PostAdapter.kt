package com.example.qans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.CustomViewHolder> (){

    // 뷰홀더가 처음 생성될 때
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return CustomViewHolder(view)
    }


    //재활용해주는 곳 값을 넣어주는
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.image.setImageResource(postList[position].image)
        holder.writer.text = postList[position].writer
        holder.category.text = postList[position].category
        holder.title.text = postList[position].title
        holder.time.text = "24분전"
        holder.mainText.text = postList[position].mainText

        //간격 설정
    }

    // 리스트의 개수를 넣어준다.
    override fun getItemCount(): Int {
        return postList.size
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.cardImage)
        val category = itemView.findViewById<TextView>(R.id.category)
        val title = itemView.findViewById<TextView>(R.id.title)
        val writer = itemView.findViewById<TextView>(R.id.writer)
        val time = itemView.findViewById<TextView>(R.id.time)
        val mainText = itemView.findViewById<TextView>(R.id.mainText)
    }
}