package com.idn.doadzikir.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.doadzikir.Model.Artikel
import com.idn.doadzikir.Utills.OnItemCallBack
import com.idn.doadzikir.databinding.ItemArtikelBinding

class ArtikelAdapter : RecyclerView.Adapter<ArtikelAdapter.ArticleViewHolder> () {

    private var listArtikel = ArrayList<Artikel>()
    private var onItemCallback : OnItemCallBack? = null

    fun setData(list: List<Artikel>){
        listArtikel.clear()
        listArtikel.addAll(list)
    }

    fun setOnItemClickCallback(onItemCallback: OnItemCallBack) {
        this.onItemCallback = onItemCallback
    }

    inner class ArticleViewHolder(val view : ItemArtikelBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder (
      ItemArtikelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


    override fun getItemCount() = listArtikel.size
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val data = listArtikel[position]
        holder.view.imgArtikel.setImageResource(data.imageArtikel)
        holder.itemView.setOnClickListener { onItemCallback?.onItemClicked(data)
        } }
}