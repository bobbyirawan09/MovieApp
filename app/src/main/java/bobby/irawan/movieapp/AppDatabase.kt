package bobby.irawan.movieapp

import androidx.room.Database
import androidx.room.RoomDatabase
import bobby.irawan.movieapp.data.favorite.FavoriteDao
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
@Database(entities = arrayOf(FavoriteEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}