Diese README bietet einen Überblick über den Code und die Struktur der EinloggOhneGoogle Android-Anwendung, 
die das MVVM (Model-View-ViewModel) Architekturmuster verwendet. MVVM wird eingesetzt, 
um die Benutzeroberfläche (View) von der Geschäftslogik (ViewModel) zu trennen und 
die Trennung der Datenrepräsentation (Model) zu ermöglichen.

Code-Struktur
Der Code ist in verschiedene Fragmente und ViewModel-Klassen unterteilt, 
um die Benutzeroberfläche und die Dateninteraktionen zu verwalten. 
Dabei wird das MVVM-Muster verwendet.

1. DataFragment:
   (com.example.einloggohnegoogle.ui.DataFragment)
Verantwortlich für die Anzeige einer Liste von Rezepten, die aus Firebase abgerufen wurden.
Implementiert das MVVM-Muster durch die Verwendung von ViewModels für die Geschäftslogik
und LiveData für die Kommunikation zwischen View und ViewModel.

3. VideoFragment:
  (com.example.einloggohnegoogle.ui.VideoFragment)
Zeigt YouTube-Video-Playlists zu verschiedenen Rezeptkategorien an.
Implementiert das MVVM-Muster, um die Interaktionen mit der Benutzeroberfläche zu steuern und die Darstellung von Daten zu trennen.

6. RezeptDetailFragment:
   (com.example.einloggohnegoogle.ui.RezeptDetailFragment)
Zeigt detaillierte Informationen zu einem bestimmten Rezept an.
Implementiert das MVVM-Muster durch die Verwendung von ViewModel für die Geschäftslogik, um die Ansicht zu aktualisieren.

7. NeuesRezeptFragment:
   (com.example.einloggohnegoogle.ui.NeuesRezeptFragment)
Ermöglicht Benutzern das Erstellen eines neuen Rezepts durch Bereitstellung relevanter Details.
Implementiert das MVVM-Muster zur Trennung von Benutzeroberfläche und Geschäftslogik und zur Verwaltung von Benutzereingaben.

Schlüsselfunktionalitäten:
Firebase-Integration: 

Die Anwendung verwendet Firebase zur Speicherung und zum Abrufen von Rezeptdaten.
RecyclerView: Verwendet RecyclerView zur Anzeige einer Liste von Rezepten in verschiedenen Fragmenten.
YouTube-Integration: Integriert YouTube-Video-Playlists, damit Benutzer verwandte Kochvideos anzeigen können.
Info API-Integration: Implementiert eine Info API, um zusätzliche Informationen zu Rezepten abzurufen.
Navigation: Nutzt das Navigationskomponente, um zwischen verschiedenen Fragmenten in der Anwendung zu navigieren.

Verwendung:

Die Anwendung ist darauf ausgelegt, es Benutzern zu ermöglichen, Rezepte anzuzeigen und zu verwalten. 
Benutzer können durch verschiedene Fragmente navigieren, 
um Rezepte anzuzeigen, neue zu erstellen, 
YouTube-Videos anzuzeigen und mehr.

Abhängigkeiten
Der Code ist abhängig von den folgenden externen Bibliotheken:

Firebase: Für die Authentifizierung und Echtzeit-Datenbankfunktionalität.
AndroidX: Für UI-Komponenten, ViewModel und LiveData.
