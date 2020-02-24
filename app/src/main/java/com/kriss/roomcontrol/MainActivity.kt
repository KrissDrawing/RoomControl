package com.kriss.roomcontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(){

    private lateinit var outState : Bundle

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
        replaceFragment(ControlFragment())
        bottomNavigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        initializeToolbar()
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun initializeToolbar(){
        val toolbar: Toolbar? = findViewById(R.id.toolbar) as Toolbar?
        if (toolbar != null) {
            setSupportActionBar(toolbar) //To display toolbar
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setElevation(1f) // or other...
        }
    }

}
