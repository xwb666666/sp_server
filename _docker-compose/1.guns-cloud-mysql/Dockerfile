FROM mysql:5.7

COPY ./guns_cloud_system.sql /docker-entrypoint-initdb.d
COPY ./guns_cloud_auth.sql /docker-entrypoint-initdb.d
COPY ./guns_cloud_file.sql /docker-entrypoint-initdb.d
COPY ./guns_cloud_workflow.sql /docker-entrypoint-initdb.d

COPY ./my.cnf /etc/mysql/conf.d