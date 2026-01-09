# Viikkotehtävä 1 Domain + kotlin-harjoitukset + ensimmäinen Compose-näkymä 


## Kuvaus
Tässä harjoituksessa harjoitellaan Kotlinin perusteita Android-sovelluksessa. 
Tehtävässä toteutettiin Task-datamalli, mock-data, puhtaita Kotlin-funktioita sekä 
ensimmäinen Jetpack Compose -käyttöliittymä, joka näyttää tehtävälistan.

## Datamalli
Sovelluksessa käytetään Task-datamallia, joka sisältää seuraavat kentät:
- id
- title
- description
- priority
- dueDate
- done

Tehtävistä on luotu mock-dataa (mockTasks), jota käytetään sovelluksessa feikkinä tietokantana.

## Kotlin-funktiot
Projektissa käytetään seuraavia funktioita tehtävälistan käsittelyyn:
- addTask(list, task) :  lisää uuden tehtävän listaan
- toggleDone(list, id) : vaihtaa tehtävän tilan true tai false id:n perusteella
- filterByDone(list, done): näyttää vain valmiit tai keskeneräiset tehtävät
- sortByDueDate(list):  järjestää tehtävät eräpäivän mukaan

## käyttöliittymä
- Käyttöliittymä on toteutettu Jetpack Composella, ja HomeScreen näyttää tehtävälistan mock-datan avulla.
- Tehtävät esitetään Card-komponenteissa ja lista on toteutettu LazyColumn illa, jotta näkymä on selkeä ja vieritettävä.



## Projektin testaminen
Projekti voidaan testa Android Studiossa normaalisti Run-toiminnolla
Android-emulaattorissa tai fyysisessä laitteessa.


