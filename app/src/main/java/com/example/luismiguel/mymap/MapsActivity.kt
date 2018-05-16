package com.example.luismiguel.mymap

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var lastLocation:Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var zoom:Float = 10.0f

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(p0: LayoutInflater, p1: ViewGroup?, p2: Bundle?): View? {
        val v: View? = super.onCreateView(p0, p1, p2)
        this.getMapAsync(this)

        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity!!)
        return v
    }

    private fun setUpMap(){
        if( ActivityCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_FINE_LOCATION )!=PackageManager.PERMISSION_GRANTED ){
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE )
        }
        else {
            this.mMap.isMyLocationEnabled = true
            this.fusedLocationProviderClient.lastLocation.addOnSuccessListener(activity!!) { location ->
                if (location != null) {
                    this.lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, this.zoom))
                }
            }
        }
    }

    public fun setMarker( nombre:String, latitude:Double, longitude:Double){
        val place = LatLng( latitude, longitude )
        val markerOpcions = MarkerOptions().position( place )
        markerOpcions.title( nombre )

        this.mMap.addMarker( markerOpcions )
        this.mMap.animateCamera( CameraUpdateFactory.newLatLngZoom(place, this.zoom) )
    }

    public fun clearMarked(){
        this.mMap.clear()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        this.setUpMap()
    }

    public fun setZoom( zoom:Float ){
        this.zoom = zoom
        this.setUpMap()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomGesturesEnabled = true

        this.setUpMap()
    }
}
    /*https://stackoverflow.com/questions/41753706/show-current-location-inside-google-map-fragment

    https://androidteachers.com/kotlin-for-android/get-location-in-android-with-kotlin/ */
