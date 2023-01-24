package com.example.premiereapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.button)
        button.setOnClickListener({ button.setBackgroundColor(Color.rgb(Random.nextInt(256),Random.nextInt(256),Random.nextInt(256)))})


    }
}