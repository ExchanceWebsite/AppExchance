package com.example.appexchance.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appexchance.databinding.CardLayoutBinding
import com.example.appexchance.forms.models.Acomodacao

class AcomodacoesAdapter(
    private val onClick: (acomodacao: Acomodacao) -> Unit
) : RecyclerView.Adapter<AcomodacoesAdapter.AcomodacoesViewHolder>() {

    private val acomodacoesList = mutableListOf<Acomodacao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcomodacoesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardLayoutBinding.inflate(inflater, parent, false)

        return AcomodacoesViewHolder(binding)
    }

    override fun getItemCount() = acomodacoesList.size

    override fun onBindViewHolder(holder: AcomodacoesViewHolder, position: Int) {
        holder.bind()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitedAcomodacoesList(listaAcomodacoa: List<Acomodacao>) {
        this.acomodacoesList.clear()
        this.acomodacoesList.addAll(listaAcomodacoa)
        notifyDataSetChanged()
    }

    inner class AcomodacoesViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(getData())
            }
        }

        fun bind() {
            binding.textFamilia.text = getData().host.nome
            binding.textDesc.text = getData().descricao
        }

        private fun getData() = acomodacoesList[adapterPosition]
    }

}