package com.example.heroestrayappretrofitmvvm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heroestrayappretrofitmvvm.databinding.HeroListItemBinding
import com.example.heroestrayappretrofitmvvm.network.model.HeroesModel
import com.example.heroestrayappretrofitmvvm.view.DetailsActivity

class HeroesAdapter(
    val context: Context
) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    var heroesList: List<HeroesModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setHeroList(heroesList: List<HeroesModel>?) {
        this.heroesList = heroesList!!
        notifyDataSetChanged()
    }

    inner class HeroViewHolder(val binding: HeroListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val views = HeroListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(views)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.apply {
            Glide.with(context)
                .load(heroesList[position].imageurl)
                .into(binding.heroImageView)

            binding.heroTitleTV.text = heroesList[position].name

            itemView.setOnClickListener {
                Intent(context, DetailsActivity::class.java).also {
                    it.putExtra("HERO_NAME", heroesList[position].name)
                    context.startActivity(it)
                }
            }
        }
    }

    override fun getItemCount(): Int = heroesList.size
}