#!/bin/sh

mkdir $CATALINA_HOME/webapps/transformer


cp -r src/main/webapp/* $CATALINA_HOME/webapps/transformer

cp -r target/classes $CATALINA_HOME/webapps/transformer/WEB-INF

cp -r lib $CATALINA_HOME/webapps/transformer/WEB-INF
