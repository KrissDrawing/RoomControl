package com.kriss.roomcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*

class LedModeActivity : AppCompatActivity() {

    private lateinit var listView : ListView
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led_mode)

        listView = findViewById(R.id.ledSceneListView)

        val listItems = arrayOf("January", "February", "March")

        database = FirebaseDatabase.getInstance().getReference("LED_SCENE")

        database.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val nameValue : String? = p0.child("name").getValue(String::class.java)
                val descriptionValue : String? = p0.child("description").getValue(String::class.java)
                listItems[1] = nameValue!!
                listItems[2] = descriptionValue!!
            }

        })




        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }
}
