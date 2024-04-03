## Table of contents

- [Quick start](#quick-start)
- [Status](#status)
- [Project notes](#project-notes)
- [What's included](#whats-included)
- [Bugs and feature requests](#bugs-and-feature-requests)
- [Contributing](#contributing)
- [Creators](#creators)
- [Thanks](#thanks)
- [Copyright and license](#copyright-and-license)

## Quick start

** Deployment **
Change application.properties 
These properties need to the change
- database setting
spring.datasource.url=jdbc:mysql://localhost:3307/termdb
spring.datasource.username=root
spring.datasource.password=
- embeded tomcat
server.address=0.0.0.0
server.port=8080
- sparql setting
sparql.endpoint.url=http://localhost:8888/sparql
ontology.gbads.prefix=https://gbadske.org/ontologies/2024/2/SpeciesTerm#
- webservices api username passwords setting
gbads.api.username=le
gbads.api.password=1234

## Project notes
1. IDE: 
	Eclipse IDE for Enterprise Java and Web Developers 
	Version: 2023-09 (4.29.0)
	Build id: 20230907-1323
2. Eclipse Market place Install: Spring Tools 4
3. Add Spring Repos: H2 Database, Spring Boot DevTools, Lombok, Spring Data JDBC, Spring Web, MySQL Driver, Thymeleaf, springdoc-openapi-starter-webmvc-ui, Spring security, rdf4j  
4. Create database using schema.sql	 
5. To change address of the MySQL database go to: application.properties in src/main/resources
6. To Run the project: Right Click the project and Run AS Spring Boot APP
7. To Generate the JAR: Right Click the project and Run AS Maven Verify


## Status

Here goes all the budgets

## What's included



## Bugs and feature requests

Have a bug or a feature request? Please first read the [issue guidelines](https://reponame/blob/master/CONTRIBUTING.md) and search for existing and closed issues. If your problem or idea is not addressed yet, [please open a new issue](https://reponame/issues/new).

## Contributing

Please read through our [contributing guidelines](https://reponame/blob/master/CONTRIBUTING.md). Included are directions for opening issues, coding standards, and notes on development.

Moreover, all HTML and CSS should conform to the [Code Guide](https://github.com/mdo/code-guide), maintained by [Main author](https://github.com/usernamemainauthor).

Editor preferences are available in the [editor config](https://reponame/blob/master/.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org/>.

## Creators

**Creator 1**

- <https://github.com/lenguyen2022>

## Thanks

Some Text

## Copyright and license

Code and documentation copyright 2023-2024 the authors. Code released under the [License](https://reponame/blob/master/LICENSE).
