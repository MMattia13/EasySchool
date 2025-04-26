# Easy School

## Obiettivo

Easy School è un sistema di gestione scolastica progettato per semplificare la gestione di utenti, classi, esami, lezioni e altre entità scolastiche. L'obiettivo principale è fornire un'applicazione scalabile e sicura per amministratori, insegnanti e studenti.

## Tecnologie

- **Java 21**: Linguaggio di programmazione principale.
- **Spring Boot**: Framework per lo sviluppo rapido di applicazioni Java.
- **Spring Security**: Per la gestione della sicurezza e dell'autenticazione.
- **Hibernate**: Per la gestione della persistenza dei dati.
- **MySQL**: Database relazionale per l'archiviazione dei dati.
- **Lombok**: Per ridurre il boilerplate code.
- **Maven**: Per la gestione delle dipendenze e la build del progetto.

## Architettura

Il progetto segue un'architettura **MVC (Model-View-Controller)**:

- **Model**: Contiene le entità principali come `User`, `Role`, `Class`, `Exam`, `Lesson`, ecc.
- **Controller**: Gestisce le richieste HTTP e fornisce le risposte appropriate.
- **Service**: Contiene la logica di business.
- **Repository**: Interagisce con il database utilizzando Spring Data JPA.

## Sicurezza e autenticazione

- **JWT (JSON Web Token)**: Utilizzato per l'autenticazione e l'autorizzazione degli utenti.
- **Ruoli**: Gli utenti sono classificati in ruoli come `ADMIN`, `MODERATOR` e `USER`.
- **Spring Security**: Configurato per proteggere gli endpoint API in base ai ruoli.

## Funzionalità principali

- Gestione degli utenti (creazione, aggiornamento, eliminazione, visualizzazione).
- Gestione delle classi scolastiche.
- Creazione e gestione di esami, lezioni e programmi.
- Autenticazione e autorizzazione basate sui ruoli.
- Dataset iniziale per popolare il database con dati di esempio.

## Avvio del progetto

1. Clona il repository:
   ```
    git clone https://github.com/MMattia13/EasySchool/

    cd backend 

    mvn clean package 

    java -jar target\backend-0.0.1-SNAPSHOT.war
   ```



