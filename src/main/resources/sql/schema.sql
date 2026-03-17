CREATE DATABASE IF NOT EXISTS TouristGuide
  CHARACTER SET utf8mb4;

USE TouristGuide;

DROP TABLE IF EXISTS attractions;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS attractions_tags;

Create table if not exists attractions (
    attraction_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) not null,
    description VARCHAR(1000)
);

create table if not exists tags (
    tags_id int auto_increment primary key,
    name varchar(100) not null
);

create table if not exists attractions_tags (
    attraction_id int not null,
    tags_id int not null,
    primary key(attraction_id, tags_id),
    foreign key(attraction_id) references attractions (attraction_id) on delete cascade,
    foreign key(tags_id) references tags (tags_id) on delete restrict
);

