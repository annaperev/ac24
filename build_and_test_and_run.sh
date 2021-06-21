#!/bin/bash

cd /sources
mvn package

java -jar target/listing-report-0.0.1-SNAPSHOT.jar