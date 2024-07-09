# Secure NOTe App
A full-stack web application that allows to create and manage securly stored notes. Security is provided by using advanced encription mechanisms.
After registration a user can log in and create a note, note can be addictionally encrypted with a choosen password, note can be deleted by its creator.
The application is created using Angular and Java's Spring Boot framework.

## Features
- User can register an account
- Password is required to be strong (high enthropy, length, different kinds of characters)
- User can Log in using email and password
- After login user can create a note
- Notes can be styled
- Note can be ecrypted with a password
- Logged user can display all the notes (only the titles)
- User can show content of choosen note
- Encrypted note can be decrypted with the password that was used to create it
- User can delete its own notes
- Security:
  - Communication between the server and client is secured by HTTPS protocol and CORS policy
  - The passwords and notes are stored in encrypted form
 
## Technologies
### Front-end
- TypeScript
- Angular 17
- Angular Material
- RxJS
### Back-end
- Java 17
- Spring Boot
- Maven
- Lombok
- PostgreSQL Database
### Infrastructure
- Docker

## Security
Before saving, passwords are encrypted using the 'Argon2' algorithm.
When trying to authenticate, the typed password after encryption is compared with saved password.
Secured notes are encrypted while creating and deprycted while receiving using the 'AES/CBC/PKCS5Padding' algorithm.
Notes can be styled using HTML, to ensure safety, they are sanitized before showing to client.

## Installation
1. Clone the repository to your local machine using Git:
`git clone https://github.com/Javelel/Secure-NOTe.git
cd Secure-NOTe`
2. Run the application:
`Docker compose up`
3. The application will be accessible at https://localhost.

## Screenshots
