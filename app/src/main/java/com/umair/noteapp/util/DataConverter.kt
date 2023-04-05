package com.umair.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

class DataConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date):Long{

        return date.time

    }

    @TypeConverter
    fun dateFromTimeStamp(timeStamp:Long):Date?{
        return Date(timeStamp)


    }
}