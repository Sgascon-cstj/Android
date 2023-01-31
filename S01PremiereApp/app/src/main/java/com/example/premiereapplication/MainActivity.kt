package com.example.premiereapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPhone:Button = findViewById<Button>(R.id.btnPhone)
        val btnSMS = findViewById<Button>(R.id.btnSMS)
        val btnGame = findViewById<Button>(R.id.btnGame)
        val edtPlayerName = findViewById<EditText>(R.id.edtPlayerName)

        //btn phone
        btnPhone.setOnClickListener {
            it.setBackgroundColor(Color.rgb(Random.nextInt(256),Random.nextInt(256),Random.nextInt(256)))
            //Toast.makeText(this,R.string.msgHello,Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:4505670123"))
            startActivity(intent)
        }
        //btn sms
        btnSMS.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:4504361580"))
            intent.putExtra("sms_body","Message de mon code")
            startActivity(intent)
        }
        //btn game
        btnGame.setOnClickListener {
            startActivity(GameActivity.newIntent(this,edtPlayerName.text.toString()))
        }



    }
}