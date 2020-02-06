package com.kriss.roomcontrol

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var changeBrightnessSeekBar: SeekBar
    lateinit var turnOnLightsButton: Button
    lateinit var ledModeButton: Button
    lateinit var testText: TextView
    lateinit var testText2: TextView

    lateinit var database: DatabaseReference
    lateinit var databaseStatus: DatabaseReference

    var ledModeButtonText: String = ""
    var turnText: String = ""
    var turnColor: Int = R.color.colorButtonOff

    var switcher: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeBrightnessSeekBar = findViewById(R.id.changeBrightnessSeekBar)
        turnOnLightsButton = findViewById(R.id.turnOnLightsButton)
        ledModeButton = findViewById(R.id.ledModeButton)
        testText = findViewById(R.id.testText)
        testText2 = findViewById(R.id.testText2)

        database = FirebaseDatabase.getInstance().reference
        //getReference("LED_SCENE")


        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                //val databaseControl : DatabaseReference = database.child("LED_CONTROL")
                turnText = p0.child("LED_CONTROL").child("TURN").getValue(String::class.java)!!
                ledModeButtonText =
                    p0.child("LED_CONTROL").child("LEDSCENE").getValue(String::class.java)!!
                ledModeButton.setText(ledModeButtonText)
                turnOnLightsButton.setText(turnText)
                if (turnText == getString(R.string.turn_on_lights)) {
                    turnColor = R.color.colorButtonOn
                } else {
                    turnColor = R.color.colorButtonOff
                }
                turnOnLightsButton.setBackgroundColor(
                    ContextCompat.getColor(
                        ledModeButton.context,
                        turnColor
                    )
                )
            }
        })

        ledModeButton.setOnClickListener {
            val intent: Intent? = Intent(applicationContext, LedModeActivity::class.java)
            startActivity(intent)
        }

        turnOnLightsButton.setOnClickListener {
            turnOnLightsButton.setBackgroundColor(ContextCompat.getColor(this, turnColor))
            saveData()
        }


        if (::changeBrightnessSeekBar.isInitialized) {
            changeBrightnessSeekBar.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                 /*   changeBrightnessSeekBar.setBackgroundColor(
                        manipulateColor(
                            R.color.colorButtonOn,
                            i.toFloat()
                        )
                    )*/
                    adjustBrightness()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {

                }
            }
            )
        }
    }


    private fun adjustBrightness() {
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")
        ref.child("BRIGHTNESS").setValue(changeBrightnessSeekBar?.progress.toString())
    }

    private fun saveData() {
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")

        if (turnText == getString(R.string.turn_on_lights)) {
            turnText = getString(R.string.turn_off_lights)
        } else {
            turnText = getString(R.string.turn_on_lights)
        }
        ref.child("TURN").setValue(turnText)
        switcher = !switcher
    }

}
