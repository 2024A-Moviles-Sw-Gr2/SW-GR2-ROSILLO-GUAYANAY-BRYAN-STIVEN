package com.example.deber02_bsrg.layouts.maps

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.deber02_bsrg.R
import com.example.deber02_bsrg.baseDatos.diseniador.TablaDiseniador
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false
    val tablaDiseniador = TablaDiseniador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        solicitarPermisos()
        iniciarLogicaMapa()

        val nombreDiseniador = intent.getStringExtra("nombreDiseniador")
        findViewById<TextView>(R.id.txt_diseniadorMapa).setText("Ubicación de: $nombreDiseniador")
    }


    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto,nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto,nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED && permisoCoarse == PackageManager.PERMISSION_GRANTED

        if(tienePermisos){
            permisos=true
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(nombrePermisoFine, nombrePermisoCoarse), 1
            )
        }

    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{googleMap ->
            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                moverDireccionDiseniador()
            }

        }
    }

    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto,nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto,nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED
                    && permisoCoarse == PackageManager.PERMISSION_GRANTED

            if(tienePermisos){
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun moverDireccionDiseniador(){
        val posicionDiseniador = intent.getIntExtra("posicionDiseniador", -1)
        val arregloUbicacion = tablaDiseniador.listarDiseniador()[posicionDiseniador].ubicacion.split(",")
        val ubicacion = LatLng(arregloUbicacion[0].toDouble(), arregloUbicacion[1].toDouble())

        val nombreDiseniador = intent.getStringExtra("nombreDiseniador")
        val mark = anadirMarcador(ubicacion, nombreDiseniador!!)
        mark.tag = nombreDiseniador
        moverCamaraConZoom(ubicacion)
    }

    fun moverCamaraConZoom(latLang: LatLng, zoom: Float=20f){
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLang,zoom
            )
        )
    }

    fun anadirMarcador(latLang: LatLng, title:String): Marker {
        return mapa.addMarker(
            MarkerOptions().position(latLang).title(title)
        )!!
    }



}