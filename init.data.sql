use retromall;
insert into tb_category(category_name, parent, is_classification) values ('Play Station', null, true);
insert into tb_category(category_name, parent, is_classification) values ('PS4', 'Play Station', false);
insert into tb_category(category_name, parent, is_classification) values ('PS3', 'Play Station', false);
insert into tb_category(category_name, parent, is_classification) values ('PS5', 'Play Station', false);
insert into tb_category(category_name, parent, is_classification) values ('PS2', 'Play Station', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox', null, true);
insert into tb_category(category_name, parent, is_classification) values ('Xbox 360 Series', 'Xbox', true);
insert into tb_category(category_name, parent, is_classification) values ('Xbox One Series', 'Xbox', true);
insert into tb_category(category_name, parent, is_classification) values ('Xbox(2001)', 'Xbox', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox 360(2005)', 'Xbox 360 Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox 360 S(2010)', 'Xbox 360 Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox 360 E(2013)', 'Xbox 360 Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox One(2013)', 'Xbox One Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox One X(2017)', 'Xbox One Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox One All Digital(2019)', 'Xbox One Series', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox Series S(2020)', 'Xbox', false);
insert into tb_category(category_name, parent, is_classification) values ('Xbox Series X(2020)', 'Xbox', false);