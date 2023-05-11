use oopLoginDetails;
select database();
drop table if exists `users`;
CREATE TABLE IF NOT EXISTS `users` (
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    country VARCHAR(255) default "Kenya",
    county VARCHAR(255),
    phoneNumber BIGINT(10),
    gender VARCHAR(15),
    course VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS `students` (
    `student_id` int(10) NOT NULL AUTO_INCREMENT, 
    `surname` varchar(50) NOT NULL, 
    `othername` varchar(50) NOT NULL, 
    `national_id` int(10) NOT NULL,
    `email` varchar(50) NOT NULL,
    `phone` int(10) NOT NULL, 
    `gender` varchar(10) NOT NULL, 
    PRIMARY KEY (`student_id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
select * from users where name = "" and password = "";
select * from students;