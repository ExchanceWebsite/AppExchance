package com.example.appexchance.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appexchance.card_layout
import com.example.appexchance.databinding.CardLayoutBinding
import com.example.appexchance.forms.models.Acomodacao

class AcomodacoesAdapter : RecyclerView.Adapter<AcomodacoesAdapter.AcomodacoesViewHolder>() {

    private val acomodacoesList = mutableListOf<Acomodacao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcomodacoesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardLayoutBinding.inflate(inflater,parent,false)

        return AcomodacoesViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return acomodacoesList.size
    }

    override fun onBindViewHolder(holder: AcomodacoesViewHolder, position: Int) {

        val acomodacao = acomodacoesList[position]

        holder.bind(acomodacao)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitedAcomodacoesList(listaAcomodacoa: List<Acomodacao>){
        this.acomodacoesList.clear()
        this.acomodacoesList.addAll(listaAcomodacoa)
        notifyDataSetChanged()
    }

    inner class AcomodacoesViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(acomodacao: Acomodacao) {

            binding.textFamilia.text = acomodacao.host.nome
            binding.textDesc.text = acomodacao.descricao

        }
    }
}