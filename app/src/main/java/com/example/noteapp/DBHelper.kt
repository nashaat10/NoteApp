package com.example.noteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context :Context) :SQLiteOpenHelper(context,"MyDB",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table note(_id integer primary key autoincrement , details text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}