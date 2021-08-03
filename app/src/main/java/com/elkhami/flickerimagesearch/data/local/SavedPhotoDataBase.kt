package com.elkhami.flickerimagesearch.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by A.Elkhami on 07,July,2021
 */

@Database(
    entities = [SavedPhoto::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)
abstract class SavedPhotoDataBase : RoomDatabase() {
    abstract fun savedPhotoDAO(): SavedPhotoDAO


}