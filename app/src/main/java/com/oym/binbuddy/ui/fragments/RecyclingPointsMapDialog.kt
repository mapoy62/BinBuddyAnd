package com.oym.binbuddy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oym.binbuddy.R
import com.oym.binbuddy.data.remote.model.RecyclingPoint

class RecyclingPointsMapDialog(private val recyclingPoints: List<RecyclingPoint>) :
    BottomSheetDialogFragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recycling_points_map, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: com.google.android.gms.maps.GoogleMap) {
        // Inicializa el mapa
        MapsInitializer.initialize(requireContext())

        // Agrega pines para cada punto de reciclaje
        recyclingPoints.forEach { point ->
            val markerOptions = MarkerOptions()
                .position(LatLng(point.latitude, point.longitude))
                .title(point.name)
                .snippet("${point.address}\nHorario: ${point.hours}")
            googleMap.addMarker(markerOptions)
        }

        // Centra el mapa en el primer punto
        if (recyclingPoints.isNotEmpty()) {
            val firstPoint = recyclingPoints[0]
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(firstPoint.latitude, firstPoint.longitude),
                    12f
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }
}
