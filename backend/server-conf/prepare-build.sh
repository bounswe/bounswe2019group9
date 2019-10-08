#!/usr/bin/env bash

cd src/main/resources
cp base-application.properties application.properties
echo "spring.datasource.url=$BACKEND_POSTGRESQL_URL" >> application.properties
echo "spring.datasource.username=$BACKEND_POSTGRESQL_USERNAME" >> application.properties
echo "spring.datasource.password=$BACKEND_POSTGRESQL_PASSWORD" >> application.properties