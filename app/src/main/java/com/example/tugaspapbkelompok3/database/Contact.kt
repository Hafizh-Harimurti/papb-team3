package com.example.tugaspapbkelompok3.database
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
    fun getAllContacts(): List<Contact>
    @Query("SELECT * FROM contacts WHERE contactId = :input")
    fun getContactById(input: Int): Contact
    @Insert
    fun newContacts(contact: Contact): Long
    @Update
    fun updateContact(contact: Contact)
    @Query("DELETE FROM contacts WHERE contactId = :contactId")
    fun deleteContact(contactId: Int)
}

