package com.umair.noteapp.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDataBaseDao {

    @Query("SELECT * from notes_tbl")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun getNoteById(id:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("Delete from notes_tbl")
   suspend fun deleteAll()

    @Delete
    fun deleteNote(note: Note)
}
