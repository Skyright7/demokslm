# demokslm
a restful demo of kslm
use mybatis plus, springboot, knife4j, mysql, lombok to build a restful demo of our project backend part

Please install mysql. And also change the database setting in resources/appication.yml to your own database name, and connection username and password.

3 table's sql
```sql
create table user(
	userId int not null auto_increment,
    userCaseId varchar(32) not null,
    userpassword varchar(32) not null,
    userName varchar(32) not null,
    userEmailAddress varchar(32) default null,
    userPrivilege int not null default 1,
    userPreferredName varchar(32) default null,
    userAddress varchar(255) default null,
    userPhoneNumber varchar(32) default null,
    userGender int not null,
    userGraduationYear int not null,
    userMajor varchar(32) default null,
    userAvatar int not null default 1,
    userTitle varchar(255) default null,
    userStatus int not null default 1,
    primary key(userId)
)
```
```sql
create table seat(
	seatId int not null auto_increment,
    seatType int not null,
    seatOccupancy int not null default 1,
    seatPosition varchar(32) not null,
    seatAvailableTime varchar(255) not null,
    primary key(seatId)
)
```
```sql
create table orders(
	orderId int not null auto_increment,
    orderTime varchar(32) not null,
    customId int not null,
    orderItemId int not null,
    orderStatus int not null default 1,
    primary key(orderId)
)
```


```sql
create table if not exists token(
    id int not null auto_increment,
    userId int not null,
    buildTime int(11) not null ,
    token varchar(300) not null,
    primary key(id)
)
```
Token table sql


```sql
create table if not exists swiper(
        swiperId int not null auto_increment,
        imageId int not null,
        primary key(swiperId)
)
```
swiper table sql

```sql
create table message(
        messageId int not null auto_increment,
        imageId int not null,
        receiverId int not null,
        header varchar(32) not null,
        sender varchar(32) not null,
        snapshots varchar(32) not null,
        times varchar(32) not null,
        detail varchar(255) not null,
        primary key(messageId)
)
```
message table sql

```sql
create table event(
        eventId int not null auto_increment,
        imageId int not null,
        header varchar(32) not null,
        snapshots varchar(32) not null,
        times varchar(32) not null,
        detail varchar(255) not null,
        primary key(eventId)
)
```
event table sql


Then please add some data in use table(id=1 are needed), and some data in seat table.

Also, the please use the pom.xml file to load the module of the project.

After you successful set up the project, you want see the full Api of the backend. We also setup the Api documentation. After launch the backend service, please use http://localhost:8080/doc.html#/home to access the documentation.
