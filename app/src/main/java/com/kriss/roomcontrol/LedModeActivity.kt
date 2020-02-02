package com.kriss.roomcontrol

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_led_mode.*


class LedModeActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var ledAdapter: LedSceneRecyclerAdapter
    lateinit var database: DatabaseReference
    private val listItems: MutableList<LedScene> = mutableListOf()
    private val TAG = "dupa123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led_mode)




        database = FirebaseDatabase.getInstance().getReference("LED_SCENE")

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //Log.v(TAG, "nie udalo sie")
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.mapNotNullTo(listItems) { it.getValue<LedScene>(LedScene::class.java) }

                //listItems.forEach { Log.v(TAG, "test " + it.name + " " + it.description) }
                initRecyclerView()
                addDataSet()

            }
        })

    }

    private fun addDataSet() {
    ledAdapter.submitList(listItems)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@LedModeActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            ledAdapter = LedSceneRecyclerAdapter()
            adapter = ledAdapter
        }
    }
}