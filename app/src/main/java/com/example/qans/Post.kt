package com.example.qans

import java.io.Serializable
import java.time.LocalDateTime

class Post (
    val id: Long,
    val category: String,
    var title: String,
    val writer: String,
    var mainText: String,
    val image: Int?
) : Serializable{
    var type = TEXT_TYPE
    init {
        if(image != null) type = IMAGE_TYPE
    }
    companion object{
        const val TEXT_TYPE = 0
        const val IMAGE_TYPE = 1
    }
}