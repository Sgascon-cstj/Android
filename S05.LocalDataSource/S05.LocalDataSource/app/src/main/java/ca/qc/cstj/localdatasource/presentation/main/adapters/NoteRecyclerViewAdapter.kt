package ca.qc.cstj.localdatasource.presentation.main.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.localdatasource.domain.models.Note
import com.example.localdatasource.R
import com.example.localdatasource.databinding.ItemNoteBinding

class NoteRecyclerViewAdapter(var notes: List<Note>) : RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder>() {
    //TODO:
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
            holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private  val binding = ItemNoteBinding.bind(view)
        fun bind(note: Note){
            binding.txvTitleNote.text = note.title
            binding.txvNoteContent.text = note.content
            binding.imvColor.imageTintList = ColorStateList.valueOf(Color.parseColor((note.color)))
        }
    }

}