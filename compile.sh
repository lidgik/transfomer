#!/bin/sh

mkdir target/classes

javac -d target/classes -classpath "$CATALINA_HOME/lib/servlet-api.jar;lib/mysql-connector-java-3.1.14-bin.jar" src/main/java/com/bodejidi/ContactServlet.java



