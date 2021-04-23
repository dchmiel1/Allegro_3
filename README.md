# Zadanie nr 3
Aplikacja wykorzystuje Spring Boot Framework  
Do uruchomienia wymagana jest Java 1.8 lub nowsza
## Uruchomienie
### Linux
```
git clone https://github.com/dchmiel1/Allegro_3.git
cd Allegro_3
chmod +x mvnw
./mvnw spring-boot:run
```
### Windows
```
git clone https://github.com/dchmiel1/Allegro_3.git
cd Allegro_3
mvnw spring-boot:run 
```
## Korzystanie z aplikacji
Gdy aplikacja jest uruchomiona, wystarczy otworzyć przeglądarkę internetową (lub inne narzędzie do wysyłania zapytań HTTP) i wpisać http://localhost:8080/{username}  
,gdzie **username** to nazwa użytkownika, którego którego repozytoria chcemy otrzymać.
## Uwagi
Aplikacja korzysta z Github REST API, które nakłada ograniczenie na liczbę pobieranych stron z danymi. Po przekroczeniu limitu należy odczekać godzinę, aby pobrać kolejne.  
Projekt można rozszerzyć o zwracanie innych danych, tworzenie różnych rankingów czy na przykład zautomatyzowane klonowanie repozytoriów użytkownika w zależności od potrzeb.
