#!/bin/bash

mvn clean package
mvn javadoc:javadoc
java -jar target/GetRich-1.0-SNAPSHOT.jar