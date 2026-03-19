use TouristGuide;

Set foreign_key_checks = 0;

truncate table attractions;
truncate table tags;
truncate table attractions_tags;

set foreign_key_checks = 1;

start transaction ;

insert into tags(name) values
('FORLYSTELSESPARK'),
('MUSEUM'),
('HISTORISK'),
('KIRKE'),
('SLOT'),
('PARK'),
('ZOO'),
('NATUR'),
('ARKITEKTUR'),
('TEATER'),
('BIOGRAF'),
('SHOPPING'),
('RESTAURANT'),
('CAFE'),
('FAMILIEVENLIG'),
('BOERNEVENLIG'),
('VOKSEN'),
('UNGE'),
('GRATIS'),
('STUDIERABAT'),
('PENSIONISTRABAT'),
('BOERNERABET');

insert into attractions(name, location, description) values
('Tivoli', 'Koebenhavn', 'En populaer forlystelsespark'),
('Bakken', 'Klambemborg', 'Samlingspunkt og forlystelsespark'),
('Taarnet', 'Koebenhavn', 'Et gammelt Taarn');

insert into attractions_tags (attraction_id, tags_id) values
(1, 1),
(1, 6),
(2, 1),
(3, 9);

commit;