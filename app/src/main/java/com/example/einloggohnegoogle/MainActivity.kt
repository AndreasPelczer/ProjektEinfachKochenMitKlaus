package com.example.einloggohnegoogle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* Beispiel Rezepte für Vorspeisen
        val vorspeisen = mutableListOf<Rezept>()
        vorspeisen.add(

            Rezept(
                name = "Hühnersuppe",
                zutaten = "1 Suppenhuhn (ca. 1,5 kg),\n2 Möhren,\n2 Selleriestangen,\n2 Zwiebeln,\n2 Lorbeerblätter,\n5 Pfefferkörner,\nSalz nach Geschmack,\n500 g Suppennudeln oder Reis (nach Wahl),\nFrische Petersilie zum Garnieren",
                zubereitung = "1. Das Suppenhuhn waschen und in einen großen Topf geben. Mit Wasser bedecken und zum Kochen bringen.\n2. Die Möhren, Selleriestangen und Zwiebeln grob schneiden und zum Huhn hinzufügen.\n3. Die Lorbeerblätter und Pfefferkörner hinzufügen. Salz nach Geschmack zugeben.\n4. Die Suppe zum Kochen bringen, dann die Hitze reduzieren und etwa 2 Stunden köcheln lassen. Den entstehenden Schaum regelmäßig abschöpfen.\n5. Das Huhn aus der Suppe nehmen und das Fleisch von den Knochen lösen. Das Fleisch in mundgerechte Stücke schneiden und beiseite stellen.\n6. Die Suppe durch ein feines Sieb oder ein Mulltuch abseihen, um klare Brühe zu erhalten.\n7. Die klare Brühe zurück in den Topf gießen und die Suppennudeln oder Reis hinzufügen. Die Suppe gemäß den Anweisungen auf der Verpackung der Nudeln oder des Reises zubereiten.\n8. Das Hühnerfleisch wieder in die Suppe geben und kurz erwärmen.\n9. Mit frischer Petersilie garnieren und servieren."
            )
        )
        // Weitere Vorspeisen hinzufügen...

        //Beispiel Rezepte für Hauptgänge
        val hauptgaenge = listOf<Rezept>(
            Rezept(
                name = "Bohnensuppe mit Liwanzen",
                zutaten = "250 g grüne Bohnen,\n2 Kartoffeln,\n1 Zwiebel,\n2 Knoblauchzehen,\n1 EL Öl,\n1 l Gemüsebrühe,\n2 Lorbeerblätter,\n4 EL Mehl,\n2 Eier,\n100 ml Milch,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die grünen Bohnen waschen und in kleine Stücke schneiden.\n2. Die Kartoffeln schälen und in Würfel schneiden.\n3. Die Zwiebel und Knoblauchzehen fein hacken.\n4. In einem Topf das Öl erhitzen und die gehackte Zwiebel und Knoblauch darin anschwitzen.\n5. Die grünen Bohnen und Kartoffeln hinzufügen und kurz anbraten.\n6. Die Gemüsebrühe hinzugießen, die Lorbeerblätter hinzufügen und alles köcheln lassen, bis das Gemüse weich ist.\n7. In einer separaten Schüssel Mehl, Eier und Milch zu einem Teig verrühren. Mit Salz und Pfeffer würzen.\n8. Mit einem Teelöffel kleine Teigportionen in die kochende Suppe geben und als Liwanzen kochen, bis sie auf der Oberfläche schwimmen.\n9. Die Lorbeerblätter aus der Suppe entfernen und mit den Liwanzen servieren."
            ),
            Rezept(
                name = "Klare Ochsenschwanzsuppe",
                zutaten = "1 Ochsenschwanz,\n2 Zwiebeln,\n2 Karotten,\n2 Selleriestangen,\n2 Lorbeerblätter,\n5 Pfefferkörner,\nSalz nach Geschmack,\nSuppennudeln (nach Wahl),\nFrische Petersilie zum Garnieren",
                zubereitung = "1. Den Ochsenschwanz waschen und in einen großen Topf geben. Mit Wasser bedecken und zum Kochen bringen.\n2. Die Zwiebeln, Karotten und Selleriestangen grob schneiden und zum Ochsenschwanz hinzufügen.\n3. Die Lorbeerblätter und Pfefferkörner hinzufügen. Salz nach Geschmack zugeben.\n4. Die Suppe zum Kochen bringen, dann die Hitze reduzieren und etwa 4-5 Stunden köcheln lassen. Den entstehenden Schaum regelmäßig abschöpfen.\n5. Den Ochsenschwanz aus der Suppe nehmen und das Fleisch von den Knochen lösen. Das Fleisch in mundgerechte Stücke schneiden und beiseite stellen.\n6. Die Suppe durch ein feines Sieb oder ein Mulltuch abseihen, um klare Brühe zu erhalten.\n7. Die klare Brühe zurück in den Topf gießen und die Suppennudeln hinzufügen. Die Suppe gemäß den Anweisungen auf der Verpackung der Nudeln zubereiten.\n8. Das Ochsenschwanzfleisch wieder in die Suppe geben und kurz erwärmen.\n9. Mit frischer Petersilie garnieren und servieren."
            ),
            Rezept(
                name = "Leberknödelsuppe",
                zutaten = "250 g Rinderleber,\n1 Zwiebel,\n2 Knoblauchzehen,\n2 EL Butter,\n200 g altbackenes Brot,\n200 ml Milch,\n2 Eier,\n1 EL gehackte Petersilie,\nSalz und Pfeffer nach Geschmack,\n1 l Rinderbrühe",
                zubereitung = "1. Die Rinderleber waschen, trocken tupfen und in kleine Stücke schneiden.\n2. Die Zwiebel und Knoblauchzehen fein hacken.\n3. In einer Pfanne die Butter erhitzen und die gehackte Zwiebel und Knoblauch darin anschwitzen.\n4. Das altbackene Brot in kleine Würfel schneiden und in der Milch einweichen.\n5. Die eingeweichten Brotwürfel gut ausdrücken und in eine Schüssel geben.\n6. Die Rinderleber, die Zwiebel-Knoblauch-Mischung, Eier, gehackte Petersilie, Salz und Pfeffer zu den Brotwürfeln in die Schüssel geben.\n7. Alles gründlich vermengen und zu kleinen Knödeln formen.\n8. Die Rinderbrühe in einem Topf zum Kochen bringen.\n9. Die Leberknödel in die kochende Brühe geben und etwa 10-15 Minuten köcheln lassen, bis sie gar sind.\n10. Die Leberknödelsuppe in tiefen Tellern servieren."
            ),
            Rezept(
                name = "Gebundene Ochsenschwanzsuppe",
                zutaten = "1 Ochsenschwanz,\n2 Zwiebeln,\n2 Karotten,\n2 Selleriestangen,\n2 Lorbeerblätter,\n5 Pfefferkörner,\nSalz nach Geschmack,\n2 EL Mehl,\n2 EL Butter,\nSuppennudeln (nach Wahl),\nFrische Petersilie zum Garnieren",
                zubereitung = "1. Den Ochsenschwanz waschen und in einen großen Topf geben. Mit Wasser bedecken und zum Kochen bringen.\n2. Die Zwiebeln, Karotten und Selleriestangen grob schneiden und zum Ochsenschwanz hinzufügen.\n3. Die Lorbeerblätter und Pfefferkörner hinzufügen. Salz nach Geschmack zugeben.\n4. Die Suppe zum Kochen bringen, dann die Hitze reduzieren und etwa 4-5 Stunden köcheln lassen. Den entstehenden Schaum regelmäßig abschöpfen.\n5. Den Ochsenschwanz aus der Suppe nehmen und das Fleisch von den Knochen lösen. Das Fleisch in mundgerechte Stücke schneiden und beiseite stellen.\n6. Die Suppe durch ein feines Sieb oder ein Mulltuch abseihen, um klare Brühe zu erhalten.\n7. Die klare Brühe zurück in den Topf gießen und die Suppennudeln hinzufügen. Die Suppe gemäß den Anweisungen auf der Verpackung der Nudeln zubereiten.\n8. Das Ochsenschwanzfleisch wieder in die Suppe geben und kurz erwärmen.\n9. In einer separaten Pfanne die Butter erhitzen und das Mehl darin anschwitzen, bis es goldbraun ist. Die Mehlschwitze zur Suppe geben und gut verrühren, bis die Suppe gebunden ist.\n10. Mit frischer Petersilie garnieren und servieren."
            ),
            Rezept(
                name = "Rindfleischsuppe + Klößchen",
                zutaten = "250 g Rindfleisch,\n2 Zwiebeln,\n2 Karotten,\n2 Selleriestangen,\n2 Lorbeerblätter,\n5 Pfefferkörner,\nSalz nach Geschmack,\nSuppennudeln (nach Wahl),\nFrische Petersilie zum Garnieren",
                zubereitung = "1. Das Rindfleisch waschen und in einen großen Topf geben. Mit Wasser bedecken und zum Kochen bringen.\n2. Die Zwiebeln, Karotten und Selleriestangen grob schneiden und zum Rindfleisch hinzufügen.\n3. Die Lorbeerblätter und Pfefferkörner hinzufügen. Salz nach Geschmack zugeben.\n4. Die Suppe zum Kochen bringen, dann die Hitze reduzieren und etwa 2 Stunden köcheln lassen. Den entstehenden Schaum regelmäßig abschöpfen.\n5. Das Rindfleisch aus der Suppe nehmen und in mundgerechte Stücke schneiden. Beiseite stellen.\n6. Die Suppe durch ein feines Sieb oder ein Mulltuch abseihen, um klare Brühe zu erhalten.\n7. Die klare Brühe zurück in den Topf gießen und die Suppennudeln hinzufügen. Die Suppe gemäß den Anweisungen auf der Verpackung der Nudeln zubereiten.\n8. Das Rindfleisch wieder in die Suppe geben und kurz erwärmen.\n9. Mit frischer Petersilie garnieren und servieren."
            ),
            Rezept(
                name = "Bohnensuppe + Nocken",
                zutaten = "250 g grüne Bohnen,\n2 Kartoffeln,\n1 Zwiebel,\n1 Knoblauchzehe,\n2 EL Öl,\n1 l Gemüsebrühe,\n2 EL Mehl,\n2 Eier,\n100 ml Milch,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die grünen Bohnen waschen und in kleine Stücke schneiden.\n2. Die Kartoffeln schälen und in Würfel schneiden.\n3. Die Zwiebel und Knoblauchzehe fein hacken.\n4. In einem Topf das Öl erhitzen und die gehackte Zwiebel und Knoblauch darin anschwitzen.\n5. Die grünen Bohnen und Kartoffeln hinzufügen und kurz anbraten.\n6. Die Gemüsebrühe hinzugießen und alles köcheln lassen, bis das Gemüse weich ist.\n7. In einer separaten Schüssel Mehl, Eier und Milch zu einem Teig verrühren. Mit Salz und Pfeffer würzen.\n8. Mit einem Teelöffel kleine Teigportionen in die kochende Suppe geben und als Nocken kochen, bis sie auf der Oberfläche schwimmen.\n9. Die Bohnensuppe mit Nocken in tiefen Tellern servieren."
            ),
            Rezept(
                name = "Thunasandwich",
                zutaten = "2 Dosen Thunfisch in Wasser,\n4 EL Mayonnaise,\n2 EL gehackte Zwiebeln,\n1 EL gehackte Gewürzgurken,\n1 TL Senf,\nSalz und Pfeffer nach Geschmack,\n8 Scheiben Brot,\nBlätter Kopfsalat und Tomatenscheiben zum Garnieren",
                zubereitung = "1. Den Thunfisch abtropfen lassen und in eine Schüssel geben.\n2. Mayonnaise, gehackte Zwiebeln, gehackte Gewürzgurken, Senf, Salz und Pfeffer hinzufügen. Alles gut vermengen.\n3. Die Mischung gleichmäßig auf 4 Scheiben Brot verteilen.\n4. Jeweils eine zweite Scheibe Brot darauflegen, um Sandwiches zu bilden.\n5. Mit Blättern Kopfsalat und Tomatenscheiben garnieren.\n6. Die Sandwiches in der Mitte schneiden und servieren."
            ),
            Rezept(
                name = "Sandwich",
                zutaten = "8 Scheiben Brot,\n4 Scheiben Schinken,\n4 Scheiben Käse,\nButter oder Margarine nach Geschmack,\nBlätter Kopfsalat und Tomatenscheiben zum Garnieren",
                zubereitung = "1. Die Brotscheiben leicht mit Butter oder Margarine bestreichen.\n2. Auf 4 der Brotscheiben jeweils eine Scheibe Schinken und eine Scheibe Käse legen.\n3. Die restlichen Brotscheiben darauflegen, um Sandwiches zu bilden.\n4. Mit Blättern Kopfsalat und Tomatenscheiben garnieren.\n5. Die Sandwiches in der Mitte schneiden und servieren."
            ),
            Rezept(
                name = "Tramezzino",
                zutaten = "6 Scheiben Sandwichbrot,\n100 g gekochter Schinken,\n50 g Mayonnaise,\n50 g weiche Butter,\nSalz und Pfeffer nach Geschmack,\nBlätter Kopfsalat und Tomatenscheiben zum Garnieren",
                zubereitung = "1. Die Mayonnaise, weiche Butter, Salz und Pfeffer in einer Schüssel vermengen.\n2. Die Schinkenscheiben leicht mit der Butter-Mayonnaise-Mischung bestreichen.\n3. Je eine Schinkenscheibe, Blätter Kopfsalat und Tomatenscheiben auf jeweils 3 Scheiben Sandwichbrot legen.\n4. Die übrigen 3 Scheiben Brot darauflegen, um Tramezzino-Sandwiches zu bilden.\n5. Die Sandwiches diagonal in zwei Hälften schneiden und servieren."
            ),
            Rezept(
                name = "Schrimpsalat",
                zutaten = "300 g Shrimps (gekocht und geschält),\n1 Eisbergsalat,\n1 Tomate,\n1/2 Gurke,\n1 Zwiebel,\n4 EL Olivenöl,\nSaft von 1 Zitrone,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Shrimps gründlich abspülen und abtropfen lassen.\n2. Den Eisbergsalat, die Tomate, die Gurke und die Zwiebel waschen und in kleine Stücke schneiden.\n3. In einer Schüssel Olivenöl und Zitronensaft vermengen und mit Salz und Pfeffer würzen.\n4. Die Shrimps und das geschnittene Gemüse in die Schüssel geben und vorsichtig vermengen.\n5. Den Shrimps-Salat vor dem Servieren etwa 30 Minuten im Kühlschrank ziehen lassen. Gut gekühlt servieren."
            ),
            Rezept(
                name = "Muscheln in Weißwein",
                zutaten = "1 kg frische Miesmuscheln,\n2 Schalotten,\n2 Knoblauchzehen,\n2 EL Olivenöl,\n200 ml trockener Weißwein,\n1 Bund Petersilie,\nSalz und Pfeffer nach Geschmack,\nBaguette zum Servieren",
                zubereitung = "1. Die Miesmuscheln gründlich unter fließendem Wasser abspülen und eventuell vorhandene Bärte entfernen.\n2. Die Schalotten und Knoblauchzehen fein hacken.\n3. In einem großen Topf das Olivenöl erhitzen und die gehackten Schalotten und Knoblauch darin anschwitzen, bis sie glasig sind.\n4. Die Miesmuscheln hinzufügen und kurz anbraten.\n5. Mit trockenem Weißwein ablöschen und den Wein etwa 2-3 Minuten einkochen lassen.\n6. Die Petersilie grob hacken und zu den Muscheln geben. Mit Salz und Pfeffer würzen.\n7. Den Topf abdecken und die Muscheln bei mittlerer Hitze etwa 5-7 Minuten kochen, bis sie sich geöffnet haben.\n8. Geschlossene Muscheln aussortieren und nicht verwenden.\n9. Die geöffneten Muscheln in tiefen Tellern anrichten und mit Baguette servieren."
            ),
            Rezept(
                name = "Lachssalat + Pastete",
                zutaten = "200 g Räucherlachs,\n1 Eisbergsalat,\n1 Tomate,\n1/2 Gurke,\n1 Zwiebel,\n4 EL Olivenöl,\nSaft von 1 Zitrone,\nSalz und Pfeffer nach Geschmack,\nFrischkäsepastete,\nBrot zum Servieren",
                zubereitung = "1. Den Räucherlachs in kleine Stücke schneiden.\n2. Den Eisbergsalat, die Tomate, die Gurke und die Zwiebel waschen und in kleine Stücke schneiden.\n3. In einer Schüssel Olivenöl und Zitronensaft vermengen und mit Salz und Pfeffer würzen.\n4. Den Räucherlachs und das geschnittene Gemüse in die Schüssel geben und vorsichtig vermengen.\n5. Die Frischkäsepastete auf Brot oder Baguette streichen und auf einem Teller anrichten.\n6. Den Lachssalat als Beilage servieren."
            ),
            Rezept(
                name = "Lammschmorbraten",
                zutaten = "1,5 kg Lammkeule,\n2 Zwiebeln,\n4 Knoblauchzehen,\n2 Karotten,\n2 Selleriestangen,\n2 EL Olivenöl,\n500 ml Rotwein,\n500 ml Fleischbrühe,\n2 Lorbeerblätter,\n2 Zweige Rosmarin,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Den Backofen auf 160°C vorheizen.\n2. Die Lammkeule salzen und pfeffern.\n3. In einem großen Bräter das Olivenöl erhitzen und die Lammkeule von allen Seiten anbraten, bis sie eine schöne braune Farbe hat. Dann aus dem Bräter nehmen und beiseite stellen.\n4. Die Zwiebeln, den Knoblauch, die Karotten und den Sellerie im Bräter anbraten, bis sie weich sind.\n5. Den Rotwein hinzufügen und aufkochen lassen. Die Fleischbrühe, Lorbeerblätter und Rosmarin hinzufügen.\n6. Die Lammkeule zurück in den Bräter legen und alles abdecken.\n7. Den Bräter in den vorgeheizten Ofen stellen und den Lammschmorbraten ca. 2,5 Stunden schmoren lassen, bis das Fleisch zart ist. Gelegentlich mit der Soße übergießen.\n8. Vor dem Servieren die Lorbeerblätter und Rosmarinzweige entfernen. Den Lammschmorbraten in Scheiben schneiden und mit der Soße servieren."
            ),
            Rezept(
                name = "Bohnensalat",
                zutaten = "500 g grüne Bohnen,\n1 Zwiebel,\n2 EL Weißweinessig,\n3 EL Olivenöl,\n1 TL Senf,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die grünen Bohnen waschen und in kochendem Salzwasser etwa 6-8 Minuten blanchieren, bis sie bissfest sind.\n2. Die Bohnen abgießen und mit kaltem Wasser abschrecken, um den Garprozess zu stoppen.\n3. Die Zwiebel fein hacken.\n4. In einer Schüssel Weißweinessig, Olivenöl, Senf, Salz und Pfeffer vermengen.\n5. Die gehackte Zwiebel hinzufügen und gut verrühren.\n6. Die grünen Bohnen in die Schüssel geben und vorsichtig in der Vinaigrette wenden.\n7. Den Bohnensalat mindestens 30 Minuten im Kühlschrank ziehen lassen, bevor er serviert wird."
            ),
            Rezept(
                name = "Lachsalat und Lachspaste",
                zutaten = "200 g geräucherter Lachs,\n100 g Frischkäse,\n1 EL Zitronensaft,\n2 EL gehackter Dill,\nSalz und Pfeffer nach Geschmack,\nGemischter grüner Salat nach Wahl",
                zubereitung = "1. Den geräucherten Lachs in kleine Stücke schneiden.\n2. In einer Schüssel den Frischkäse, Zitronensaft und gehackten Dill vermengen. Mit Salz und Pfeffer abschmecken.\n3. Die Lachspaste auf Baguette- oder Vollkornbrot streichen.\n4. Den grünen Salat waschen und auf Teller verteilen.\n5. Die geräucherten Lachsstücke auf dem Salat anrichten und die Lachspaste dazu servieren."
            ),
            Rezept(
                name = "Muscheln in Weißweinsud",
                zutaten = "1 kg Miesmuscheln,\n2 Schalotten,\n2 Knoblauchzehen,\n1 Bund Petersilie,\n200 ml trockener Weißwein,\n100 ml Gemüsebrühe,\n2 EL Butter,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Miesmuscheln gründlich unter kaltem Wasser abspülen und geöffnete Muscheln aussortieren.\n2. Die Schalotten und Knoblauchzehen fein hacken und die Petersilie grob schneiden.\n3. In einem großen Topf die Butter erhitzen und die gehackten Schalotten und Knoblauch darin anschwitzen.\n4. Den Weißwein hinzufügen und aufkochen lassen. Dann die Gemüsebrühe und die Hälfte der Petersilie hinzufügen.\n5. Die Muscheln in den Topf geben und den Deckel schließen. Die Muscheln bei mittlerer Hitze etwa 5-7 Minuten kochen, bis sie sich geöffnet haben. Geschlossene Muscheln aussortieren.\n6. Die Muscheln in tiefe Teller geben, mit der Weißweinsauce übergießen und mit der restlichen Petersilie bestreuen. Mit frischem Brot servieren."
            ),
            Rezept(
                name = "Reisfleisch",
                zutaten = "250 g Rinderhackfleisch,\n1 Zwiebel,\n1 Knoblauchzehe,\n1 Tasse Reis,\n1 EL Tomatenmark,\n1 TL Paprikapulver,\n500 ml Rinderbrühe,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Zwiebel und den Knoblauch fein hacken.\n2. In einem großen Topf das Rinderhackfleisch anbraten, bis es braun und krümelig ist.\n3. Die gehackte Zwiebel und den Knoblauch hinzufügen und weiter braten, bis sie weich sind.\n4. Das Tomatenmark und das Paprikapulver unterrühren.\n5. Den Reis hinzufügen und alles gut vermengen.\n6. Die Rinderbrühe hinzugießen und mit Salz und Pfeffer würzen.\n7. Den Topf abdecken und das Reisfleisch bei niedriger Hitze etwa 20-25 Minuten köcheln lassen, bis der Reis gar ist und die Flüssigkeit aufgesogen wurde. Gelegentlich umrühren.\n8. Vor dem Servieren das Reisfleisch kurz ruhen lassen und dann servieren."
            ),
            Rezept(
                name = "Entenbraten mit Rotkraut",
                zutaten = "1 Ente (ca. 2,5 kg),\n1 kg Rotkraut,\n4 Äpfel,\n4 EL Zucker,\n4 EL Rotweinessig,\n2 EL Butterschmalz,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Den Backofen auf 180°C vorheizen.\n2. Die Ente waschen und trocken tupfen. Mit Salz und Pfeffer von innen und außen würzen.\n3. Die Äpfel schälen, entkernen und in Stücke schneiden.\n4. In einem Bräter das Butterschmalz erhitzen und die Ente von allen Seiten anbraten, bis sie eine goldbraune Farbe hat.\n5. Die Äpfel um die Ente legen und mit Zucker bestreuen. Den Rotweinessig darüber gießen.\n6. Den Bräter abdecken und die Ente im vorgeheizten Ofen etwa 2 Stunden braten. Gelegentlich mit dem Bratensaft übergießen.\n7. Das Rotkraut putzen und fein schneiden.\n8. In einem Topf das Rotkraut mit etwas Wasser, Salz und Zucker kochen, bis es weich ist.\n9. Die Ente aus dem Ofen nehmen und in Stücke schneiden. Mit dem Rotkraut und den Äpfeln servieren."
            ),
            Rezept(
                name = "Entenbrust mit Wirsingemüse",
                zutaten = "2 Entenbrüste,\n500 g Wirsing,\n2 Schalotten,\n2 Knoblauchzehen,\n200 ml Geflügelbrühe,\n100 ml Sahne,\n2 EL Butterschmalz,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Haut der Entenbrüste rautenförmig einschneiden und mit Salz würzen.\n2. Die Entenbrüste mit der Hautseite nach unten in einer Pfanne ohne Fett bei mittlerer Hitze anbraten, bis die Haut knusprig ist. Dann umdrehen und kurz von der anderen Seite anbraten. Aus der Pfanne nehmen und beiseite stellen.\n3. Die Schalotten und Knoblauchzehen fein hacken.\n4. Den Wirsing in Streifen schneiden.\n5. In der gleichen Pfanne das Butterschmalz erhitzen und die gehackten Schalotten und Knoblauch darin anschwitzen.\n6. Den geschnittenen Wirsing hinzufügen und kurz anbraten.\n7. Die Geflügelbrühe und Sahne hinzufügen und alles köcheln lassen, bis der Wirsing weich ist und die Sauce eingedickt ist. Mit Salz und Pfeffer abschmecken.\n8. Die Entenbrüste in die Pfanne legen und kurz erwärmen.\n9. Die Entenbrust in Scheiben schneiden und mit dem Wirsing servieren."
            ),
            Rezept(
                name = "Nudelauflauf",
                zutaten = "300 g Nudeln,\n400 g gemischtes Hackfleisch,\n1 Zwiebel,\n2 Knoblauchzehen,\n400 ml passierte Tomaten,\n200 g geriebener Gouda-Käse,\n2 EL Olivenöl,\n1 TL getrockneter Oregano,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Nudeln nach Packungsanweisung kochen, bis sie al dente sind. Anschließend abtropfen lassen und beiseite stellen.\n2. In einer Pfanne das Olivenöl erhitzen und die gewürfelte Zwiebel und gehackten Knoblauch darin anschwitzen.\n3. Das gemischte Hackfleisch hinzufügen und anbraten, bis es braun und krümelig ist.\n4. Die passierten Tomaten unterrühren und mit Oregano, Salz und Pfeffer würzen.\n5. Den Backofen auf 180°C vorheizen.\n6. Die Nudeln in eine große Auflaufform geben und die Hackfleisch-Tomatensauce darüber verteilen.\n7. Den geriebenen Gouda-Käse darüber streuen.\n8. Den Nudelauflauf im vorgeheizten Ofen etwa 20-25 Minuten backen, bis der Käse goldbraun ist. Servieren."
            ),
            Rezept(
                name = "Schoko-Apfel Kuchen",
                zutaten = "3 Äpfel,\n200 g Zartbitterschokolade,\n200 g Butter,\n200 g Zucker,\n4 Eier,\n200 g Mehl,\n1 TL Backpulver,\n1 Päckchen Vanillezucker",
                zubereitung = "1. Die Zartbitterschokolade grob hacken und mit der Butter in einem Topf schmelzen. Vom Herd nehmen und abkühlen lassen.\n2. Den Backofen auf 180°C vorheizen.\n3. Die Äpfel schälen, entkernen und in Stücke schneiden.\n4. Die Eier mit dem Zucker und Vanillezucker schaumig schlagen.\n5. Die geschmolzene Schokoladen-Butter-Mischung unter die Ei-Zucker-Masse rühren.\n6. Das Mehl mit dem Backpulver vermengen und vorsichtig unter die Schokoladenmasse heben.\n7. Die Apfelstücke ebenfalls unterheben.\n8. Den Teig in eine gefettete Backform geben.\n9. Den Schoko-Apfel Kuchen im vorgeheizten Ofen ca. 30-35 Minuten backen, bis er fest ist. Stäbchenprobe machen.\n10. Aus dem Ofen nehmen und abkühlen lassen. Vor dem Servieren in Stücke schneiden."
            ),
            Rezept(
                name = "Forelle Müllerin",
                zutaten = "4 Forellen,\n80 g Mehl,\n4 EL Butter,\nSaft einer Zitrone,\nSalz und Pfeffer nach Geschmack,\nPetersilie zum Garnieren",
                zubereitung = "1. Die Forellen gründlich waschen, trocken tupfen und mit Mehl bestäuben. Überschüssiges Mehl abklopfen.\n2. In einer Pfanne die Butter erhitzen und die Forellen von beiden Seiten goldbraun braten. Die Hitze sollte nicht zu hoch sein, damit die Forellen nicht zu dunkel werden.\n3. Die Forellen aus der Pfanne nehmen und auf einem Teller warmhalten.\n4. In der Pfanne den Zitronensaft erhitzen und mit Salz und Pfeffer würzen.\n5. Die Zitronenbutter über die Forellen gießen und mit gehackter Petersilie garnieren. Servieren."
            ),
            Rezept(
                name = "Shrimps-Salat",
                zutaten = "200 g Shrimps,\n1 Eisbergsalat,\n1 Tomate,\n1 Gurke,\n1 Zwiebel,\n4 EL Olivenöl,\nSaft einer Zitrone,\nSalz und Pfeffer nach Geschmack",
                zubereitung = "1. Die Shrimps gründlich abspülen und abtropfen lassen.\n2. Den Eisbergsalat, die Tomate, die Gurke und die Zwiebel waschen und in kleine Stücke schneiden.\n3. In einer Schüssel Olivenöl und Zitronensaft vermengen und mit Salz und Pfeffer würzen.\n4. Die Shrimps und das geschnittene Gemüse in die Schüssel geben und vorsichtig vermengen.\n5. Den Shrimps-Salat vor dem Servieren etwa 30 Minuten im Kühlschrank ziehen lassen. Gut gekühlt servieren."
            )
        )

        // Weitere Hauptgänge hinzufügen...

        // Beispiel Rezepte für Desserts
        val desserts = mutableListOf<Rezept>()
        desserts.add(
            Rezept(
                name = "Schokoladenmousse",
                zutaten = "Schokolade, Sahne, Eier, Zucker",
                zubereitung = "Schokolade schmelzen, Sahne steif schlagen. Eigelb und Zucker schaumig schlagen. Geschmolzene Schokolade unterheben, Sahne unterheben. Kühl stellen."
            )
        )
        // Weitere Desserts hinzufügen...

        // Speichern der Rezepte in die Firebase Firestore-Datenbank
        val rezepte = mutableListOf<Rezept>()
        rezepte.addAll(vorspeisen)
        rezepte.addAll(hauptgaenge)
        rezepte.addAll(desserts)

        saveRezeptToFirestore(rezepte)


    }

    private fun saveRezeptToFirestore(rezepte: List<Rezept>) {
        val db = Firebase.firestore
        val rezepteCollection = db.collection("Rezepte")

        for (rezept in rezepte) {
            rezepteCollection.add(rezept)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        TAG,
                        "Rezept erfolgreich in Firestore gespeichert mit ID: ${documentReference.id}"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Fehler beim Speichern des Rezepts in Firestore", e)
                }
        }*/

    }
}
