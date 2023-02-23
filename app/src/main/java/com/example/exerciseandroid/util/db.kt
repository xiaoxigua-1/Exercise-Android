package com.example.exerciseandroid.util

import androidx.room.*

@Entity
data class Ticket(@PrimaryKey val id: Int, val name: String, val price: Int, val userName: String, val userEmail: String, val userPhone: String)

@Dao
interface TicketDao {
    @Query("SELECT * FROM Ticket")
    fun getAll(): List<Ticket>

    @Query("SELECT * FROM Ticket WHERE ID = :ticketId")
    fun findById(ticketId: Int): Ticket

    @Insert
    fun insert(vararg ticket: Ticket)

    @Delete
    fun delete(ticket: Ticket)
}

@Database(entities = [Ticket::class], version = 1)
abstract class TicketDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}