

INSERT INTO MOVIE (MOVIE_ID , TITLE)
VALUES (1, 'Spider Man');

INSERT INTO MOVIE (MOVIE_ID , TITLE)
VALUES (2, 'Avengers');

INSERT INTO MOVIE (MOVIE_ID , TITLE)
VALUES (3, 'Harry Potter');

INSERT INTO screening_room(screening_room_id, name, capacity)
VALUES 
(1, 'Screening room A', 10),
(2, 'Screening room B', 10),
(3, 'Screening room C', 10),
(4, 'Screening room A', 10),
(5, 'Screening room B', 10),
(6, 'Screening room C', 10);

INSERT INTO ticket(ticket_ID , type, price)
VALUES 
(1, 'adult', 25.0),
(2, 'student', 18.0),
(3, 'child', 12.5);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (1, '2022-07-01 10:15:00', 1, 1);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (2, '2022-07-01 13:00:00', 1, 2);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (3, '2022-07-01 18:45:00', 1, 3);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (4, '2022-07-02 13:00:00', 2, 4);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (5, '2022-07-03 18:30:00', 3, 5);

INSERT INTO screening(screening_id, start, movie_id, SCREENING_ROOM_ID )
VALUES (6, '2022-07-03 12:00:00', 3, 6);


INSERT INTO room_row(room_row_ID , seats, screening_room_id, name)
VALUES 
(1, 5, 1, 'A'),
(2, 5, 1, 'B'),
(3, 5, 2, 'A'),
(4, 5, 2, 'B'),
(5, 5, 3, 'A'),
(6, 5, 3, 'B'),
(7, 5, 4, 'A'),
(8, 5, 4, 'B'),
(9, 5, 5, 'A'),
(10, 5, 5,'B'),
(11, 5, 6, 'A'),
(12, 5, 6, 'B');

INSERT INTO seat(seat_ID , number, is_occupied, room_row_id, reservation_id, ticket_id)
VALUES 
(1,	1,	FALSE,	1,	null,	null),
(2,	2,	FALSE,	1,	null,	null),
(3,	3,	FALSE,	1,	null,	null),
(4,	4,	FALSE,	1,	null,	null),
(5,	5,	FALSE,	1,	null,	null),
(6,	1,	FALSE,	2,	null,	null),
(7,	2,	FALSE,	2,	null,	null),
(8,	3,	FALSE,	2,	null,   null),
(9,	4,	FALSE,	2,	null,	null),
(10,	5,	FALSE,	2,	null,	null),
(11,	1,	FALSE,	3,	null,	null),
(12,	2,	FALSE,	3,	null,	null),
(13,	3,	FALSE,	3,	null,	null),
(14,	4,	FALSE,	3,	null,	null),
(15,	5,	FALSE,	3,	null,	null),
(16,	1,	FALSE,	4,	null,	null),
(17,	2,	FALSE,	4,	null,	null),
(18,	3,	FALSE,	4,	null,	null),
(19,	4,	FALSE,	4,	null,	null),
(20,	5,	FALSE,	4,	null,	null),
(21,	1,	FALSE,	5,	null,	null),
(22,	2,	FALSE,	5,	null,	null),
(23,	3,	FALSE,	5,	null,	null),
(24,	4,	FALSE,	5,	null,	null),
(25,	5,	FALSE,	5,	null,	null),
(26,	1,	FALSE,	6,	null,	null),
(27,	2,	FALSE,	6,	null,	null),
(28,	3,	FALSE,	6,	null,	null),
(29,	4,	FALSE,	6,	null,	null),
(30,	5,	FALSE,	6,	null,	null),
(31,	1,	FALSE,	7,	null,	null),
(32,	2,	FALSE,	7,	null,	null),
(33,	3,	FALSE,	7,	null,	null),
(34,	4,	FALSE,	7,	null,	null),
(35,	5,	FALSE,	7,	null,	null),
(36,	1,	FALSE,	8,	null,	null),
(37,	2,	FALSE,	8,	null,	null),
(38,	3,	FALSE,	8,	null,	null),
(39,	4,	FALSE,	8,	null,	null),
(40,	5,	FALSE,	8,	null,	null),
(41,	1,	FALSE,	9,	null,	null),
(42,	2,	FALSE,	9,	null,	null),
(43,	3,	FALSE,	9,	null,	null),
(44,	4,	FALSE,	9,	null,	null),
(45,	5,	FALSE,	9,	null,	null),
(46,	1,	FALSE,	10,	null,	null),
(47,	2,	FALSE,	10,	null,	null),
(48,	3,	FALSE,	10,	null,	null),
(49,	4,	FALSE,	10,	null,	null),
(50,	5,	FALSE,	10,	null,	null),
(51,	1,	FALSE,	11,	null,	null),
(52,	2,	FALSE,	11,	null,	null),
(53,	3,	FALSE,	11,	null,	null),
(54,	4,	FALSE,	11,	null,	null),
(55,	5,	FALSE,	11,	null,	null),
(56,	1,	FALSE,	12,	null,	null),
(57,	2,	FALSE,	12,	null,	null),
(58,	3,	FALSE,	12,	null,	null),
(59,	4,	FALSE,	12,	null,	null),
(60,	5,	FALSE,	12,	null,	null);



