package com.techvd.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.techvd.dictionaryapp.databinding.ActivityMainBinding
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)
        binding.dictButtonId.setOnClickListener {
            val word = binding.dictEditText.text
            val apiKey = "b87b1b7d-d9a3-49ec-97bc-e858229e2084"
            val url =
                "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    try{
                    extractDefinitionFromJson(response) }
                    catch(exception : Exception){
                        exception.printStackTrace()
                    }},
                { error ->
                    Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show() }
            )

            queue.add(stringRequest)
        }

    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val jsonObject =  jsonArray.getJSONObject(0)
        val getShortDefinition = jsonObject.getJSONArray("shortdef")
        val firstDefinition = getShortDefinition.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY,firstDefinition.toString())
        startActivity(intent)


    }
}