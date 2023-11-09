package com.example.einloggohnegoogle.viewModels
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.einloggohnegoogle.data.database.RezeptDataBase
import com.example.einloggohnegoogle.data.database.getRezeptDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.example.einloggohnegoogle.repository.SearchRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(context: Context) : ViewModel() {
    private val rezeptDatabase = getRezeptDatabase(context) // Beispiel für die Datenbankinstanz
    private val firestore = FirebaseFirestore.getInstance() // Beispiel für die Firestore-Instanz
    private val auth = FirebaseAuth.getInstance() // Beispiel für die Authentifizierungsinstanz

    private val repository = SearchRepository(rezeptDatabase, firestore, auth)

    fun loadfromFireStore() {

        Log.d("FirebaseLoad", "Attempting to load data from Firebase")

        viewModelScope.launch(Dispatchers.IO) {
            val localData = repository.getAll().value
            if (localData.isNullOrEmpty()) {
                Log.d("FirebaseLoad", "load data from Firebase")

                // Es gibt keine Daten in der Room-Datenbank, lade Daten aus Firestore.//
                repository.getRezeptAndSaveToDatabase()
            }
        }
    }
    // In dieser Live Data Variablen wird bei einer Eingabe der aktuelle Textinput gespeichert
    val inputText = MutableLiveData<String>()


}
