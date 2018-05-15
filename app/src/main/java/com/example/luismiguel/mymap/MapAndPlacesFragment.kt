package com.example.luismiguel.mymap

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var layoutPlaces: ArrayList<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_map_and_places, container, false)

        this.layoutPlaces = ArrayList()
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces1) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces2) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces3) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces4) )
        this.layoutPlaces.add( view.findViewById(R.id.layoutPlaces5) )

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event

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
