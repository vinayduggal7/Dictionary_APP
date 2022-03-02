package com.techvd.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techvd.dictionaryapp.databinding.ActivityMainBinding
import com.techvd.dictionaryapp.databinding.ActivityWordDefinitionBinding

class WordDefinitionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWordDefinitionBinding
    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordDefinitionBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        binding.definitionTextView.text = intent.getStringExtra(KEY)

        binding.definitionImageView.setOnClickListener{
            finish()
        }

    }
}