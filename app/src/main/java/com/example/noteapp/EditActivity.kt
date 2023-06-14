package com.example.noteapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.noteEt.setText(intent.getStringExtra("note"))
        val id = intent.getIntExtra("id",-1)
        
        val helper = DBHelper(this)
        val db = helper.writableDatabase
        binding.updateBtn.setOnClickListener { 
            val updateNote = binding.noteEt.text.toString()
            val values = ContentValues()
            values.put("details",updateNote)
            val rowId = db.update("note",values,"_id = ?", arrayOf("$id"))
            if (rowId != 0){
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.deleteBtn.setOnClickListener {
            val rowNo = db.delete("note","_id = ?", arrayOf("$id"))
            if (rowNo != 0){
                Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
                binding.updateBtn.isEnabled = false
                binding.deleteBtn.isEnabled = false
                finish()
            }
        }

    }
}