package ca.qc.cstj.localdatasource.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Delete
import ca.qc.cstj.localdatasource.domain.models.Note
import com.example.localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.localdatasource.presentation.main.adapters.NoteRecyclerViewAdapter
import ca.qc.cstj.localdatasource.presentation.note.NoteActivity
import ca.qc.cstj.localdatasource.presentation.preference.PreferencesActivity
import com.example.localdatasource.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private lateinit var  noteRecyclerViewAdapter : NoteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: Configurer le layoutManager (grid de 2 colonnes) et l'adapter du recyclerView

        noteRecyclerViewAdapter = NoteRecyclerViewAdapter(listOf(),::onDeleteNote,::OnClickNote)
        binding.rcvNotes.layoutManager = GridLayoutManager(this, 2)
        binding.rcvNotes.adapter = noteRecyclerViewAdapter
        // TODO : Traiter le changement d'état
        viewModel.mainUiState.onEach {
            when(it){
                is MainUiState.Success ->{
                    noteRecyclerViewAdapter.notes = it.notes.asReversed()
                    noteRecyclerViewAdapter.notifyDataSetChanged()
                    val mode = if (it.userPreferences.isDarkMode){
                        AppCompatDelegate.MODE_NIGHT_YES
                    }else{
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                    AppCompatDelegate.setDefaultNightMode(mode)
                }
                is MainUiState.Empty-> Unit
            }
        }.launchIn(lifecycleScope)

        binding.fabAddNote.setOnClickListener {
            startActivity(NoteActivity.newIntent(this))
        }

        binding.fabSettings.setOnClickListener {
            startActivity(PreferencesActivity.newIntent(this))
        }

    }

    private fun OnClickNote(note: Note) {
        val dialog = AlertDialog.Builder(this).setView(R.layout.dialog)
        .setTitle(note.title)
        .setMessage(note.content)
        .setPositiveButton("OK") {_, _ ->

        }.create()
        .show()
    }

    fun onDeleteNote(note: Note){
        viewModel.deleteNote(note)

    }

}