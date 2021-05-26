
-- ************************** users ****************************************

INSERT INTO users (username, password, enabled) VALUES ('customer1', '$2y$12$OTdFbEe9EqmvLJIVQrEf6OWO8AUagdfitwfVXVBefWv7C9hWAX9oG', TRUE);/*password: customer */
INSERT INTO users (username, password, enabled) VALUES ('customer2', '$2y$12$OTdFbEe9EqmvLJIVQrEf6OWO8AUagdfitwfVXVBefWv7C9hWAX9oG', TRUE);/*password: customer */
INSERT INTO users (username, password, enabled) VALUES ('customer3', '$2y$12$OTdFbEe9EqmvLJIVQrEf6OWO8AUagdfitwfVXVBefWv7C9hWAX9oG', TRUE);/*password: customer */
INSERT INTO users (username, password, enabled) VALUES ('customer4', '$2y$12$OTdFbEe9EqmvLJIVQrEf6OWO8AUagdfitwfVXVBefWv7C9hWAX9oG', TRUE);/*password: customer */
INSERT INTO users (username, password, enabled) VALUES ('rob', '$2a$10$Tc5D3zX6Ilabdc80lj6bLO/zuI8TQueM7UC8WHghPxBvH/SOl1pGu', TRUE);/*password: 123456 */
INSERT INTO users (username, password, enabled) VALUES ('user', '$2y$12$ib7HLC5a/du7UNFgRggGteXvr0SyclZiMSwdMXXZ1uwYyzFfBTkV.', TRUE);/*password: user */
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2y$12$STmgp8r1FxIQpmOlD9osTuAnYZQ4s8qahccMT.vgatkEE6I8ev8s.', TRUE);/*password: admin */
-- ******************************************************************************

-- ************************** authorities ****************************************
INSERT INTO authorities (username, authority_role) VALUES ('customer1', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer2', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer3', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('customer4', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('rob', 'CUSTOMER');

INSERT INTO authorities (username, authority_role) VALUES ('user', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('user', 'COMPANY_USER');

INSERT INTO authorities (username, authority_role) VALUES ('admin', 'CUSTOMER');
INSERT INTO authorities (username, authority_role) VALUES ('admin', 'COMPANY_USER');
INSERT INTO authorities (username, authority_role) VALUES ('admin', 'ADMIN');
-- ******************************************************************************





INSERT INTO orders (ordername, status, fk_user) VALUES ('order_10', 'open','customer1');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_11', 'open','customer1');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_20', 'open','customer2');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_31', 'open','customer3');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_32', 'open','customer3');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_40', 'open','customer4');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_41', 'pending','customer4');
INSERT INTO orders (ordername, status, fk_user) VALUES ('order_42', 'closed','customer4');
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



INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 1', 'city 1','PC1111','tel1111111','customer1');
INSERT INTO address (street,city,postalcode,telnumber,fk1_user) VALUES ('straat 2', 'city 2','PC2222','tel2222222','customer2');
-- INSERT INTO address (user_id,street,city,postalcode,telnumber) VALUES (3,'straat 3', 'city 3','PC3333','tel3333333');
-- INSERT INTO address (user_id,street,city,postalcode,telnumber) VALUES (4,'straat 4', 'city 4','PC4444','tel4444444');
