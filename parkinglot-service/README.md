docker run --name parkinglot -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=parkinglot -e MYSQL_USER=admin -e MYSQL_P
ASSWORD=admin -p 3306:3306 -d mysql

mysql -u root -p
admin
use parkinglot