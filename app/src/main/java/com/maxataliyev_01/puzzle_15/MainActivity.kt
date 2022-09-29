package com.maxataliyev_01.puzzle_15

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.app.AppCompatActivity



class MainActivity:AppCompatActivity(){
    private lateinit var btnPlay:AppCompatButton
    private lateinit var player:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlay=findViewById(R.id.play)
        player=findViewById(R.id.playerName)

        btnPlay.setOnClickListener {
            if (player.text.toString().isNotEmpty()) {
                startActivity(Intent(this,GameActivity::class.java))
            }else Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show()
        }

////suio;lnkdzbz
            //roslkndfzfas
        //zdtng z

        val btnScore=findViewById<AppCompatButton>(R.id.score)
        btnScore.setOnClickListener {
            val intent=Intent(this,ScoreActivity::class.java)
            startActivity(intent)
        }
        val btnSetting=findViewById<AppCompatButton>(R.id.setting)
        btnSetting.setOnClickListener {
            val intent=Intent(this,Settings::class.java)
            startActivity(intent)
        }


        val btnAbout=findViewById<AppCompatButton>(R.id.About)
        btnAbout.setOnClickListener {
            val intent=Intent(this,About_us::class.java)
            startActivity(intent)
        }
    }
}
