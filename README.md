# proto_madera_back - README V1.1
__Build status by branch__
* __master :__ 
* __int :__ [![I_Codemagic build status][]][I_latest_build]
* __dev :__ [![D_Jenkins_Build_Status][]][D_Jenkins_Job]

__Tests coverage :__
  ![Coverage](https://github.com/RcDevRIL/proto_madera_back/blob/master/coverage/coverage.svg)

Welcome on our development repository for our graduation project!! 

    You will find here the backend application that we produce for our graduation 
    project during a 2-years work-study program with CESI Dijon school.
  
*Distributed by __LesCodeursEnBois__ - CESI RIL B2 - 2018-2020*

## Getting Started

This README will guide you through the setup and deployment of this application on a local machine.

/!\ This README is a work in __progress__ ! /!\

###### Things to add on this README:

* [X] Jenkins badges to show build/test status
* [X] Test coverage badge
* [X] librairies used to run the app
* [X] How to run app on localhost
* [X] How to run tests
* [X] How to setup local database
* [ ] Add details on spring modules used if needed
* [ ] Write specific steps on Linux OS if needed
* [ ] How to ...

##### Prerequisites

What things you need to install and how to install them

* First you will need a copy of this repository: either use "Download" feature on [this][Github root] page, or use Git CLI if you have installed it on your computer:
    * `git clone https://github.com/RcDevRIL/proto_madera_back.git`
    
    * Get the Java [JDK]
    
    * Get [Maven]

    * Install [pgAdmin] to create a local database


## Run app on localhost
_(In the following sections, we assume you are running on **Windows** OS)_

### Setup the local database

You need a copy of the database to run tests locally. To do so, when you're done with installing [pgAdmin] v11.6, you have to go on the home page of pgAdmin server. Clic on create server if you want to add custom name for connection. Then create a database on the selected server. database server **must** be _'madera'_, host name: _localhost / 127.0.0.1_, user: _postgres_, password: *your_master_password*.

	Your database is now up and running, we need to build it now with our scheme.
	Select the newly created 'madera' database, and click on black lightning icon (Query Tool).

To build the database, copy paste [madera_dump.sql] content into the Query Tool and run the query. Do the same with [data_dump.sql]!
	
	Now we can install the backend application in our maven repository: this will compile to the packaging specified in pom.xml file.

### Build & Run

To start the installation of the application:

* `mvn clean install -DskipTests=true`

We are skipping tests on purpose as there is another section for this purpose on this README.
There is now two ways to launch application:

* `mvn spring-boot:run`

* `java -jar [path_to_jar]/api-[VERSION]` (__TODO__ vérifier)

## Run tests

_This paragraph explain how to trigger tests written in the **[test]** folder of this repository._

You need the local database to run tests and see green, so if you haven't done it already, please refer to the **[Setup the local database](https://github.com/RcDevRIL/proto_madera_back#setup-the-local-database)** section

To start tests, execute this command:

* `mvn clean test`

This will trigger the execution of tests. Maven will output result and possible errors. 

## Built With

* [Spring] - The Java framework used to build this server
* [Jooq] - The 'ORM' used to build and generate database interaction code
* [JUnit4] - The popular unit test framework

## Contributing

To contribute please email one of the authors...or hit that PR button!! :rocket: :smile:

## Authors

* **Romain** - *Main Author* - [RcDevRIL]
* **David** - *Main Author* - [BoiteSphinx]
* **Fabien** - *Main Author* - [LadouceFabien]

See also the list of [contributors] who participated in this project.

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE.md] file for details

## Disclaimer

* As I mentionned before, this is still a work in progress. :upside_down_face:
[M_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK
[M_Jenkins_Job]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK/
[I_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK_INT
[I_Jenkins_Job]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK_INT/
[D_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK_DEV
[D_Jenkins_Job]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK_DEV/
[Spring]: https://spring.io/
[Jooq]: https://www.jooq.org/
[JUnit4]: https://junit.org/junit4/
[Github root]: https://github.com/RcDevRIL/proto_madera_back/
[test]: https://github.com/RcDevRIL/proto_madera_back/tree/master/src/test/java/com/madera/api
[RcDevRIL]: https://github.com/RcDevRIL
[BoiteSphinx]: https://github.com/BoiteSphinx
[LadouceFabien]: https://github.com/LadouceFabien
[contributors]: https://github.com/RcDevRIL/proto_madera_back/contributors
[LICENSE.md]: https://github.com/RcDevRIL/proto_madera_back/blob/master/LICENSE
[JDK]: https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
[Maven]: https://maven.apache.org/download.cgi
[pgAdmin]: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
[madera_dump.sql]: https://github.com/RcDevRIL/proto_madera_back/blob/master/madera-scripts/dumps/madera_dump.sql
[data_dump.sql]: https://github.com/RcDevRIL/proto_madera_back/blob/master/madera-scripts/dumps/data_dump.sql
