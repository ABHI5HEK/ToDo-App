package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNotes = ArrayList<Notes>()

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteTitle = itemView.findViewById<TextView>(R.id.noteTitle)
        val delete = itemView.findViewById<ImageView>(R.id.delete)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))
        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = allNotes[position]
        holder.noteTitle.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


}
interface INotesRVAdapter{
    fun onItemClicked(notes: Notes)
}