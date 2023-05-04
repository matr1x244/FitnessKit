package com.example.fitnesskit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesskit.ui.main.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, MainFragment.newInstance())
                .commitNow()
        }

        bottomNavMenu()
    }

    private fun bottomNavMenu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.training_menu -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }
                R.id.like_menu -> {
                    Toast.makeText(this, "like_menu", Toast.LENGTH_SHORT).show()
                }
                R.id.add_menu -> {
                    Toast.makeText(this, "add_menu", Toast.LENGTH_SHORT).show()
                }
                R.id.chat_menu -> {
                    Toast.makeText(this, "chat_menu", Toast.LENGTH_SHORT).show()
                }
                R.id.still_menu -> {
                    Toast.makeText(this, "still_menu", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.training_menu
    }

}