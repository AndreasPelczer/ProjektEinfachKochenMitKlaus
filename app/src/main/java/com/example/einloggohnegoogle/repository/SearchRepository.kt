package com.example.einloggohnegoogle.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.einloggohnegoogle.data.database.RezeptDataBase
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class SearchRepository (

    private val rezeptDataBase: RezeptDataBase,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth

) { private var lastKnownDocumentCount: Int = 0

    private val _firestoreRezept = MutableLiveData<List<Rezept>>()

    val firestoreRezept: LiveData<List<Rezept>> = _firestoreRezept
    fun getAll(): LiveData<List<Rezept>> {
        return rezeptDataBase.dao.getAllItems()
    }
    suspend fun getRezeptAndSaveToDatabase() {
        try {
            val firestoreData = fetchFirestoreData()
            Log.e(ContentValues.TAG, "FirestoreDatato: $firestoreData")

            _firestoreRezept.postValue(firestoreData)
            saveRezeptToDatabase(firestoreData)


        } catch (e: Exception) {
            //  Log.e(TAG, "Error loading Data: $e")
        }
    }

    private suspend fun fetchFirestoreData(): List<Rezept> {
        val firestoreData = mutableListOf<Rezept>()
        try {
            val firestoreCollection = firestore.collection("Rezepte")
            val querySnapshot: QuerySnapshot = firestoreCollection.get().await()

            // Überprüfen, ob es neue Daten gibt
            val currentDocumentCount = querySnapshot.size()
            if (currentDocumentCount > lastKnownDocumentCount) {
                for (document in querySnapshot.documents) {
                    val rezeptId = document.id
                    val userId = document.getString("userId")?: ""
                    val name = document.getString("name") ?: ""
                    val zutaten = document.getString("zutaten") ?: ""
                    val zubereitung = document.getString("zubereitung") ?: ""
                    val ersteller = document.getString("ersteller")?: ""

                    val rezept = Rezept(
                        id = rezeptId,
                        userId=userId.toString(),
                        name = name,
                        zutaten = zutaten,
                        zubereitung = zubereitung,
                        ersteller = ersteller
                    )
                    Log.e(ContentValues.TAG, "Fetched data: $rezept")
                    firestoreData.add(rezept)
                }
                // Aktualisiere die letzte bekannte Anzahl von Dokumenten
                lastKnownDocumentCount = currentDocumentCount
            } else {
                Log.e(ContentValues.TAG, "No new data available.")
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error fetching Firestore data: ${e.message}")
        }
        return firestoreData
    }

    private fun saveRezeptToDatabase(apiData: List<Rezept>) {
        try {
            // Lösche alle vorhandenen Rezepte in der Room-Datenbank
            rezeptDataBase.dao.deleteAllRezepte()

            // Füge die neuen Rezepte aus Firebase in die Room-Datenbank ein
            for (itemData in apiData) {
                rezeptDataBase.dao.insertRezept(itemData)
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error inserting data from API into database: $e")
        }
    }

}


