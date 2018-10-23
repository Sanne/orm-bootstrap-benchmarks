#!/usr/bin/env bash

echo "Starting mvn build..."
mvn clean package

## Update the classpath definition used by this script:
mvn dependency:build-classpath -DexcludeArtifactIds=graal-annotations -Dmdep.outputFile=cp.txt
CLASSPATH=`cat cp.txt`

echo "CLASSPATH: $CLASSPATH"
PROFILER_OPT=""

#-XX:+UseStringDeduplication -XX:+PrintStringDeduplicationStatistics
TUNING_OPT="-XX:MetaspaceSize=80M -Xmx1G -XX:+UseG1GC "
#PROFILER_OPT="-agentpath:/opt/jprofiler10/bin/linux-x64/libjprofilerti.so=offline,id=128,config=/home/sanne/.jprofiler10/config.xml"

java $TUNING_OPT $PROFILER_OPT -cp "$CLASSPATH":./target/classes org.hibernate.orm.jmh.StandardLaunch

