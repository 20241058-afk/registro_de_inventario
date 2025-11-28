package com.example.registrodeinventario.Vista.Adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeinventario.Modelo.clsEquipos
import com.example.registrodeinventario.R

class equiposAdapter(val contexto: Context,val listaEquipo:List<clsEquipos>):RecyclerView.Adapter<equiposAdapter.equipoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): equipoViewHolder {
        //Aqui haremos el vinculocon el layout
        val view= LayoutInflater.from(parent.context).inflate(R.layout.equipos_layout,parent,false)
        return equipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: equipoViewHolder,position: Int) {
        val equipo=listaEquipo[position]

        holder.txtnombre.text=equipo.Nombre
        holder.txtdescripcion.text=equipo.Descripcion

        Glide.with(contexto)
            .load("https://inventariocomputo.grupoctic.com/img/"+holder.imgequipo)
            .into(holder.imgequipo)
    }

    override fun getItemCount(): Int {
        return listaEquipo.size
    }

    class equipoViewHolder(control: View): RecyclerView.ViewHolder(control){
        val imgequipo: ImageView =control.findViewById(R.id.imgEquipo)
        val txtnombre: TextView =control.findViewById(R.id.txtEquipo)
        val txtdescripcion: TextView =control.findViewById(R.id.txtDescripcion)

    }

}