#!/usr/bin/env bash

docker run --ulimit memlock=-1:-1 -it --net=host --rm=true --memory-swappiness=0 --name postgres-hibernate-testing -e POSTGRES_USER=hibernate_orm_test -e POSTGRES_PASSWORD=hibernate_orm_test -e POSTGRES_DB=hibernate_orm_test -p 5432:5432 postgres:10.5
