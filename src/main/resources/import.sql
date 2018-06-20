/* Person */
INSERT INTO person (id, username, password) VALUES (-1, 'Admin', 'admin');
INSERT INTO person (id, username, password) VALUES (-2, 'Staff', 'staff');
INSERT INTO person (id, username, password) VALUES (-3, 'User1', 'password');
INSERT INTO person (id, username, password) VALUES (-4, 'User2', 'password');

/* Staff */
INSERT INTO staff (id, staff_number, is_admin) VALUES (-1, 'A001', TRUE);
INSERT INTO staff (id, staff_number, is_admin) VALUES (-2, 'B001', FALSE);

/* Customer */
INSERT INTO customer (id, real_name, identity_number, gender, phone_number, email_address, is_vip) VALUES (-3, '王小明', '810000198607130010', '男', '10000', 'xiaoming@me.com', FALSE);
INSERT INTO customer (id, real_name, identity_number, gender, phone_number, email_address, is_vip) VALUES (-4, '李莓铃', '810000198607130002', '女', '', '', FALSE);

/* RoomType */
INSERT INTO room_type (id, description, capacity, price, image_url) VALUES (-1, '标准大床房', 1, 200.0, 'http://www.hotelnikkosz.com/d/nikkosz/mod_pukkaSimpleRooms/itemImage_11.jpg');
INSERT INTO room_type (id, description, capacity, price, image_url) VALUES (-2, '标准双人房', 2, 200.0, 'http://www.hotelnikkosz.com/d/nikkosz/mod_pukkaSimpleRooms/itemImage_51.jpg');
INSERT INTO room_type (id, description, capacity, price, image_url) VALUES (-3, '豪华大床房', 1, 300.0, 'http://www.hotelnikkosz.com/d/nikkosz/mod_pukkaSimpleRooms/itemImage_31.jpg');
INSERT INTO room_type (id, description, capacity, price, image_url) VALUES (-4, '豪华三人房', 3, 400.0, 'http://www.hotelnikkosz.com/d/nikkosz/mod_pukkaSimpleRooms/itemImage_141.jpg');

/* Room */
INSERT INTO room (id, room_number, room_type_id) VALUES (-1, 101, -1);
INSERT INTO room (id, room_number, room_type_id) VALUES (-2, 102, -1);
INSERT INTO room (id, room_number, room_type_id) VALUES (-3, 103, -1);
INSERT INTO room (id, room_number, room_type_id) VALUES (-4, 104, -1);
INSERT INTO room (id, room_number, room_type_id) VALUES (-5, 105, -1);
INSERT INTO room (id, room_number, room_type_id) VALUES (-6, 111, -2);
INSERT INTO room (id, room_number, room_type_id) VALUES (-7, 112, -2);
INSERT INTO room (id, room_number, room_type_id) VALUES (-8, 113, -2);
INSERT INTO room (id, room_number, room_type_id) VALUES (-9, 114, -2);
INSERT INTO room (id, room_number, room_type_id) VALUES (-10, 115, -2);
INSERT INTO room (id, room_number, room_type_id) VALUES (-11, 201, -3);
INSERT INTO room (id, room_number, room_type_id) VALUES (-12, 202, -3);
INSERT INTO room (id, room_number, room_type_id) VALUES (-13, 211, -4);
INSERT INTO room (id, room_number, room_type_id) VALUES (-14, 212, -4);
INSERT INTO room (id, room_number, room_type_id) VALUES (-15, 213, -4);

/* Reservation */
INSERT INTO reservation (id, customer_id, room_type_id, start_date, end_date, charge, reservation_time, allocated_room_id, checkin_staff_id, checkin_time, checkout_staff_id, checkout_time) VALUES (-4, -3, -4, '2018-06-01', '2018-06-05', 1600.0, '2018-05-30 08:16:32', -13, -2, '2018-06-01 18:48:00', -2, '2018-06-05 11:22:44');
INSERT INTO reservation (id, customer_id, room_type_id, start_date, end_date, charge, reservation_time, allocated_room_id, checkin_staff_id, checkin_time) VALUES (-3, -3, -1, 'yesterday', 'today', 200.0, 'yesterday', -1, -2, 'today');
INSERT INTO reservation (id, customer_id, room_type_id, start_date, end_date, charge, reservation_time, allocated_room_id, checkin_staff_id, checkin_time) VALUES (-2, -4, -3, 'yesterday', 'tomorrow', 600.0, 'yesterday', -11, -2, 'today');
INSERT INTO reservation (id, customer_id, room_type_id, start_date, end_date, charge, reservation_time) VALUES (-1, -3, -3, 'today', 'tomorrow', 300.0, 'yesterday');
