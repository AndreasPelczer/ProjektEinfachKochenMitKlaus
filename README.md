Diese README bietet einen Überblick über den Code und die Struktur der 
# Einfach kochen mit KLaus - Android-Anwendung, 
Die App "Einfach Kochen mit Klaus" ist dein ultimativer Begleiter in der Welt des Kochens. 
In Zusammenarbeit mit dem bekannten YouTube-Koch Klaus bietet die App eine Fülle von Rezepten, 
inspirierenden Kochvideos und eine Plattform, um deine eigenen Kochkreationen zu teilen.

"Einfach Kochen mit Klaus" bringt die Leidenschaft für gutes Essen direkt auf dein Smartphone. 
Entdecke alte Gerichte neu, finde neue Rezeptideen, erhalte Tipps und Tricks direkt von Klaus und werde Teil einer 
Gemeinschaft von Gleichgesinnten, die ihre Liebe zum Kochen teilen.

## Inhaltsverzeichnis
- [Überblick](#Code-Struktur)
- [Funktionen](#Schlüsselfunktionalitäten)
- [Technologie-Stack](#technologie-stack)
- [Beispielverwendung](#beispielverwendung)
- [Entwicklungsfahrplan](#entwicklungsfahrplan)


# Überblick

die das MVVM (Model-View-ViewModel) Architekturmuster verwendet. 
MVVM wird eingesetzt, um die Benutzeroberfläche (View) von der Geschäftslogik (ViewModel) zu trennen und 
die Trennung der Datenrepräsentation (Model) zu ermöglichen.

<img width="1081" alt="Bildschirmfoto 2023-11-15 um 09 37 02" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/0120282a-09a8-4edd-8089-8500d9d6a3a8">

# Code-Struktur
Der Code ist in verschiedene Fragmente und ViewModel-Klassen unterteilt, 
um die Benutzeroberfläche und die Dateninteraktionen zu verwalten. 
Dabei wird das MVVM-Muster verwendet.

![Dein Abschnittstext](https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/59086cc2-bfbe-452c-9aa8-1479429509b1)

DataFragment:

Verantwortlich für die Anzeige einer Liste von Rezepten, die aus Firebase abgerufen wurden.
Implementiert das MVVM-Muster durch die Verwendung von ViewModels für die Geschäftslogik
und LiveData für die Kommunikation zwischen View und ViewModel.

VideoFragment:

Zeigt YouTube-Video-Playlists zu verschiedenen Rezeptkategorien an.
Implementiert das MVVM-Muster, um die Interaktionen mit der Benutzeroberfläche zu steuern und die Darstellung von Daten zu trennen.

RezeptDetailFragment:

Zeigt detaillierte Informationen zu einem bestimmten Rezept an.
Implementiert das MVVM-Muster durch die Verwendung von ViewModel für die Geschäftslogik, um die Ansicht zu aktualisieren.

NeuesRezeptFragment:

Ermöglicht Benutzern das Erstellen eines neuen Rezepts durch Bereitstellung relevanter Details.
Implementiert das MVVM-Muster zur Trennung von Benutzeroberfläche und Geschäftslogik und zur Verwaltung von Benutzereingaben.

# Schlüsselfunktionalitäten:

## Firebase-Integration: 
<img width="690" alt="Bildschirmfoto 2023-11-15 um 10 08 17" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/114c7c67-9e2c-4e7d-af00-d0d8a9b877b5">

Die Anwendung verwendet Firebase zur Speicherung und zum Abrufen von Rezeptdaten.

## RecyclerView: 
<img width="636" alt="Bildschirmfoto 2023-11-15 um 10 42 16" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/7c923be5-1c84-42b1-b5e6-97fbe0de65f0">
Verwendet RecyclerViews zur Anzeige von Listen von Rezepten, Videos und Suchergebnissen in verschiedenen Fragmenten.

## YouTube-Integration: 
Integriert YouTube-Video-Playlists, damit Benutzer verwandte Kochvideos anzeigen können.

## Info API-Integration:

Implementiert eine Info API, um zusätzliche Informationen abzurufen.


# Technologie-Stack
 <img width="1203" alt="Bildschirmfoto 2023-11-15 um 11 21 52" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/c03d74d3-813e-415a-bea5-e418d9ae3ecf">


# Verwendung:
https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/fdf148c0-d85c-47f4-a449-40c54f3ba673

Die Anwendung ist darauf ausgelegt, es Benutzern zu ermöglichen, 
Rezepte anzuzeigen und zu verwalten. 
Benutzer können durch verschiedene Fragmente navigieren, 
um Rezepte anzuzeigen, neue zu erstellen, 
YouTube-Videos anzuzeigen und mehr.

# Abhängigkeiten
Der Code ist abhängig von den folgenden externen Bibliotheken:

## Firebase: 
<img width="1445" alt="Bildschirmfoto 2023-11-15 um 11 01 36" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/39af96fc-08eb-4c9b-879e-c97ab916701e">
Für die Authentifizierung und Echtzeit-Datenbankfunktionalität.

## AndroidX: 
![android-jetpack-architecture-components -squaremedium](https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/5488b241-4980-491d-8f5f-1ab9694b329d)
Für UI-Komponenten, ViewModel und LiveData.

## Room: 
<img width="1679" alt="Bildschirmfoto 2023-11-15 um 10 55 19" src="https://github.com/AndreasPelczer/ProjektEinfachKochenMitKlaus/assets/134264285/3cfa9bfa-eb77-403f-8c61-13f29d34a5c9">
Für die Lokalen Daten





# Roadmap für "Einfach Kochen mit Klaus"

## Optimierte Suchfunktion:
damit Benutzer Rezepte noch einfacher nach Zutaten, Schwierigkeitsgrad oder Zubereitungszeit filtern können.

## Personalisierte Benutzerprofile:
Möglichkeit für Benutzer, persönliche Profile zu erstellen und ihre Lieblingsrezepte zu speichern.
Kochkurse oder Live-Events:

## Community-Funktionen:
Funktionen für Kochkurse oder Live-Events um mit Klaus zu integrieren, um die Interaktion und das Engagement zu steigern.

## Benutzern ermöglichen: 
sich untereinander auszutauschen, Rezepte zu teilen und Kochtipps zu geben.
Einkaufsliste integrieren:

## Implementieren einer Funktion, 
mit der Benutzer Zutaten direkt zu einer Einkaufsliste hinzufügen können.
Integration von Ernährungsinformationen:

## Nährwertinformationen zu den Rezepten hinzufügen,
um Benutzern bei bestimmten Ernährungsbedürfnissen zu helfen.

## Integration von Feedback und Bewertungen:
Ermögliche Benutzern, Rezepte zu bewerten und Feedback zu hinterlassen, um die Qualität und Popularität der Rezepte zu steigern.
