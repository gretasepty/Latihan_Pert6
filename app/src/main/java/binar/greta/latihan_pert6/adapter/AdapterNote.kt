package binar.greta.latihan_pert6.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.greta.latihan_pert6.room.Note

class AdapterNote(val listNote : List<Note>)
    :RecyclerView.Adapter<AdapterNote.ViewHolder>(){

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}