package com.umair.noteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.umair.noteapp.data.NotesDataSource
import com.umair.noteapp.model.Note
import com.umair.noteapp.screen.NoteScreen
import com.umair.noteapp.ui.theme.NoteAppTheme
import com.umair.noteapp.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel : NoteViewModel by viewModels()
                        NoteApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NoteApp(viewModel: NoteViewModel){
val noteList = viewModel.noteList.collectAsState().value

//        val noteList = viewModel.getAllNotes()
    NoteScreen(notes = noteList
        ,
        onAddNote = {
            viewModel.addNote(it)

        },
        onRemoveNote = {
           viewModel.removeNote(it)

        })
}