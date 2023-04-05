package com.umair.noteapp.viewModel


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umair.noteapp.data.NotesDataSource
import com.umair.noteapp.model.Note
import com.umair.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())

    // var noteList = mutableStateListOf<Note>()
    val noteList = _noteList.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect() { listOfAllNotes ->

                    if (listOfAllNotes.isEmpty()) {
                        Log.d("TAG", ": Empty: Empty List")
                    } else {

                        _noteList.value = listOfAllNotes
                    }
                }

        }
//        noteList.addAll(NotesDataSource().loadNotes())
    }

     fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

     fun removeNote(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.deleteNote(note) }

     fun upDateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }


}