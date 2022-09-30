#!/usr/bin/env bash

docker rm -f mariadb 2&> /dev/null

# start mariadb and launch all sql scripts in the directory database
docker run -p 3306:3306 --name mariadb -e MARIADB_ALLOW_EMPTY_ROOT_PASSWORD -d mariadb:10.4

