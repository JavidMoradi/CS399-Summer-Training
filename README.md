# Bilkent CS399 Summer Training
This project is an Identity and Access Management (IAM) program that generates a JSON Web Token (JWT) to a user after log-in. Afterward, on each operation, the JWT is a mandatory requirement on header section of each API call. If a user has access to a method, program will call an alert stating that the user has access; otherwise, alert's message will be "user don't have access." This internship was done as a part of Bilkent University's mandatory Summer Training/Internship programme. Internship was done at İleti Yönetim Sistemi corp.

Used Technologies
===
- Java Spring Boot
- ReactJs
- PostgreSQL

How to Run
===
- Open iys-project folder and find application.properties file
  - it should be located at "iys-project/src/main/resources"
- Change the fields with [ ] in accordance with your PostgreSQL's configuration
  - choose any arbitrary secret key
- Run the iys-front
- Go to PostgreSQL and fill each Database tables to your liking
- Navigate to iys-front, and write "npm start" on your cmd.
  - you can find a read me regarding how to run a ReactJs project on the same directory
- You will be presented with the webpage

Please note that the used technologies' setup is assumed and neglected.
