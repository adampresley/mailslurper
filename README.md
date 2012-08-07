MailSlurper
===========
Simple mail SMTP server that slurps mail into oblivion! Useful only for local development
MailSlurper runs on port 2500 and listens for outgoing mail send requests. When one is
received the mail item is stored in a local H2 database.

With an embedded web server running on port 4040 you can use your web browser and 
go to address *http://localhost:8083* and view mail items that MailSlurper has 
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

**08.07.2012**
* Removed calls to console.log() in JavaScript that breaks IE

**07.27.2012**
* Updated interface to use Twitter Bootstrap
* Upgrade to Grails 2.1.0
* Application now uses embedded Jetty instead of Tomcat
* Now has a refresh button to reload page 
* Fixed issue with bad date formatting
* Simplier binaries. Now only main JAR and WAR for web app. 

**09.03.2010**
* Updates to support paging and sorting

**09.02.2010**
* Initial release

License
-------
Copyright 2012 Adam Presley. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY Adam Presley "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL Adam Presley OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.