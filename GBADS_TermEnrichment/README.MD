1. IDE: 
	Eclipse IDE for Enterprise Java and Web Developers 
	Version: 2023-09 (4.29.0)
	Build id: 20230907-1323
2. Eclipse Market place Install: Spring Tools 4
3. Add Spring Repos: H2 Database, Spring Boot DevTools, Lombok, Spring Data JDBC, Spring Web, MySQL Driver, Thymeleaf, springdoc-openapi-starter-webmvc-ui  
4. Create database using schema.sql	 
5. To change address of the MySQL database go to: application.properties in src/main/resources
6. To Run the project: Right Click the project and Run AS Spring Boot APP
7. To Generate the JAR: Right Click the project and Run AS Maven Verify

Running in docker
```
docker run \
	-p 80:80 \
	-e MYSQL_HOST=hosturl \
	-e MYSQL_DATABASE=database-name \
	-e MYSQL_USER=username \
	-e MYSQL_PASSWORD=password \
	-e BASE_URL=/api \
	gbadsinformatics/term-enrichment-api
```