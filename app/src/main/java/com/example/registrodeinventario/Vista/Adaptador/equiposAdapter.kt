package com.example.registrodeinventario.Vista.Adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.R

class equiposAdapter(
    private val context: Context,
    private val lista: List<clsEquipos>
) : RecyclerView.Adapter<equiposAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val imgFoto: ImageView = itemView.findViewById(R.id.imgFoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context)
            .inflate(R.layout.equipos_layout, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipo = lista[position]

        holder.txtNombre.text = equipo.nombre
        holder.txtDescripcion.text = equipo.descripcion

        Glide.with(context)
            .load(equipo.ruta_imagen)
            .into(holder.imgFoto)
    }

    override fun getItemCount(): Int = lista.size
}