# Viikkotehtävä 4 – Navigointi ja MVVM käytännössä

Tämä projekti on Viikkotehtävä 4, joka on laajennus Viikkotehtävä 3 -tehtävästä.  
Tehtävässä käytetään MVVM-arkkitehtuuria ja Jetpack Compose -navigointia.

## Mitä sovellus tekee
- käyttäjä voi lisätä tehtävän dialogin avulla
- käyttäjä voi poistaa tehtävän
- käyttäjä voi muokata tehtävää dialogissa
- tehtävät voi merkitä tehdyiksi
- tehtävät voi järjestää päivämäärän mukaan
- tehtävät näkyvät kalenterimaisessa näkymässä (CalendarScreen)
- sovelluksessa on kolme sivua: Home, Calendar ja Settings
- käyttäjä voi vaihtaa sivujen välillä navigaation avulla
- Settings-näkymä sisältää sovelluksen perustiedot

## Mitä tarkoittaa navigointi Jetpack Composessa
Navigointi Jetpack Composessa tarkoittaa siirtymistä eri sivujen välillä 
yhden activityn sisällä composable-näkymien avulla.


## Mitä ovat NavHost ja NavController
NavControlleria käytetään sivujen vaihtamiseen sovelluksessa.
Tässä projektissa sitä käytetään siirtymiseen esimerkiksi HomeScreeniltä CalendarScreenille.
NavHost näyttää sen näkymän, joka on tällä hetkellä aktiivinen.
Kun käyttäjä valitsee jonkin sivun, NavController vaihtaa näkymän ja NavHost näyttää valitun sivun.


## Miten sovelluksesi navigaatiorakenne on toteutettu (Home ↔ Calendar).

Sovelluksessa on kolme nappia alavalikossa.
Niiden tarkoitus on, että käyttäjä voi vaihtaa sivujen välillä.
Sovelluksessa on kolme sivua Home, Calendar ja Settings.
Kun käyttäjä painaa jotakin nappia, sovellus vaihtaa sivua NavControllerin avulla.
NavController huolehtii sivujen vaihtamisesta näkymästä toiseen.



## 1) Miten MVVM ja navigointi yhdistyvät (yksi ViewModel kahdelle screenille)  
## 2)  Miten ViewModelin tila jaetaan kummankin ruudun välillä.
Tässä sovelluksessa käytetään MVVM-arkkitehtuuria (Model, ViewModel, View).
Sama ViewModel on käytössä kaikissa näkymissä, kuten HomeScreenissä ja CalendarScreenissä.

Kun tehtävää muokataan yhdessä näkymässä, muutos näkyy heti myös toisessa näkymässä.
Sovelluksessa käytetään StateFlow’ta tilan hallintaan, joten kun tehtäviä lisätään tai muokataan, 
käyttöliittymä päivittyy automaattisesti.


## Miten CalendarScreen on toteutettu (miten tehtävät ryhmitellään / esitetään kalenterimaisesti).
CalendarScreen on toteutettu siten, että kaikki tehtävät ryhmitellään päivämäärän mukaan.
Tehtävät järjestellään dueDate-päivän perusteella.
Jokainen päivämäärä näytetään otsikkona, ja sen alla näkyvät kyseisen päivän tehtävät.
Tehtävästä näytetään otsikko, kuvaus ja prioriteetti (low, medium tai high)
Näin käyttäjä näkee helposti, mille päivälle tehtävä kuuluu ja mitä tehtäviä on tulossa.

## Miten AlertDialog hoitaa addTask ja editTask.

Olen käyttänyt AlertDialogia, koska se on parempi ratkaisu kuin uusi näyttö.
Sen avulla käyttäjä voi lisätä uuden tehtävän ilman, että näkymää tarvitsee vaihtaa.
Kun käyttäjä lisää tehtävän dialogissa, tiedot lähetetään ViewModeliin, joka luo tehtävälle uuden id:n 
ja tallentaa sen listaan.

Muokkaustilanteessa, kun käyttäjä painaa tehtävää, avautuu dialogi, jossa vanhat tiedot näkyvät valmiiksi.
Käyttäjä voi muokata tietoja ja tallentaa muutokset ilman, että hänen tarvitsee siirtyä uuteen näkymään.
Näin sovellus pysyy selkeänä ja helppokäyttöisenä.


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
