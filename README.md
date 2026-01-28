# Viikkotehtävä 3 – MVVM-arkkitehtuuri käytännössä

Tämä projekti on Viikkotehtävä 3, joka on laajennus Viikkotehtävä 2 -tehtävästä.  
Tehtävässä käytetään MVVM-arkkitehtuuria Jetpack Compose -sovelluksessa.

## Mitä sovellus tekee
- käyttäjä voi lisätä tehtävän
- käyttäjä voi poistaa tehtävän
- tehtävät voi järjestää päivämäärän mukaan
- tehtäviä voi näyttää erikseen (Done / Not done)
- tehtävän voi merkitä tehdyksi valintaruudulla
- tehtävää voi muokata dialogissa (otsikko, kuvaus, deadline ja prioriteetti)

## MVVM – miksi se on hyödyllinen Compose-sovelluksissa

MVVM-arkkitehtuurin tarkoitus on selkeyttää sovellusta ja tehdä koodista
helpompi ymmärtää, testata ja ylläpitää.

### Model
Model sisältää sovelluksen datan.  
Tässä projektissa Model on `Task`-luokka, joka sisältää tehtävän tiedot,
kuten otsikon, kuvauksen, deadlinen ja prioriteetin.

### View
View sisältää käyttöliittymän.  
Tässä projektissa View on Jetpack Compose -näkymä, kuten `HomeScreen`.

View:
- näyttää tehtävälistan
- reagoi käyttäjän painalluksiin

### ViewModel
ViewModel hoitaa sovelluksen logiikan ja tilan.  
Tässä projektissa `TaskViewModel`:
- säilyttää tehtävälistan
- lisää, poistaa ja muokkaa tehtäviä
- hallitsee tehtävien tilaa StateFlow’n avulla

## Miten StateFlow toimii
StateFlow on tapa hallita sovelluksen tilaa ViewModelissa.
Kun data muuttuu (esimerkiksi tehtävä lisätään, poistetaan tai muokataan),
Jetpack Compose päivittää käyttöliittymän automaattisesti
`collectAsState()`-funktion avulla.

## Funktiot
Projektissa käytetään seuraavia funktioita tehtävälistan käsittelyyn
TaskViewModelin kautta:
- addTask
- toggleDone
- filterByDone
- sortByDueDate
- removeTask
- showAll
- updateTask

## Projektin testaaminen
Projekti voidaan testata Android Studiossa normaalisti Run-toiminnolla
Android-emulaattorissa tai fyysisessä Android-laitteessa.
