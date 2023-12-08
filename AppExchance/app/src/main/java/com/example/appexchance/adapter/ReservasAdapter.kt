package com.example.appexchance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appexchance.databinding.AdapterCardLayoutReservaBinding
import com.example.appexchance.forms.models.Reserva

class ReservasAdapter(
    private val list: List<Reserva>,
    private val onClick: (id: Int, position: Int) -> Unit
) : RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {

    private var listReservas = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCardLayoutReservaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = listReservas.size

    inner class ViewHolder(private val binding: AdapterCardLayoutReservaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnCancelar.setOnClickListener {
                onClick(getData().idReserva, adapterPosition)
            }
        }

        fun bind() = with(binding) {
            textFamilia.text = "Familía ${getData().host.nome}"
            textDesc.text = getData().acomodacao.descricao
            textLocal.text =
                "${getData().acomodacao.localidade?.pais}, ${getData().acomodacao.localidade?.cidade}"
        }

        private fun getData() = listReservas[adapterPosition]
    }

    fun removeItem(position: Int) {
        val newList = listReservas.toMutableList()
        newList.removeAt(position)
        listReservas = newList

        notifyItemRemoved(position)
    }
}