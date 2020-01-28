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
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var changeBrightnessSeekBar: SeekBar
    lateinit var turnOnLightsButton : Button
    lateinit var ledModeButton: Button
    lateinit var testText: TextView
    lateinit var testText2: TextView

    lateinit var database: DatabaseReference

    var switcher: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeBrightnessSeekBar = findViewById(R.id.changeBrightnessSeekBar)
        turnOnLightsButton = findViewById(R.id.turnOnLightsButton)
        ledModeButton = findViewById(R.id.ledModeButton)
        testText = findViewById(R.id.testText)
        testText2 = findViewById(R.id.testText2)

        database = FirebaseDatabase.getInstance().getReference("LED_SCENE")

        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val nameValue : String? = p0.child("name").getValue(String::class.java)
                val descriptionValue : String? = p0.child("description").getValue(String::class.java)
                testText?.text = nameValue
                testText2?.text = descriptionValue
            }

        })


        ledModeButton.setOnClickListener{
            val intent: Intent? = Intent(applicationContext,LedModeActivity::class.java)
            startActivity(intent)
        }

        turnOnLightsButton.setOnClickListener {
            saveData()
        }

        if (::changeBrightnessSeekBar.isInitialized){
            changeBrightnessSeekBar.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    //testText.text = changeBrightnessSeekBar.progress.toString()
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


    private fun adjustBrightness(){
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")
        ref.child("BRIGHTNESS").setValue(changeBrightnessSeekBar?.progress.toString())
    }

    private fun saveData(){
        val ref = FirebaseDatabase.getInstance().getReference("LED_CONTROL")
        ref.child("TURN").setValue(switcher.toString())
        switcher = !switcher
    }
}
