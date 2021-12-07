package com.example.qans

import java.time.LocalDateTime

class Post (
    val id: Long,
    val category: String,
    var title: String,
    val writer: String,
    var mainText: String,
    val image: Int
    //time은 추후 추가
    // LocalDateTime 사용
)