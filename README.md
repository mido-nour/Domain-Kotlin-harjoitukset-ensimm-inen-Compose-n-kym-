# Viikkotehtävä 1 & Viikkotehtävä 2 – Kotlin Compose ja ViewModel

Tämä projekti on Viikkotehtävä 2, joka on laajennus Viikkotehtävä 1 -tehtävästä

## Mitä sovellus tekee
- pystyt lisätä tehtävän
- pystyt poistaa tehtävän
- pystytjärjestää tehtävät päivämäärän mukaan
- pystyt näyttää tehdyt ja tekemättömät tehtävät erikseen painikkeilla
- Tehtävän voi merkitä tehdyksi ruksilla

## compose tilanhallinnan
Compose-tilanhallinta tarkoittaa, että käyttöliittymä seuraa sovelluksen tilaa.
Tässä projektissa tehtävälista seuraa tehtävien lisäämistä ja poistamista
sekä muita muutoksia.
Tehtävälista on tallennettu ViewModeliin state-muuttujana, ja kun data muuttuu,
Compose-käyttöliittymä päivittyy automaattisesti.


## Miksi käytin ViewModel eikä remember
remember:  on tarkoitettu lyhytikäiseen tilaan.
Se sopii esimerkiksi tekstikenttään, jossa käyttäjä kirjoittaa tekstiä.
Kun painetaan esimerkiksi Add-painiketta tai näkymä päivitetään,
tieto voi kadota.

ViewModelia : käytetään tärkeämmän ja pidempään säilyvän datan tallentamiseen.
Esimerkiksi näytön käännöksissä tieto ei katoa, koska ViewModel säilyy.



## funktiot
Projektissa käytetään seuraavia funktioita tehtävälistan käsittelyyn
TaskViewModelin kautta
- addTask
- toggleDone
- filterByDone
- sortByDueDate
- removeTask
- showAll
- 
## Projektin testaminen
Projekti voidaan testa Android Studiossa normaalisti Run-toiminnolla
Android-emulaattorissa tai fyysisessä laitteessa.


