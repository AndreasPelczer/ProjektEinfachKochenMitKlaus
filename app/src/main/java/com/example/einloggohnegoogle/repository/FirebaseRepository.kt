package com.example.einloggohnegoogle.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.data.database.RezeptDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID


class FirebaseRepository(

    private val rezeptDataBase: RezeptDataBase,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth

) {
    private var lastKnownDocumentCount: Int = 0

    private val _firestoreRezept = MutableLiveData<List<Rezept>>()

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    val firestoreRezept: LiveData<List<Rezept>> = _firestoreRezept



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
                    Log.e(TAG, "Fetched data: $rezept")
                    firestoreData.add(rezept)
                }
                // Aktualisiere die letzte bekannte Anzahl von Dokumenten
                lastKnownDocumentCount = currentDocumentCount
            } else {
                Log.e(TAG, "No new data available.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching Firestore data: ${e.message}")
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
            Log.e(TAG, "Error inserting data from API into database: $e")
        }
    }



    suspend fun getRezeptAndSaveToDatabase() {
        try {
            val firestoreData = fetchFirestoreData()
            Log.e(TAG, "FirestoreDatato: $firestoreData")

            _firestoreRezept.postValue(firestoreData)
            saveRezeptToDatabase(firestoreData)


        } catch (e: Exception) {
            //  Log.e(TAG, "Error loading Data: $e")
        }
    }

    fun getAll(): LiveData<List<Rezept>> {
        return rezeptDataBase.dao.getAllItems()
    }



    suspend fun saveRezeptToFirestore(
        name: String,
        zutaten: String,
        zubereitung: String,
        videoupload: String,
        userId: String,
        ersteller:String
    ) {
        try {
            val rezeptId = UUID.randomUUID().toString()
            val localRezeptId = savePostAndGetIdLocally(rezeptId, name, zutaten, zubereitung,videoupload,userId,ersteller)

            // Daten für das Rezept
            val rezeptInfo = Rezept(

                id = rezeptId,
                name = name,
                zubereitung = zubereitung,
                zutaten = zutaten,
                videoupload = videoupload,
                userId = userId,
                ersteller = ersteller
            )
            Log.d(
                "NeuesRezeptFragment",
                "Rezept erfolgreich in die Firebase-Datenbank gespeichert.${rezeptInfo}"
            )

            // Firebase-Referenz zur Sammlung "Rezepte" und Dokument mit eindeutiger ID
            firestore.collection("Rezepte").document(rezeptId).set(rezeptInfo)
            return localRezeptId

        } catch (e: Exception) {

            Log.e(TAG, "Error posting data: $e")

        }
    }


    private suspend fun savePostAndGetIdLocally(
        rezeptId: String,
        name: String,
        zutaten: String,
        zubereitung: String,
        videoupload: String,
        userId: String,
        ersteller: String


        ){
        val localRezept = Rezept(
            id = rezeptId,
            name = name,
            zubereitung = zubereitung,
            zutaten = zutaten,
            videoupload = videoupload,
            userId = userId,
            ersteller = ersteller
        )
        return savePostAndGetId(localRezept)
    }


    private suspend fun savePostAndGetId(localRezept: Rezept) {
        return withContext(Dispatchers.IO) {
            rezeptDataBase.dao.insertAndGetId(localRezept)
        }
    }
    fun getRezeptDetail(id: String): Rezept {
        return rezeptDataBase.dao.getItemById(id)
    }
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("rezepte")
    fun updateRezeptDetailsInFirestore(rezeptId: String, updatedName: String, updatedZutaten: String, updatedZubereitung:String) {
        val postRef = firestore.collection("Rezepte").document(rezeptId)
        postRef.update("name", updatedName, "zutaten",updatedZutaten,"zubereitung",updatedZubereitung)
    }




}


