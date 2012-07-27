MailSlurper
===========
Simple mail SMTP server that slurps mail into oblivion! Useful only for local development
MailSlurper runs on port 2500 and listens for outgoing mail send requests. When one is
received the mail item is stored in a local H2 database.

With an embedded web server running on port 4040 you can use your web browser and 
go to address *http://localhost:4040* and view mail items that MailSlurper has 
gobbled up.


Requirements (to build)
-----------------------
* Java JDK 6+
* Grails 2.1.0
* Maven
* Ant

Requirements (to run)
---------------------
* Java JDK 6+

How to Build
------------
To build run **./build.sh**.

How to Run
----------
* Windows: mailslurper.bat
* Linux: ./mailslurper.sh

Release Notes
-------------

**07.27.2012**
* Updated interface to use Twitter Bootstrap
* Upgrade to Grails 2.1.0
* Application now uses embedded Jetty instead of Tomcat
* Simplier binaries. Now only main JAR and WAR for web app. 

**09.03.2010**
* Updates to support paging and sorting

**09.02.2010**
* Initial release

