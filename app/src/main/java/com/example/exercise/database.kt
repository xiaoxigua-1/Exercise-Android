package com.example.exercise

import android.content.Context
import androidx.room.*
import androidx.room.Database

@Entity(tableName = "test")
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "username")
    val username: String
)

@Dao
interface TestDAO {
    @Query("SELECT * FROM TEST")
    fun selectAllTestTable(): List<Test>

    @Insert
    fun insert(test: Test)

    @Delete
    fun delete(data: Test)
}

@Database(version = 1, entities = [Test::class], exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun dao(): TestDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun createDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "dbs")
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}