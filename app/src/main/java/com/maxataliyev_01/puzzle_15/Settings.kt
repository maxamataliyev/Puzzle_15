package com.maxataliyev_01.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.maxataliyev_01.puzzle_15.util.SharedPreferencesHelper

class Settings : AppCompatActivity() {
    private val shared by lazy {
        SharedPreferencesHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        findViewById<Button>(R.id.uzb).setOnClickListener {
            shared.setLanguage("uz", this)
            startActivity(Intent(this,MainActivity::class.java))
        }

        findViewById<Button>(R.id.eng).setOnClickListener {
            shared.setLanguage("en", this)
            startActivity(Intent(this,MainActivity::class.java))
        }
        findViewById<Button>(R.id.ru).setOnClickListener {
            shared.setLanguage("ru", this)
            startActivity(Intent(this,MainActivity::class.java))
        }


    }

}
