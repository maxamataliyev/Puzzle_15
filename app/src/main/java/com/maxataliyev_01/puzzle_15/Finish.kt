package com.maxataliyev_01.puzzle_15
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class Finish : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        val score = intent.getIntExtra("SCORE", 0)
        val time = intent.getIntExtra("TIME", 0)
        findViewById<TextView>(R.id.winningScore).text = "Step count: $score"
        findViewById<TextView>(R.id.timeScore).text = timeFormat(time)
        val winningRestartButton = findViewById<AppCompatButton>(R.id.winningRestartButton)
        val winningMenuBack = findViewById<AppCompatButton>(R.id.winningMenu)
        winningRestartButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        winningMenuBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun timeFormat(time: Int): String {
        val minute = time / 60
        val second = time % 60
        val secondFormat = if (second < 10) "0${second}" else "$second"
        val minuteFormat = if (minute < 10) "0${minute}" else "$minute"
        return "${minuteFormat}:${secondFormat}"
    }
}
