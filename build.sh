#!/bin/bash

cd mailslurper
mvn clean assembly:assembly

cd ../admin
grails war

cd ..
