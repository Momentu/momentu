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
*add the following to your application.properties file*
```
aws.s3.mediaBucketName=[S3 bucket]
aws.s3.accessKeyId=[S3 Access Key]
aws.s3.secretAccessKey=[S3 Secret Key]

```