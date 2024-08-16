package com.example.proyectoiib.vista.actividadNota

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(activity: FragmentActivity, var id_asignatura:Int) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentoActividades.newInstance(id_asignatura)
            1 -> FragmentoNotas()
            else -> Fragment()
        }
    }

}