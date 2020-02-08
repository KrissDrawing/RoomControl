package com.kriss.roomcontrol


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * A simple [Fragment] subclass.
 */
class ControlFragment : Fragment() {

    lateinit var changeBrightnessSeekBar: SeekBar
    lateinit var turnOnLightsButton: Button
    lateinit var ledModeButton: Button

    lateinit var database: DatabaseReference
    lateinit var databaseStatus: DatabaseReference

    var ledModeButtonText: String = ""
    var turnText: String = ""
    var turnColor: Int = R.color.colorButtonOff

    var switcher: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val controlLayout = inflater.inflate(R.layout.fragment_control, container, false)

        changeBrightnessSeekBar = controlLayout.findViewById(R.id.changeBrightnessSeekBar)
        turnOnLightsButton = controlLayout.findViewById(R.id.turnOnLightsButton)
        ledModeButton = controlLayout.findViewById(R.id.ledModeButton)

        onDataBaseListener()
        initializeUIItems()

        return controlLayout

    }

    private fun onDataBaseListener(){
        database = FirebaseDatabase.getInstance().reference

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                turnText = p0.child("LED_CONTROL").child("TURN").getValue(String::class.java)!!
                ledModeButtonText = p0.child("LED_CONTROL").child("LEDSCENE").getValue(String::class.java)!!

                ledModeButton.setText(ledModeButtonText)
                turnOnLightsButton.setText(turnText)

                if (turnText == getString(R.string.turn_on_lights)) {
                    turnColor = R.color.colorButtonOn
                } else {
                    turnColor = R.color.colorButtonOff
                }

                turnOnLightsButton.setBackgroundColor(ContextCompat.getColor(ledModeButton.context,turnColor)
                )
            }
        })
    }

    private fun initializeUIItems(){
        ledModeButton.setOnClickListener {
            val intent: Intent? = Intent(context, LedModeActivity::class.java)
            startActivity(intent)
        }

        turnOnLightsButton.setOnClickListener {
            turnOnLightsButton.setBackgroundColor(ContextCompat.getColor(turnOnLightsButton.context, turnColor))
            saveData()
        }

        if (::changeBrightnessSeekBar.isInitialized) {
            changeBrightnessSeekBar.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

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
        ref.child("BRIGHTNESS").setValue(changeBrightnessSeekBar.progress.toString())
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
