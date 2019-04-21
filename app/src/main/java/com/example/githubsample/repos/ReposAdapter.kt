package com.example.githubsample.repos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.githubsample.R
import com.example.githubsample.network.models.Repo
import kotlinx.android.synthetic.main.repo_list_item.view.*

class ReposAdapter(
    var items: MutableList<Repo>,
    val context: Context
) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}

class RepoViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: Repo) {
        tvName.text = item.name

        Glide
            .with(avatarImageView)
            .load(item.owner.avatarUrl)
            .thumbnail(0.1f)
            .into(avatarImageView);
    }

    private val tvName: TextView = view.repo_name_text_view
    private val avatarImageView = view.avatar_image_view


}