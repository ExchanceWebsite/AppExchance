package com.example.appexchance.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appexchance.databinding.AdapterImageCityBinding
import com.example.appexchance.forms.models.Pais

@Suppress("UNCHECKED_CAST")
class CountryAdapter(
    private val list: List<Pais>,
    private val onClick: (country: String) -> Unit
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private var driverFiltered = list

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            AdapterImageCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = driverFiltered.size

    inner class ViewHolder(private val binding: AdapterImageCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(getData().nome)
            }
        }

        fun bind() = with(binding) {
            Glide.with(context)
                .load(getData().imagem)
                .into(ivCountry)
            tvNameCountry.text = getData().nome
        }

        private fun getData() = driverFiltered[adapterPosition]
    }


    private val driverFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val text = constraint?.toString().orEmpty()
            val resultList = ArrayList<Pais>()

            if (text.isEmpty()) resultList.addAll(list)
            else list.forEach {
                if (it.nome.contains(text, true)) resultList.add(it)
            }

            return FilterResults().apply {
                values = resultList
                count = resultList.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            setNewData(
                if (results?.values == null) emptyList()
                else results.values as List<Pais>
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(data: List<Pais>) {
        driverFiltered = data.orEmpty()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return driverFilter
    }
}