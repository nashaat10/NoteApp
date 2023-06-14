package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.noteapp.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val i = Intent(this,AddNote::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        val helper = DBHelper(this)
        val db = helper.readableDatabase
        val c = db.rawQuery("select*from note",null )
        val details = arrayListOf<String>()
        val ids = arrayListOf<Int>()

        while (c.moveToNext()){
            details.add(c.getString(1))
            ids.add(c.getInt(0))
        }

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,details)
        binding.list.adapter = adapter

        binding.list.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this,EditActivity::class.java)
            intent.putExtra("note",details[i])
            intent.putExtra("id",ids[i])
            startActivity(intent)
        }


    }

}