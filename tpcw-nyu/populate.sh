#! /bin/sh

JAVACMD=$JAVA_HOME/bin/java

_CLASSPATH=./build/classes/
_CLASSPATH=$JBOSS_DIST/server/default/lib/mysql-connector-java-3.0.6-stable-bin.jar:$_CLASSPATH

$JAVACMD -classpath $_CLASSPATH edu.nyu.pdsg.tpcw.populate.Populate
