package com.kriss.roomcontrol

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var turnOnLightsButton : Button
    lateinit var ledModeButton : Button
    lateinit var changeBrightnessSeekBar: SeekBar
    lateinit var testText: TextView

    lateinit var ref: FirebaseDatabase

    var switcher: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value : String? = p0.child("TURN").getValue(String::class.java)
                testText.text = value
            }

        })

        turnOnLightsButton = findViewById(R.id.turnOnLightsButton)
        ledModeButton = findViewById(R.id.ledModeButton)
        changeBrightnessSeekBar = findViewById(R.id.changeBrightnessSeekBar)
        testText = findViewById(R.id.testText)

        changeBrightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                //testText.text = changeBrightnessSeekBar.progress.toString()
                AdjustBrightness()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }
        )

        ledModeButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext,LedModeActivity::class.java)
            startActivity(intent)
        }

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
