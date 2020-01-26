package com.kriss.roomcontrol

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var turnOnLightsButton : Button
    lateinit var changeBrightnessSeekBar: SeekBar
    lateinit var testText: TextView
    var switcher: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        turnOnLightsButton = findViewById(R.id.turnOnLightsButton)
        changeBrightnessSeekBar = findViewById(R.id.changeBrightnessSeekBar)
        testText = findViewById(R.id.testText)

        changeBrightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                testText.text = changeBrightnessSeekBar.progress.toString()
                AdjustBrightness()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }

        )

        turnOnLightsButton.setOnClickListener {
            saveData()
        }
    }
    private fun AdjustBrightness(){
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")
        ref.child("BRIGHTNESS").setValue(changeBrightnessSeekBar.progress.toString())
    }

    private fun saveData(){
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")
        ref.child("TURN").setValue(switcher.toString())

        switcher = !switcher
    }
}
