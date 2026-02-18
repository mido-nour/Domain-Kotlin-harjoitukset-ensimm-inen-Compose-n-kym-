# Viikkotehtävä 6 – Room

Tämä projekti on Viikkotehtävä 6, joka on laajennus Viikkotehtävä 4 -tehtävästä.  
Tässä tehtävässä lisättiin Room-tietokanta, jotta tehtävät tallentuvat pysyvästi puhelimeen.

## Mitä sovellus tekee
- käyttäjä voi lisätä tehtävän dialogin avulla
- käyttäjä voi poistaa tehtävän
- käyttäjä voi muokata tehtävää dialogissa
- tehtävät voi merkitä tehdyiksi
- tehtävät voi järjestää päivämäärän mukaan
- tehtävät tallentuvat pysyvästi Room-tietokantaan
- tehtävät näkyvät kalenterimaisessa näkymässä (CalendarScreen)
- sovelluksessa on kolme sivua: Home, Calendar ja Settings
- käyttäjä voi vaihtaa sivujen välillä navigaation avulla
- Settings-näkymä sisältää sovelluksen perustiedot

## Mitä Room tekee (Entity–DAO–Database–Repository–ViewModel–UI)

Room on Androidin tietokantakirjasto, joka tallentaa datan pysyvästi laitteen sisään.  
Ennen Roomin käyttöä data oli vain väliaikaisessa muistissa, eikä se säilynyt sovelluksen sulkemisen jälkeen.

Entity määrittelee tietokannan taulun rakenteen. Tässä projektissa TaskEntity kuvaa yhtä tehtävää tietokannassa

DAO sisältää tietokantaan liittyvät funktiot, kuten lukeminen, lisääminen, muokkaaminen ja poistaminen.

Database on varsinainen Room-tietokanta, joka luodaan sovellukseen ja antaa pääsyn DAO:hon.

Repository toimii välikerroksena ViewModelin ja Roomin välillä.

ViewModel hakee datan Repositoryltä ja tarjoaa sen käyttöliittymälle.

UI: näyttää datan käyttäjälle ja päivittyy automaattisesti, kun tietokanta muuttuu.

## Projektin rakenne

Projektissa käytetään MVVM-arkkitehtuuria ja Room-tietokantaa.

Projektin rakenne on seuraava:

- data/model → sisältää TaskEntity-tiedoston
- data/local → sisältää TaskDao ja AppDatabase
- data/repository → sisältää TaskRepository
- viewmodel → sisältää TaskViewModel
- view → sisältää HomeScreen, CalendarScreen ja muut käyttöliittymän tiedostot

Tämä rakenne erottaa käyttöliittymän, logiikan ja tietokannan toisistaan selkeästi.

## Miten datavirta kulkee

Kun käyttäjä lisää, muokkaa tai poistaa tehtävän, käyttöliittymä (UI) kutsuu ViewModelia.  
ViewModel lähettää pyynnön Repositorylle.  
Repository tallentaa tai hakee datan Room-tietokannasta DAO:n avulla.  
Room palauttaa datan Flow’n kautta ViewModelille.  
Tämän jälkeen ViewModel lähettää datan takaisin käyttöliittymälle, ja UI päivittyy automaattisesti.

## Funktiot

Projektissa käytetään seuraavia funktioita TaskViewModelin kautta:
- addTask
- toggleDone
- filterByDone
- sortByDueDate
- removeTask
- showAll
- updateTask

## Projektin testaaminen

Projekti voidaan testata Android Studiossa normaalisti Run-toiminnolla  
Android-emulaattorissa tai fyysisellä Android-laitteella.

