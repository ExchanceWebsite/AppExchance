package com.example.appexchance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appexchance.databinding.AdapterCardLayoutReservaBinding
import com.example.appexchance.forms.models.Reserva

class ReservasAdapter(
    val list: List<Reserva>
) : RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCardLayoutReservaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: AdapterCardLayoutReservaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding) {
            textFamilia.text = "Familía ${getData().host.nome}"
            textDesc.text = getData().acomodacao.descricao
            textLocal.text =
                "${getData().acomodacao.localidade?.pais}, ${getData().acomodacao.localidade?.cidade}"
        }

        private fun getData() = list[adapterPosition]
    }
}