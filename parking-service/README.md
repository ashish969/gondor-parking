docker run --name parking -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=parking -e MYSQL_USER=admin -e MYSQL_PASSWOR
D=admin -p 3307:3306 -d mysql


mysql -u root -p
admin
use parking