`docker network create pg-network`

```shell
docker run \
--name PostgreSQL \
--network pg-network \
-v postgre:/var/lib/postgresql/data \
-e POSTGRES_PASSWORD=1 \
-p 5432:5432 \
-d postgres:15
```

```shell
docker run \
--name pgadmin \
--network pg-network \
-e "PGADMIN_DEFAULT_EMAIL=user@domain.com" \
-e "PGADMIN_DEFAULT_PASSWORD=1" \
-p 5050:80 \
-d dpage/pgadmin4
```

За `pgadmin connect` взимаш `ip`-то на посгрес контейнера  

`liquibase:update`