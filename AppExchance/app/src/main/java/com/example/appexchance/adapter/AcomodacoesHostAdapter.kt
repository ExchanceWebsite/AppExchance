package com.example.appexchance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appexchance.R
import com.example.appexchance.databinding.AdapterCardLayoutHostBinding
import com.example.appexchance.forms.models.Acomodacao

class AcomodacoesHostAdapter(
    private val list: List<Acomodacao>
) : RecyclerView.Adapter<AcomodacoesHostAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            AdapterCardLayoutHostBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: AdapterCardLayoutHostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding) {
            val pairInfo = getStringAndColor()

            textStatus.apply {
                text = context.getText(pairInfo.first)
                setTextColor(context.getColor(pairInfo.second))
            }
            textFamilia.text = "Familía ${getData().host?.nome}"
            textDesc.text = getData().descricao
        }

        private fun getStringAndColor() =
            if (getData().reservado!!) Pair(R.string.reservado, R.color.reservado)
            else Pair(R.string.livre, R.color.livre)

        private fun getData() = list[adapterPosition]
    }

}