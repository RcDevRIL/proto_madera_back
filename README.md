# proto_madera_back - README V2.0.0
__Build status by branch__
* __master :__ [![M_Jenkins_Build_Status][]][M_latest_build]
* __int :__ [![I_Jenkins_Build_Status][]][I_latest_build]
* __dev :__ [![D_Jenkins_Build_Status][]][D_latest_build]

__Tests coverage :__
  ![Coverage](https://github.com/RcDevRIL/proto_madera_back/blob/master/coverage/coverage.svg)

Welcome on our development repository for our graduation project!! 

    You will find here the backend application that we produce for our graduation 
    project during a 2-years work-study program with CESI Dijon school.
  
*Distributed by __LesCodeursEnBois__ - CESI RIL B2 - 2018-2020*

## Documentation

To see the documentation of our API, please visit [our app documentation][doc_website].

## Getting Started

This README will guide you through the setup and deployment of this application on a local machine for Windows and Linux (Debian) OS.

# Windows 10 OS
##### Prerequisites

What things you need to install and how to install them.

* First you will need a copy of this repository: either use "Download" feature on [this][Github root] page, or use Git CLI if you have installed it on your computer:
    * `git clone https://github.com/RcDevRIL/proto_madera_back.git`
    
    * Get the Java [JDK]
    
    * Get [Maven]

    * Install [pgAdmin] to create a local database

## Run app on localhost

### Setup the local database on Windows

You need a copy of the database to run tests locally. To do so, when you're done with installing [pgAdmin] v11.6, you have to go on the home page of pgAdmin server. Clic on create server if you want to add custom name for connection. Then create a database on the selected server. database server **must** be _'madera'_, host name: _localhost / 127.0.0.1_, user: _postgres_, password: *your_master_password* (which is the one you used to setup [pgAdmin]).

    Your database is now up and running, we need to build it now with our scheme.
Select the newly created 'madera' database, and click on black lightning icon (Query Tool).

To build the database, copy paste [madera_dump.sql] content into the Query Tool and run the query. Do the same with [data_dump.sql]!
	
    Now we can install the backend application in our maven repository: this will compile to the packaging specified in pom.xml file.

### Build & Run

To start the installation of the application, you need to add application properties file first because we didn't versionned this file for security purposes!
Please create a new file '_madera.properties_' under _[src/main/resources]_ folder. This file __must__ contain these lines:
```
1  driver.className=org.postgresql.Driver
2  db.url=jdbc:postgresql://127.0.0.1:5432/madera
3  db.user=postgres
4  db.pass=your_master_password
5  server.port=8081
```
Now run the following command:

  `mvn clean install -DskipTests=true`

We are skipping tests on purpose as there is another section for this.
There is now two ways to launch application:

* `mvn spring-boot:run`

* `java -jar ./target/*.jar`

_Using the first option is probably the better to test our app._
## Run tests

_This paragraph explain how to trigger tests written in the **[test]** folder of this repository._

You need the local database to run tests and see green, so if you haven't done it already, please refer to the **[Setup the local database][Windows_db_setup]** section

To start tests, execute this command:

  `mvn clean test`

This will trigger the execution of tests. Maven will output result and possible errors.

# Linux OS (Debian)
##### Prerequisites

What things you need to install and how to install them.

* First you will need to install some packages on the Debian machine. Get these from the following command: `sudo apt install`. You have to enable these popular tools:
  * postgresql
  * java (open-jdk 11)
  * git

## Run app on localhost
### Setup the local database on Debian

You now need to configure postgresql.

##### PostgreSQL Configuration

To connect to the server:

`sudo -u postgres bash` then `psql`. To quit this mode simply enter `\q`.

You will now set your master password for default/admin user '_postgres_'. To do so, enter the command  `\password` and set your password, we will refer it as *your_master_password* from now on.

Last but not least, create the empty scheme by typing:

`CREATE DATABASE madera;`

And finally, to start the installation of the application, you need to add application properties file first because we didn't versionned this file for security purposes!

Please create a new file '_madera.properties_' under _[src/main/resources]_ folder. This file __must__ contain these lines:
```
1  driver.className=org.postgresql.Driver
2  db.url=jdbc:postgresql://127.0.0.1:5432/madera
3  db.user=postgres
4  db.pass=your_master_password
5  server.port=8081
```
Tired? We got you covered! Next step is executing the semi-automated deployment script!

But first, you  need a copy of this repository: either use "Download" feature on [this][Github root] page, or use Git CLI if you have installed it on your computer:
  * `git clone https://github.com/RcDevRIL/proto_madera_back.git`

We have implemented [this][deploy_backend.sh] script when we didn't have Jenkins deployed.
To run it on your Linux machine, just call following command:

`sh /path/to/project/madera-scripts/deploy_backend.sh`

This will prompt you the steps executed and ask 3 times for *your_master_password*.
After updating the database, it will install application on your maven repository by compiling source code after success of unit tests.

The script will then try to deploy the new jar built, but this will fail as it was scripted with an update in mind, and in our precise deployment environment: it will fail until you fix it. The thing is we use the list of java processes that run on the machine at the script execution time. But you will probably never have the same configuration on your machine.

Please refer to following section to understand how to launch properly our app.

### Build & Run
Make sure you have compiled the jar file with the following command:

`mvn clean install -DskipTests=true`

We are skipping tests on purpose as there is another section for this.
There is now two ways to launch application:

* `mvn spring-boot:run`

* `nohup java -jar ./target/*.jar  > ~/maderaserver.log 2>&1 &`

_Using the second option is probably the better to test our app because this will run app on background and print standard output to the specified "maderaserver.log" at your user home._
## Run tests

_This paragraph explain how to trigger tests written in the **[test]** folder of this repository._

You need the local database to run tests and see green, so if you haven't done it already, please refer to the **[Setup the local database][Debian_db_setup]** section

To start tests, execute this command:

  `mvn clean test`

This will trigger the execution of tests. Maven will output result and possible errors.

## Built With

* [Spring] - The Java framework used to build this server
* [Jooq] - The 'ORM' used to build and generate database interaction code
* [JUnit4] - The popular unit test framework. We also use Jacoco to generate tests reports.
* [PostgreSQL] - The popular DBMS used to build and manage our database


## Contributing

To contribute please email one of the authors...or hit that PR button!! :rocket: :smile:

## Authors

* **Romain** - *Main Author* - [RcDevRIL]
* **David** - *Main Author* - [BoiteSphinx]
* **Fabien** - *Main Author* - [LadouceFabien]

See also the list of [contributors] who participated in this project.

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE.md] file for details

[Windows_db_setup]: https://github.com/RcDevRIL/proto_madera_back#setup-the-local-database-on-windows
[Debian_db_setup]: https://github.com/RcDevRIL/proto_madera_back#setup-the-local-database-on-debian
[Spring]: https://spring.io/
[Jooq]: https://www.jooq.org/
[JUnit4]: https://junit.org/junit4/
[PostgreSQL]: https://www.postgresql.org/
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
[src/main/resources]: https://github.com/RcDevRIL/proto_madera_back/src/main/resources
[deploy_backend.sh]: https://github.com/RcDevRIL/proto_madera_back/blob/master/madera-scripts/deploy_backend.sh
[doc_website]: http://vps756227.ovh.net/
[M_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK
[M_latest_build]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK/
[I_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK_INT
[I_latest_build]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK_INT/
[D_Jenkins_Build_Status]: http://vps756227.ovh.net:8082/buildStatus/icon?job=PROTO_MADERA_BACK_DEV
[D_latest_build]: http://vps756227.ovh.net:8082/job/PROTO_MADERA_BACK_DEV/
