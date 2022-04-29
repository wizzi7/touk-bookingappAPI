
CREATE TABLE ticket (
                ticket_id SMALLINT NOT NULL,
                Type VARCHAR(10) NOT NULL,
                price DOUBLE NOT NULL,
                CONSTRAINT ticket_id PRIMARY KEY (ticket_id)
);


CREATE TABLE screening_room (
                screening_room_id INTEGER NOT NULL,
                name VARCHAR(20) NOT NULL,
                capacity INTEGER NOT NULL,
                CONSTRAINT screening_room_id PRIMARY KEY (screening_room_id)
);


CREATE TABLE room_row (
                room_row_id INTEGER NOT NULL,
                name VARCHAR(1) NOT NULL,
                seats INTEGER NOT NULL,
                screening_room_id INTEGER NOT NULL,
                CONSTRAINT room_row_id PRIMARY KEY (room_row_id)
);


CREATE TABLE movie (
                movie_id INTEGER NOT NULL,
                title VARCHAR(45) NOT NULL,
                CONSTRAINT movie_id PRIMARY KEY (movie_id)
);


CREATE TABLE screening (
                screening_id INTEGER NOT NULL,
                start TIMESTAMP NOT NULL,
                movie_id INTEGER NOT NULL,
                screening_room_id INTEGER NOT NULL,
                CONSTRAINT screening_id PRIMARY KEY (screening_id)
);


CREATE TABLE reservation (
                reservation_id INTEGER NOT NULL AUTO_INCREMENT,
                screening_id INTEGER NOT NULL,
                CONSTRAINT reservation_id PRIMARY KEY (reservation_id)
);


CREATE TABLE client (
                client_id INTEGER NOT NULL AUTO_INCREMENT,
                name VARCHAR(45) NOT NULL,
                surname VARCHAR(45) NOT NULL,
                reservation_id INTEGER NOT NULL,
                CONSTRAINT client_id PRIMARY KEY (client_id)
);


CREATE TABLE seat (
                seat_id INTEGER NOT NULL,
                number INTEGER NOT NULL,
                is_occupied BOOLEAN NOT NULL,
                room_row_id INTEGER NOT NULL,
                reservation_id INTEGER,
                ticket_id SMALLINT,
                CONSTRAINT seat_id PRIMARY KEY (seat_id)
);


ALTER TABLE seat ADD CONSTRAINT ticket_seat_fk
FOREIGN KEY (ticket_id)
REFERENCES ticket (ticket_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE screening ADD CONSTRAINT screening_room_screening_fk
FOREIGN KEY (screening_room_id)
REFERENCES screening_room (screening_room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE room_row ADD CONSTRAINT screening_room_Row_fk
FOREIGN KEY (screening_room_id)
REFERENCES screening_room (screening_room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE seat ADD CONSTRAINT Row_Seat_fk
FOREIGN KEY (room_row_id)
REFERENCES room_row (room_row_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE screening ADD CONSTRAINT movie_screening_fk
FOREIGN KEY (movie_id)
REFERENCES movie (movie_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE reservation ADD CONSTRAINT screening_reservation_fk
FOREIGN KEY (screening_id)
REFERENCES screening (screening_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE seat ADD CONSTRAINT reservation_seat_fk
FOREIGN KEY (reservation_id)
REFERENCES reservation (reservation_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE client ADD CONSTRAINT reservation_client_fk
FOREIGN KEY (reservation_id)
REFERENCES reservation (reservation_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
