package com.example.qans

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper (context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table post (id integer primary key, category text, title text, mainText text, writer text,image integer)"
        db?.execSQL(create)
        setDefault(db, Post(1,"유머","헬스장 빌런 특","aiden18","Test1",R.drawable.sample_image_barbel))
        setDefault(db, Post(2,"헬스","헬스장 추천 좀","jennie00","Test2",R.drawable.sample_image_gym))
        setDefault(db, Post(3,"음식","득근득근에 좋은 음식","kevin989","Test3",null))
        setDefault(db ,Post(4,"눈바디","몸평 해주세요","aiden18","Test4",R.drawable.sample_image_body))
        setDefault(db, Post(5,"유머","헬스장 빌런 특","chris2001","Test1",R.drawable.sample_image_barbel))
        setDefault(db, Post(6,"헬스","헬스장 추천 좀","jennie00","Test2",R.drawable.sample_image_gym))
        setDefault(db, Post(7,"음식","득근득근에 좋은 음식","94tjghks","Test3",R.drawable.sample_image_chicken))
    }
    private fun setDefault(db: SQLiteDatabase?,post :Post){
        val values = ContentValues()
        values.put("id", post.id)
        values.put("category", post.category)
        values.put("title", post.title)
        values.put("mainText", post.mainText)
        values.put("writer", post.writer)
        values.put("image", post.image)
        db?.insert("post", null, values)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    public fun insertMemo(post : Post){
        val values = ContentValues()
        val id = getCount()
        values.put("id", id+1)
        values.put("category", post.category)
        values.put("title", post.title)
        values.put("mainText", post.mainText)
        values.put("writer", post.writer)

        val wd = writableDatabase

        wd.insert("post", null, values)
        wd.close()
    }


    @SuppressLint("Range")
    public fun selectMemo():MutableList<Post>{
        val list = mutableListOf<Post>()
        //전체 조회
        val selectAll = "select * from post"
        //읽기 전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        while(cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val category = cursor.getString(cursor.getColumnIndex("category"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val mainText = cursor.getString(cursor.getColumnIndex("mainText"))
            val writer = cursor.getString(cursor.getColumnIndex("writer"))
            var image = cursor.getInt(cursor.getColumnIndex("image"))

            if(image == 0) list.add(Post(id,category,title, writer, mainText,null))
            else list.add(Post(id,category,title, writer, mainText,image))
        }

        cursor.close()
        rd.close()

        return list
    }

    public fun updatePost(post:Post){
        val values = ContentValues()

        values.put("content",post.mainText)
        values.put("datetime",post.id)

        val wd = writableDatabase
        wd.update("post",values,"id=${post.id}",null)
        wd.close()
    }

    public fun deletePost(post:Post){
        val delete = "delete from Memo where id = ${post.id}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

    public fun getCount(): Int {
        val rd = readableDatabase
        val cursor = rd.rawQuery("select * from post",null)

        return cursor.count
    }
}