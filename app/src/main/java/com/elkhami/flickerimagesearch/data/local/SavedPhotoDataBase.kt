package com.elkhami.flickerimagesearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by A.Elkhami on 07,July,2021
 */

@Database(
    entities = [SavedPhoto::class],
    version = 1
)
abstract class SavedPhotoDataBase: RoomDatabase() {
    abstract fun savedPhotoDAO(): SavedPhotoDAO
}