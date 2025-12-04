package com.example.registrodeinventario.Vista.Adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.registrodeinventario.Modelo.clsHistorial
import com.example.registrodeinventario.R

class historialAdapter(
    private val context: Context,
    private val lista: List<clsHistorial>
) : RecyclerView.Adapter<historialAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
        val imgEquipo: ImageView = itemView.findViewById(R.id.imgFoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context)
            .inflate(R.layout.equipos_layout, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        holder.txtNombre.text = item.nombre_equipo
        holder.txtEstado.text = item.estado_equipo

        Glide.with(context)
            .load(item.imagen_equipo)
            .into(holder.imgEquipo)
    }

    override fun getItemCount(): Int = lista.size
}
