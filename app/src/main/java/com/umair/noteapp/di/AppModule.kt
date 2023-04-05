package com.umair.noteapp.di

import android.content.Context
import androidx.room.Room
import com.umair.noteapp.model.NoteDataBase
import com.umair.noteapp.model.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDataBase: NoteDataBase): NoteDataBaseDao = noteDataBase.noteDao()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context):NoteDataBase
    = Room.databaseBuilder(context,NoteDataBase::class.java,
    name = "notes_db"
        ).fallbackToDestructiveMigration().build()

}