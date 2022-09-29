package com.maxataliyev_01.puzzle_15



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.math.absoluteValue

class GameActivity : AppCompatActivity() {
    private var allButtons = ArrayList<ArrayList<AppCompatButton>>()
    private var numbers = ArrayList<Int>()
    private var scoreCounter = 0
    var timeCounter = 0
    private lateinit var score: TextView
    lateinit var time: TextView
    private lateinit var passiveCoordinate: Coordinate
    private lateinit var btnParent: RelativeLayout
    private lateinit var timer: Timer
    private lateinit var restartButton:AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnParent = findViewById(R.id.buttonsParent)
        restartButton = findViewById(R.id.resButton)
        loadAllViews()
        loadNumbers()
        shuffle()
        loadDataToView()
        setTimer()
        restartButton.setOnClickListener {
            timer.cancel()
            loadAllViews()
            shuffle()
            loadDataToView()
            setTimer()
        }
    }


    private fun loadAllViews() {
        var list = ArrayList<AppCompatButton>()
        for (i in 0 until btnParent.childCount) {
            val b = btnParent.getChildAt(i)
            b.setOnClickListener {
                check(it as AppCompatButton)
            }
            b.tag = Coordinate(i / 4, i % 4)
            list.add(b as AppCompatButton)
            if ((i + 1) % 4 == 0) {
                print("ssss list $list")
                allButtons.add(list)
                list = ArrayList()
                print("ssss matrix $allButtons")
            }
        }

        passiveCoordinate = Coordinate(3, 3)

        score = findViewById(R.id.score_but)
        time = findViewById(R.id.time_but)
    }

    private fun loadNumbers() {
        for (i in 1..15) {
            numbers.add(i)
        }
    }

    private fun loadDataToView() {
        var t = 0
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (i == 3 && j == 3) {
                    allButtons[i][j].text = ""
                    allButtons[i][j].setBackgroundResource(R.drawable.bg_passive_btn)

                } else {
                    allButtons[i][j].text = "${numbers[t++]}"
                    allButtons[i][j].setBackgroundResource(R.drawable.bg_active_btn)
                }
            }
        }
        scoreCounter = 0
        timeCounter = 0
        score.text = "$scoreCounter"
        time.text = timeFormat(timeCounter)
    }

    private fun shuffle() {
        numbers.shuffle()
    }

    private fun setTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                time.text = timeFormat(++timeCounter)
            }

        }, 1000, 1000)
    }

    fun timeFormat(time: Int): String {
        val minute = time / 60
        val second = time % 60
        val secondFormat = if (second < 10) "0${second}" else "$second"
        val minuteFormat = if (minute < 10) "0${minute}" else "$minute"
        return "${minuteFormat}:${secondFormat}"
    }

    private fun check(activeBtn: AppCompatButton) {
        val activeCoordinate = activeBtn.tag as Coordinate
        if (
            (activeCoordinate.x - passiveCoordinate.x).absoluteValue
            + (activeCoordinate.y - passiveCoordinate.y).absoluteValue == 1
        ) {
            val passiveBtn = allButtons[passiveCoordinate.x][passiveCoordinate.y]
            passiveBtn.text = activeBtn.text
            activeBtn.text = ""
            passiveBtn.setBackgroundResource(R.drawable.bg_active_btn)
            activeBtn.setBackgroundResource(R.drawable.bg_passive_btn)
            passiveCoordinate.x = activeCoordinate.x
            passiveCoordinate.y = activeCoordinate.y


            score.text = "${++scoreCounter}"

            if (isWin()) {
                timer.cancel()
                Timer("SettingUp", false).schedule(1000) {
                    val intent = Intent(this@GameActivity, Finish::class.java)
                    intent.putExtra("SCORE", scoreCounter)
                    intent.putExtra("TIME", timeCounter)
                    startActivity(intent)
                }
            }
        }
    }

    private fun isWin(): Boolean {
        if (passiveCoordinate.x != 3 && passiveCoordinate.y != 3) return false
        var isTrue = true
        for (i in 0..14) {
            isTrue = isTrue && "${i + 1}" == allButtons[(i) / 4][(i) % 4].text.toString()
        }
        return isTrue
    }

}
