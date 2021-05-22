package com.example.friendsr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var data: List<ViewList>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var listener: PeopleListener = object : PeopleListener {}

    fun setOnFriendListener(listener: PeopleListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updatePerson(friend: ViewList) {
        val index = data.indexOfFirst { it.name == friend.name }
        val newList = data.toMutableList()
        newList[index] = friend
        data = newList.toList()
        notifyItemChanged(index)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo = view.findViewById<ImageView>(R.id.foto3)
        private val score = view.findViewById<RatingBar>(R.id.calificacion2)
        private val name = view.findViewById<TextView>(R.id.nombre)

        fun bind(friend: ViewList) {
            score.rating = friend.score
            score.isEnabled = false
            name.text = friend.name
            photo.setImageResource(friend.photo)
            /*{
                transformations(CircleCropTransformation())
                scale(Scale.FILL)
            }*/
            itemView.setOnClickListener {
                listener.onFriendClick(data[adapterPosition])
            }
        }

    }

    interface PeopleListener {
        fun onFriendClick(friend: ViewList) {}
    }

}


