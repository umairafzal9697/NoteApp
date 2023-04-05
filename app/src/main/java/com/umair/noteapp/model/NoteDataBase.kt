package com.umair.noteapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.umair.noteapp.util.DataConverter
import com.umair.noteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)

@TypeConverters(DataConverter::class,UUIDConverter::class)
 abstract class NoteDataBase :RoomDatabase() {
     abstract fun noteDao() : NoteDataBaseDao



}