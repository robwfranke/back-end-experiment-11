
-- ************************** users ****************************************

INSERT INTO users (username, password, enabled, email) VALUES ('customer1', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'customer1@gmail.com');/*password: 12345678 */
INSERT INTO users (username, password, enabled, email) VALUES ('customer2', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'customer2@gmail.com');/*password: 12345678 */
INSERT INTO users (username, password, enabled, email) VALUES ('customer3', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'customer3@gmail.com');/*password: 12345678 */
INSERT INTO users (username, password, enabled, email) VALUES ('customer4', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'customer4@gmail.com');/*password: 12345678 */
INSERT INTO users (username, password, enabled, email) VALUES ('rob', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'rob@gmail.com');/*password: 12345678 */
INSERT INTO users (username, password, enabled, email) VALUES ('karel','$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'karel@gmail.com');/*password: 12345678*/
INSERT INTO users (username, password, enabled, email) VALUES ('user', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'user@gmail.com');/*password: 12345678*/
INSERT INTO users (username, password, enabled, email) VALUES ('admin', '$2y$12$zZ18kCpfzeF8C8O8vFA9pu.1Epkibi33YnuhG5aRsKlmb28veITPm', TRUE,'admin@gmail.com');/*password: 12345678*/
-- ******************************************************************************

-- ************************** authorities ****************************************
INSERT INTO authorities (username, authority_role) VALUES ('customer1', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer2', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer3', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer4', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('rob', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('karel', 'CUSTOMER');

INSERT INTO authorities (username, authority_role) VALUES ('user', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('user', 'COMPANY_USER');

INSERT INTO authorities (username, authority_role) VALUES ('admin', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('admin', 'COMPANY_USER');
INSERT INTO authorities (username, authority_role) VALUES ('admin', 'ADMIN');
-- ******************************************************************************





INSERT INTO orders (ordername, description, status, fk_user) VALUES ('order_10', 'Omschrijving order_10', 'open','customer1');
INSERT INTO orders (ordername, description, status, fk_user) VALUES ('order_11', 'Omschrijving order_11', 'pending','customer1');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_20', 'open','customer2');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_31', 'open','customer3');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_32', 'open','customer3');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_40', 'open','customer4');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_41', 'pending','customer4');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_42', 'finished','customer4');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_51', 'open','rob');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_52', 'open','rob');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_53', 'open','rob');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_54', 'open','rob');



INSERT INTO items (itemname, quantity, fk_order) VALUES ('1001',13,2);
INSERT INTO items (itemname, quantity, fk_order) VALUES ('2001', 1,2);
INSERT INTO items (itemname, quantity, fk_order) VALUES ('3001', 3,2);
INSERT INTO items (itemname, quantity, fk_order) VALUES ('4001', 6,1);
--
--
INSERT INTO jobs (jobname, department) VALUES ('voordraaien', 'draai afdeling');
INSERT INTO jobs (jobname, department) VALUES ('nadraaien', 'draai afdeling');
INSERT INTO jobs (jobname, department) VALUES ('voorfrezen', 'frees afdeling');
INSERT INTO jobs (jobname, department) VALUES ('nafrezen', 'frees afdeling');
INSERT INTO jobs (jobname, department) VALUES ('slijpen', 'slijp afdeling');


INSERT INTO linktable_item_job (fk_item, fk_job) VALUES ('1', '1');
INSERT INTO linktable_item_job (fk_item, fk_job) VALUES ('1', '2');
INSERT INTO linktable_item_job (fk_item, fk_job) VALUES ('1', '3');
INSERT INTO linktable_item_job (fk_item, fk_job) VALUES ('1', '4');



INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 1', 'city 1','PC1111','0123456789','customer1');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 2', 'city 2','PC2222','9876543210','customer2');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 3', 'city 3','PC3333','9876543210','customer3');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 4', 'city 4','PC4444','9876543210','customer4');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat rob', 'city rob','PC2222','9876543210','rob');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat karel', 'city karel','PC2222','9876543210','karel');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat user', 'city user','PC2222','9876543210','user');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat admin', 'city admin','PC2222','9876543210','admin');

