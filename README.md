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


有一个重要的点：想办法限制一下，order中具有相同customId、orderItemId跟orderStatus的只能有一个量。

简化一下数据库的命名。稍微修改一下数据库的数据类型。

登录还是自己做吧：需要加一个token的表，user的表还需要进行一定的改动（预计使用jjwt来做）。

user表中的密码做一下加密。

token库： id（自生成）（big int） userId（big int） buildTime（int 11） token（string）

准备开发一下前端部分