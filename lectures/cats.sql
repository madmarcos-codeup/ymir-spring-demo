-- Adminer 4.8.1 MySQL 5.5.5-10.6.12-MariaDB-0ubuntu0.22.04.1 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

truncate cat_toys;
truncate cats;
truncate toys;

INSERT INTO `cats` (`id`, `age`, `gender`, `name`, `owner_id`) VALUES
                                                                   (1,	15,	'Male',	'Smokey',	1),
                                                                   (2,	5,	'Female',	'Fluffy',	1);

INSERT INTO `cat_toys` (`toy_id`, `cat_id`) VALUES
                                                (1,	1),
                                                (2,	1),
                                                (2,	2),
                                                (3,	2);

INSERT INTO `toys` (`id`, `name`) VALUES
                                      (1,	'Yarn'),
                                      (2,	'Dead mouse'),
                                      (3,	'Laser pointer');

-- 2023-03-28 13:16:53
