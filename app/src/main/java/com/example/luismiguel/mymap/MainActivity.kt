package com.example.luismiguel.mymap

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.configuration_places, menu)
        this.menuInflater.inflate(R.menu.configuration_buttons, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var preferencesIntent:Intent = Intent(this, PreferencesPlacesConfigurations::class.java)
        startActivity( preferencesIntent )

        return super.onOptionsItemSelected(item)
    }
}
