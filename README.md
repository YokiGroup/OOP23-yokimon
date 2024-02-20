[OOP-23] Yokimon

### Email dei Componenti:
- luca.palazzini3@studio.unibo.it
- marilia.merendi@studio.unibo.it
- giovanni.paone@studio.unibo.it
- federico.deleonardis@studio.unibo.it

### Obiettivo del gruppo:
Consiste nel realizzare un videogame ispirato al mondo Pokèmon dell'era 2D, in una versione rivisitata in stile rogue-like, completo di una mappa da esplorare e nemici da sfidare.

### Lista delle funzionalità raggiunte:
- Un main character, che possiede necessariamente uno Yokimon dall’inizio della partita.
- Mappa "a tile" procedurale (un tile lo consideriamo schermata), suddivisa in una zona centrale invariabile (la città) e una zona circostante (la natura) generata a ogni partita. Il movimento dei personaggi resta comunque libero e non su griglia.
- Movimento automatico dei nemici presenti sulla mappa: essi, quando il protagonista si trova in un determinato raggio, dovranno avvicinarsi per istigare il combattimento.
- Meccanica di combattimento a turni: gli Yokimon combattono tramite mosse.
- Barra della vita espressa in life points e meccanica di game-over che riporta al menu principale una volta esauriti i suddetti punti (in quanto rogue-like, se fallita la partita il giocatore deve ripartire da capo dentro una nuova mappa).
- Meccanica di level-up degli Yokimon
- Possibilità di acquisire più Yokimon
- Sviluppo della meccanica di evoluzione degli Yokimon

### Lista di funzionalità opzionali non raggiunte:
- Implementazione di un boss finale
- Sistema di salvataggio/caricamento
- Implementazione di monete e di uno shop nella città dove poter acquistare power-up o simili (gli acquisti all’interno dello shop sono persistenti, non si resettano a ogni game over).

### Challenge previste:
- Programmazione dell'AI dei nemici, sia per il movimento sulla mappa sia per le dinamiche di combattimento.
- Proceduralità del terreno per la zona randomizzata.
- L'estrema estendibilità degli oggetti come Yokimon, attacchi e il meccanismo dei punti (life points, attack points, etc.) richiede prestare particolare attenzione al design.

### Suddivisione di massima del lavoro fra i partecipanti:
- Palazzini: generazione mappa.
- Merendi: meccanica di combattimento a turni.
- Paone: gestione file, sistemi di eventi (relativi ai sottomoduli), game loop.
- De Leonardis: player, Yokimon, enemy.