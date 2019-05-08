USE Projectdb;

DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Subject;

create table subject(
	subjectId integer not null,
	subjectAbbreviation varchar(4) not null,
	subjectName varchar(50),
	primary key (subjectId)
);

create table Course (
  courseId integer not null,
  subId integer not null,
  courseNumber integer, 
  title varchar(50) not null, 
  numOfCredits integer, 
  instructorId integer,
  primary key (courseId),
  foreign key (subId) references subject (subjectId)
);


INSERT INTO `subject`(`subjectId`, `subjectAbbreviation`, `subjectName`) VALUES (100,"CIT","Computer Information Technology");
INSERT INTO `subject`(`subjectId`, `subjectAbbreviation`, `subjectName`) VALUES (101,"ENG","English");
INSERT INTO `subject`(`subjectId`, `subjectAbbreviation`, `subjectName`) VALUES (102,"MAT","Math");
INSERT INTO `subject`(`subjectId`, `subjectAbbreviation`, `subjectName`) VALUES (103,"PSY","Psychology");


INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (110, 100, 119, "Python 1", 4);
INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (111, 101, 101, "English Comp 1", 4);
INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (112, 100, 129, "Python 2", 4);
INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (113, 102, 108, "Academic Algebra", 4);
INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (114, 100, 111, "Intro To Java", 4);
INSERT INTO `course`(`courseId`, `subId`, `courseNumber`, `title`, `numOfCredits`) VALUES (115, 100, 130, "Object Oriented Java 1", 4);

