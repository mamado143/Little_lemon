package com.example.little_lemon_project

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

@Dao
interface MenuItemDao {
    @Insert
    fun insertMenuItem(menuItem: MenuItemEntity)

    @Query("SELECT * FROM menu_items")
    fun getMenuItems(): List<MenuItemEntity>
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}
