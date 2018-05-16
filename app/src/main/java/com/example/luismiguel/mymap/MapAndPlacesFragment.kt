package com.example.luismiguel.mymap

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout


// TODO: Rename parameter arguments, choose names that match
data class Site(val nombre:String, val latitude:Double, val longitude:Double)

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MapAndPlacesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MapAndPlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MapAndPlacesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var layoutPlaces: ArrayList<LinearLayout>

    //Places
    lateinit var place1:Site
    lateinit var place2:Site
    lateinit var place3:Site
    lateinit var place4:Site

    lateinit var place5:Site
    lateinit var place6:Site
    lateinit var place7:Site
    lateinit var place8:Site

    lateinit var place9:Site
    lateinit var place10:Site



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_map_and_places, container, false)

        this.initActivity( view )

        return view
    }

    private fun initActivity( view: View ){
        this.initActions( view )
    }

    private fun initActions( view: View ){
        this.initLayoutPlaces( view )
        this.initButtonPlaces( view )
    }

    private fun initLayoutPlaces( view: View ){
        this.layoutPlaces = ArrayList()
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces1) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces2) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces3) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces4) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces5) )

        //Places
        this.place1 = Site( getString(R.string.site1), -20.1337772, -67.4891345)
        this.place2 = Site( getString(R.string.site2), -14.087238, -75.763030)
        this.place3 = Site( getString(R.string.site3), -38.261346, 175.123145)
        this.place4 = Site( getString(R.string.site4), -25.695245, -54.436658)

        this.place5 = Site( getString(R.string.site5), -30.591075, 115.160251)
        this.place6 = Site( getString(R.string.site6), 2.264229, -73.794448)
        this.place7 = Site( getString(R.string.site7), 38.629180, 34.803982)
        this.place8 = Site( getString(R.string.site8), -21.650979, 35.469177)

        this.place9 = Site( getString(R.string.site9), 55.240813, -6.511553)
        this.place10 = Site( getString(R.string.site10), -50.494702, -73.137639)
    }

    private fun initButtonPlaces( view: View ){
        val buttonSite1:Button = view.findViewById(R.id.site1) as Button
        val buttonSite2:Button = view.findViewById(R.id.site2) as Button
        val buttonSite3:Button = view.findViewById(R.id.site3) as Button
        val buttonSite4:Button = view.findViewById(R.id.site4) as Button
        val buttonSite5:Button = view.findViewById(R.id.site5) as Button
        val buttonSite6:Button = view.findViewById(R.id.site6) as Button
        val buttonSite7:Button = view.findViewById(R.id.site7) as Button
        val buttonSite8:Button = view.findViewById(R.id.site8) as Button
        val buttonSite9:Button = view.findViewById(R.id.site9) as Button
        val buttonSite10:Button = view.findViewById(R.id.site10)  as Button

        buttonSite1.setOnClickListener {
            view -> this.showPlace("place1")
        }
        buttonSite2.setOnClickListener {
            view -> this.showPlace("place2")
        }
        buttonSite3.setOnClickListener {
            view -> this.showPlace("place3")
        }
        buttonSite4.setOnClickListener {
            view -> this.showPlace("place4")
        }
        buttonSite5.setOnClickListener {
            view -> this.showPlace("place5")
        }
        buttonSite6.setOnClickListener {
            view -> this.showPlace("place6")
        }
        buttonSite7.setOnClickListener {
            view -> this.showPlace("place7")
        }
        buttonSite8.setOnClickListener {
            view -> this.showPlace("place8")
        }
        buttonSite9.setOnClickListener {
            view -> this.showPlace("place9")
        }
        buttonSite10.setOnClickListener {
            view -> this.showPlace("place10")
        }

    }

    // TODO: Rename method, update argument and hook method into UI event

    private fun showPlace( place: String ){

        val mapActivity = childFragmentManager.findFragmentById(R.id.mapFragment) as MapsActivity
        mapActivity.clearMarked()
        when(place){
            "place1" -> {
                mapActivity.setMarker( this.place1.nombre, this.place1.latitude, place1.longitude )
            }
            "place2" -> mapActivity.setMarker( this.place2.nombre, this.place2.latitude, place2.longitude )

            "place3" -> mapActivity.setMarker( this.place3.nombre, this.place3.latitude, place3.longitude )

            "place4" -> mapActivity.setMarker( this.place4.nombre, this.place4.latitude, place4.longitude )

            "place5" -> mapActivity.setMarker( this.place5.nombre, this.place5.latitude, place5.longitude )

            "place6" -> mapActivity.setMarker( this.place6.nombre, this.place6.latitude, place6.longitude )

            "place7" -> mapActivity.setMarker( this.place7.nombre, this.place7.latitude, place7.longitude )

            "place8" -> mapActivity.setMarker( this.place8.nombre, this.place8.latitude, place8.longitude )

            "place9" -> mapActivity.setMarker( this.place9.nombre, this.place9.latitude, place9.longitude )

            "place10" -> mapActivity.setMarker( this.place10.nombre, this.place10.latitude, place10.longitude )

        }
    }

    public fun updateGuessRows( sharedPreferences: SharedPreferences ){
        val numberPlacesToShow:String = sharedPreferences.getString( MainActivity.NUMBER_PLACES_TO_SHOW,"10" )
        val numberLayoutButtonVisibled = Integer.parseInt(numberPlacesToShow) / 2;

        for (layout:LinearLayout in this.layoutPlaces){
            layout.visibility = View.GONE
        }

        for (i in 0..numberLayoutButtonVisibled-1){
            layoutPlaces[i].visibility = View.VISIBLE
        }
    }
}
