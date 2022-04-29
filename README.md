# Ticket booking app

# Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Assumptions](#assumptions)
* [Setup](#setup)
* [Demo](#demo)


# General info
A ticket booking application that supports seat reservations for a selected screening in one of several screening rooms.


# Technologies
* Java
* Spring Boot 2
* Spring MVC
* Hibernate 5
* Spring Data JPA
* Maven
* H2 database (in-memory)

# Assumptions
* Seats can be booked at latest 15 minutes before the screening begins.
* While booking several seats, the seats must be next to each other and in the same row.
* System properly handles polish characters, but executing the POST request in Windows PowerShell gives an error.
* Reservation expiration time is  set to 45 minutes.

# Setup
## Global requirements
* Installed Java and JDK
* Installed Maven

## Windows
Clone repository and create JAR file
```
git clone https://github.com/wizzi7/touk-bookingappAPI.git
cd touk-bookingappAPI
mvn package
cd ..
```
Run application server
```
cd target
java -jar bookingapp-0.0.1-SNAPSHOT.jar
```
# Demo
Open Windows PowerShell

* The user selects the day and the time when he/she would like to see the movie. The system lists movies available in the given time interval - title and screening
times.
```
curl.exe --location --request GET "http://localhost:8080/api/screenings/programme" --header "Date-Time: 2022-04-01 09:00:00"
```
* The user chooses a particular screening. The system gives information regarding screening room and available seats.
```
curl.exe --location --request GET "http://localhost:8080/api/screeningRooms/1"
```
* The user chooses seats, and gives the name of the person doing the reservation(name and surname). The system gives back the total amount to pay and reservation expiration time.
```
curl.exe --location --request POST 'http://localhost:8080/api/reservations' --header 'Content-Type: application/json' --data-raw '{
     \"seats\": [
         {
             \"seatId\": 1,
             \"ticket\": {
                 \"ticketId\": 3
             }
         }
     ],
     \"screening\": {
         \"screeningId\": 1
     },
     \"client\": {
         \"name\": \"Adam\",
         \"surname\": \"Bak-Swierk\"
     }
 }'
```
* Multiple seats reservation.
```
curl.exe --location --request POST 'http://localhost:8080/api/reservations' --header 'Content-Type: application/json' --data-raw '{
     \"seats\": [
         {
             \"seatId\": 4,
             \"ticket\": {
                 \"ticketId\": 1
             }
         },
	       {
             \"seatId\": 5,
             \"ticket\": {
                 \"ticketId\": 2
             }
         }
     ],
     \"screening\": {
         \"screeningId\": 1
     },
     \"client\": {
         \"name\": \"Adam\",
         \"surname\": \"Bak-Swierk\"
     }
 }'
```
 * Reservation on a pre-booked seat.
```
curl.exe --location --request POST 'http://localhost:8080/api/reservations' --header 'Content-Type: application/json' --data-raw '{
     \"seats\": [
         {
             \"seatId\": 4,
             \"ticket\": {
                 \"ticketId\": 1
             }
         }
     ],
     \"screening\": {
         \"screeningId\": 1
     },
     \"client\": {
         \"name\": \"Adam\",
         \"surname\": \"Kowal\"
     }
 }'
```
* Single Reservation so that there is place left over between two already reserved seats.
```
curl.exe --location --request POST 'http://localhost:8080/api/reservations' --header 'Content-Type: application/json' --data-raw '{
     \"seats\": [
         {
             \"seatId\": 3,
             \"ticket\": {
                 \"ticketId\": 1
             }
         }
     ],
     \"screening\": {
         \"screeningId\": 1
     },
     \"client\": {
         \"name\": \"Adam\",
         \"surname\": \"Kowal\"
     }
 }'
```
