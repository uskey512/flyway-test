#!/bin/sh
DB_NAME=flyway
USER=root
PASSWORD=root

mysql -u$USER -p$PASSWORD $DB_NAME < /docker-entrypoint-initdb.d/initial.sql
