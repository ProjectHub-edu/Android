package com.project.hub.feature.profile.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.hub.R
import com.project.hub.databinding.ProfileProjectRvItemBinding
import com.project.hub.feature.profile.data.model.ProjectModel

class ProfileProjectsAdapter(
    private val list: List<ProjectModel>
): RecyclerView.Adapter<ProfileProjectsAdapter.ProjectsViewHolder>() {

    class ProjectsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val role = itemView.findViewById<TextView>(R.id.role_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ProfileProjectRvItemBinding.inflate(inflater, parent, false)

        return ProjectsViewHolder(view.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.title
        holder.role.text = item.role_description
    }

}