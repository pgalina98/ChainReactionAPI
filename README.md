# ChainReactionAPI
## 1. Instanciranje PostgresSQL baze
Pokrenuti instancu PostgresSQL baze pomoću Docker-a i bindati port na 5432 (defaultni PostresSQL port). PostgresSQL je moguće instancirati i lokalno preuzimanjem sa https://www.postgresql.org/download/, ali se zbog jednostavnosti preporučuje koristiti Docker.
> Ukoliko naiđete na probleme prilikom instanciranja PostresSQL baze, dodatne informacije te detaljnije upute možete pronaći na https://hub.docker.com/_/postgres
## 2. Izvršavanje SQL naredbi
```
DROP DATABASE IF EXISTS chainreactiondb;
```
```
DROP USER IF EXISTS pgalina;
```
```
CREATE DATABASE chainreactiondb;
```
```
CREATE USER pgalina WITH PASSWORD 'password';
```
```
GRANT ALL PRIVILEGES ON DATABASE chainreactiondb TO pgalina;
```
## 3. Podešavanje datoteke application.yaml [OPCIONALNO]
Ukoliko ste kreirali **user-a** i/ili **database** koristeći **vlastite** nazive tada je potrebno prilagoditi atribute (**db_name, username, password**) u datoteci *application.properties*
```
# Database Configuration for PostgresSQL
# Database Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<db_name>
# Change username i password to adapt it to your Database
spring.datasource.username=<username>
spring.datasource.password=<password>
```
## 4. Pokretanje aplikacije
Ukoliko ste izvršili sve prethodne korake tada možete pokrenuti aplikaciju klikom na gumb **Run ChainReactionApplication**
> U terminalu će se pojaviti poruka *Started ChainReactionApplication in X seconds (JVM running for 3.4)* ukoliko je aplikacija uspješno pokrenuta.
