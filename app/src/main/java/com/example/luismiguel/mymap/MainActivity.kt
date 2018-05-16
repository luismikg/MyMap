package com.example.luismiguel.mymap

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var isSmarthone: Boolean = true
    private var preferencesChanged: Boolean = false

    companion object {
        public const val NUMBER_PLACES_TO_SHOW = "prefNumberButtons"
        public const val PREFERENCE_ZOOM = "prefZoom"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initActivity()
        //https://www.skyscanner.es/noticias/10-lugares-espectaculares-del-mundo-que-deberias-visitar
        //PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener( preferencesChangeListener )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if( this.isSmarthone ){
            //this.menuInflater.inflate(R.menu.configuration_places, menu)
            this.menuInflater.inflate(R.menu.configuration_buttons, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val preferencesIntent:Intent = Intent(this, PreferencesPlacesConfigurations::class.java)
        startActivity( preferencesIntent )

        return super.onOptionsItemSelected(item)
    }

    private fun initActivity(){
        this.initScreen()
        this.initActions()
    }


    private fun initScreen(){
        val screenSize:Int = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        if( screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE ) {
            this.isSmarthone = false
        }

        if( this.isSmarthone ){
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }else{
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
    private fun initActions(){
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        //Registro del "listener" para SharedPreferences
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(preferencesChangeListener)
    }

    private val preferencesChangeListener = OnSharedPreferenceChangeListener { sharedPreferences, key ->
        // Called when the user changes the app's preferences.
        this.preferencesChanged = true  // User changed app settings.

        val placesFragment = supportFragmentManager.findFragmentById(R.id.placesFragment) as MapAndPlacesFragment

        if (key == NUMBER_PLACES_TO_SHOW) {
            // Number of choices to display changed.
            placesFragment.updateGuessRows(sharedPreferences)
        }else if( key == PREFERENCE_ZOOM ){
            placesFragment.updateZoom(sharedPreferences)
        }
        Toast.makeText(this@MainActivity,
                "Ok",
                Toast.LENGTH_SHORT).show()
    }
}
