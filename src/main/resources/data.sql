INSERT INTO members(name, password, email, phoneNumber)
VALUES ('Mikkel', '$2y$12$3Yt7Y1KlW1BaIwNDoIgDs.0FvVza2sby.znx5UllcTCjwe4TjJ1jG', 'tobiasrolesen@gmail.com',
        '11223344'),
       ('Mo', '$2y$12$3Yt7Y1KlW1BaIwNDoIgDs.0FvVza2sby.znx5UllcTCjwe4TjJ1jG', 'mo@gmail.com', '11111111');

INSERT INTO cats(name, mom, dad, color, race, memberId)
VALUES ('Trælle', 'BoomDestroyer3000', 'Ekstra Needy Bazooka', 'red', 'NORSK_SKOVKAT', 1),
       ('Tramper', 'BoomDestroyer3000', 'Ekstra Needy Bazooka', 'red', 'NORSK_SKOVKAT', 2),
       ('Tro', 'BoomDestroyer3000', 'Ekstra Needy Bazooka', 'red', 'NORSK_SKOVKAT', 1),
       ('Trækker', 'BoomDestroyer3000', 'Ekstra Needy Bazooka', 'red', 'NORSK_SKOVKAT', 1);

INSERT INTO catshows (title, location, date)
VALUES ('International Persian Cat Show', 'Copenhagen', '2026-06-15 10:00:00'),
       ('Nordic Maine Coon Championship', 'Aarhus', '2026-07-20 09:30:00'),
       ('Scandinavian Cat Expo', 'Odense', '2026-08-10 11:00:00'),
       ('Elite Bengal Showcase', 'Aalborg', '2026-09-05 10:00:00');