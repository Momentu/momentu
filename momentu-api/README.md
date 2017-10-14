*enter mysql console using root password:*
```
mysql -uroot -p
```
*run following commands in console to create database and user:*
```sql
drop database if exists momentu;
create database momentu;
grant all privileges on momentu.* to 'mdbuser'@'localhost' identified by "se491SE591password!";

```