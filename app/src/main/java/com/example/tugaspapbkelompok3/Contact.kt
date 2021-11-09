package com.example.tugaspapbkelompok3

import androidx.room.*

@Entity(tableName = "contacts")
data class Contact(

    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "description") var description: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "contactId") var contactId: Int = 0
    )

@Dao
interface ContactDAO{
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Array<Contact>
    @Query("SELECT * FROM contacts WHERE contactId = :input")
    fun getContactById(input: Int): Contact
    @Insert
    fun newContacts(vararg contact: Contact)
    @Update
    fun updateContact(vararg contact: Contact)
    @Delete
    fun deleteContact(vararg contact: Contact)
}

@Database(entities = [Contact::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun ContactDAO(): ContactDAO
}
