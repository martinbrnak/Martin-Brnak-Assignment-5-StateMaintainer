package com.example.statemaintainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by viewModels()
    private lateinit var randomImageButton : Button
    private lateinit var textBar: EditText
    private var randomId : Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomImageButton = findViewById(R.id.buttonCat)
        textBar = findViewById(R.id.textEdit)
        randomId = sharedPreferences.imageId
        findViewById<ImageView>(R.id.images).setImageResource(resources.getIdentifier("_image${randomId}", "drawable", "com.example.statemaintainer"))
        randomImageButton.setOnClickListener{
            randomId = Random.nextInt(1, 12)
            val resourceId = resources.getIdentifier("_image${randomId}", "drawable", "com.example.statemaintainer")
            findViewById<ImageView>(R.id.images).setImageResource(resourceId)
        }
        findViewById<ImageView>(R.id.images).setImageResource(resources.getIdentifier("_image${sharedPreferences.imageId}", "drawable", "com.example.statemaintainer"))
    }

    override fun onDestroy() {
        sharedPreferences.edittedText = textBar.text.toString()
        sharedPreferences.imageId = randomId
        super.onDestroy()
    }
}