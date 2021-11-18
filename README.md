# sti-microservice

We assume that a docker mysql image with the name sti-mysql is running.
This image should have the Chinook_MySql.sql script copied to /root/

```bash
docker start sti-mysql
docker exec -it sti-mysql bash
mysql -uroot -p

grant all on *.* to 'sti'@'%';
flush privileges
exit

mysql -usti -psti
source /root/Chinook_MySql.sql

# Or you can source with a version that has auto increment keys
source /root/Chinook_MySql_AutoIncrementPKs.sql

git clone https://github.com/miwashiab/sti-microservice.git

cd sti-microservices
gradle bootRun

```
Enjoy
