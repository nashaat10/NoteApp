package com.example.noteapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = ActivityAddNoteBinding.inflate(layoutInflater)
       setContentView(binding.root)
       title = "Add Note"

       binding.insertBtn.setOnClickListener {
           val noteDetails = binding.noteEt.text.toString()
           val values = ContentValues()
           values.put("details",noteDetails)
           val helper = DBHelper(this)
           val db = helper.writableDatabase

           val rowId = db.insert("note",null,values)
           if (rowId != -1L){
               Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
               binding.noteEt.text?.clear()
           }
       }
    }
}