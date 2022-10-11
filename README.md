# demokslm
a restful demo of kslm
use mybatis plus, springboot, knife4j, mysql, lombok to build a restful demo of our project backend part

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