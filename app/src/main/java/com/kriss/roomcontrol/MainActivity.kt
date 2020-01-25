package com.kriss.roomcontrol

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var turnOnLightsButton : Button
    var switcher: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        turnOnLightsButton = findViewById(R.id.turnOnLightsButton)

        turnOnLightsButton.setOnClickListener {
            saveData();

        }



    }
    private fun saveData(){
        val ref = FirebaseDatabase.getInstance().getReference("switcher")
        ref.child("Name").setValue(switcher)
        switcher = !switcher
    }
}
