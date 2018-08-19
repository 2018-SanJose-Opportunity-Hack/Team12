create table feedback_question (
	id int NOT NULL AUTO_INCREMENT,
	int type,
	content varchar(1000)
); # realtime feedback question

create table sms_template (
	id int
	content varchar(1000)
)

create table survey_question (
	id int,
	content varchar(1000)
)

create table user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phonenumber varchar(20),
	email varchar(2000),
	name varchar(100)
)

create table conversation (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	convoID varchar(20),
	scheduleId INT,
	status int,
	userId int,
	createTime long,
	lastUpdateTime long
)


create table feedback (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	timestamp long ,
	questionId int,
	userId int,
    conversationId int,
	phoneNumber varchar(20),
	content varchar(200)
)

create table questions(
question_text varchar(500),
for_mentor binary,
priority numeric(1)
)

