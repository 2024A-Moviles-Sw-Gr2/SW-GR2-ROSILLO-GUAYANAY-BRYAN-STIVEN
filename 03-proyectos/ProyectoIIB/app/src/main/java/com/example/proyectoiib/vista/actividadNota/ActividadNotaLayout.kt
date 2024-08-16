package com.example.proyectoiib.vista.actividadNota

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectoiib.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActividadNotaLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_nota_layout)

        val idAsignatura = intent.getIntExtra("id_asignatura", -1)
        val nombreAsignatura = intent.getStringExtra("nombre_asignatura")
        val profesorAsignatura = intent.getStringExtra("profesor_asignatura")
        val aulaAsignatura = intent.getStringExtra("aula_asignatura")

        findViewById<TextView>(R.id.lb_asignaturaActividadNota).setText(nombreAsignatura)
        findViewById<TextView>(R.id.lb_profesorAsignaturaActividadNota).setText(profesorAsignatura)
        findViewById<TextView>(R.id.lb_aulaAsignaturaActividadNota).setText(aulaAsignatura)



        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // Crea un adaptador para ViewPager2
        val adapter = ViewPageAdapter(this, idAsignatura)
        viewPager.adapter = adapter

        // Vincula el TabLayout con el ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Actividades"
                1 -> "Notas"
                else -> "Tab ${position + 1}"
            }
        }.attach()

    }


}