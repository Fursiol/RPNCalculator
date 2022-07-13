package com.example.rpncalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import android.media.VolumeShaper.Configuration

class MenuActivity : AppCompatActivity() {
    var decimalPlaces : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Zaimplementowanie listy do wyboru ilosci miejsc po przecinku
        val mySpinner: Spinner = findViewById(R.id.spinner1)
        val myAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.decimals)
        )
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.adapter = myAdapter
        mySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent : AdapterView<*>, view : View, position : Int, id : Long) {
                var item: String = parent.getItemAtPosition(position).toString()
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show()
                decimalPlaces = item.toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        // Obsluga przycisku przekazujacego decimalPlaces do glownej aktywnosci i przechodzacego do niej
        val applyButton : Button = findViewById(R.id.applyButton)
        applyButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("decimalPlaces", decimalPlaces)
            startActivity(intent)
        }
    }
}