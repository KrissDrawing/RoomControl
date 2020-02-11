package com.kriss.roomcontrol

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.kriss.roomcontrol.dummy.DummyContent
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : AppCompatActivity(){

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{ item->
        when(item.itemId){
            R.id.control ->{
                replaceFragment(ControlFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.goldenRule ->{
                replaceFragment(GoldenRuleFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.checkList ->{
                replaceFragment(TaskFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
            false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        replaceFragment(ControlFragment())
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}
