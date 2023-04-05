
INSERT INTO public.tag (id, description, "name", persist_date) VALUES
(101, 'Java', 'Java', '11.03.2023  19:48:36'),
(102, 'JavaScript', 'JavaScript', '11.03.2023  19:48:36'),
(103, 'C#', 'C#', '11.03.2023  19:48:36'),
(104, 'HTML', 'HTML', '11.03.2023  19:48:36')
;

INSERT INTO public."role"  ("id", "name") VALUES
(100, 'ROLE_ADMIN'),
(200, 'ROLE_USER')
;

INSERT INTO public.user_entity
("id", "about", "city", "email", "full_name", "image_link", "is_deleted","is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk", "nickname", "password", "persist_date", "role_id")
VALUES
(101, 'about0', 'city0', 'super0@gmail.com', 'superfullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.087177', 'https://github.com/0', '0.com', 'https://vk.com/0', 'supernickname0', '$2a$10$CedsmtFiFcjwTXltVyPaS.BDiR8BHgu3ibBAE.jvo0s3Skd69LMfm', '2023-03-01 13:45:13.087177', 100),
(102, 'about1', 'city1', 'super1@gmail.com', 'superfullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.157876', 'https://github.com/1', '1.com', 'https://vk.com/1', 'supernickname1', '$2a$10$tzt5G/kpUYKwW1J5poHcG.eOJFnuUWk336HgmLYYqUMAaLsteGj/W', '2023-03-01 13:45:13.157876', 100),
(103, 'about2', 'city2', 'super2@gmail.com', 'superfullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.228975', 'https://github.com/2', '2.com', 'https://vk.com/2', 'supernickname2', '$2a$10$ik1ZSY50/K1610/r1uPCaOqeUk5NS25g9iJbZP2uJ0SyhWjz5Cdq6', '2023-03-01 13:45:13.228975', 100),
(104, 'about3', 'city3', 'super3@gmail.com', 'superfullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.296487', 'https://github.com/3', '3.com', 'https://vk.com/3', 'supernickname3', '$2a$10$137l1tGJAY.cjRAOTT.ATO1dNzezqGtcatZFX21Iqa/1NdStQcUMC', '2023-03-01 13:45:13.296487', 100),
(105, 'about4', 'city4', 'super4@gmail.com', 'superfullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.362387', 'https://github.com/4', '4.com', 'https://vk.com/4', 'supernickname4', '$2a$10$DxNa.GJfhJsSDgjI1exiiOE8v9fp6xty.wAOi8wC0jE3bZtuAkvLm', '2023-03-01 13:45:13.362387', 100),
(106, 'about0', 'city0', '0@gmail.com', 'fullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.42879', 'https://github.com/0', '0.com', 'https://vk.com/0', 'nickname0', '$2a$10$i/0lEwj/muRl8KlMwOxfB.ZrWdn2wGdf2hXk0zDdv1QINHQ/06FLm', '2023-03-01 13:45:13.42879', 200),
(107, 'about1', 'city1', '1@gmail.com', 'fullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.496799', 'https://github.com/1', '1.com', 'https://vk.com/1', 'nickname1', '$2a$10$n407wDJOGs5GSAV/hSpTReJf3mCGQFCljB3J5P8ejto0Sxc5UNKWa', '2023-03-01 13:45:13.496799', 200),
(108, 'about2', 'city2', '2@gmail.com', 'fullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.563375', 'https://github.com/2', '2.com', 'https://vk.com/2', 'nickname2', '$2a$10$740WgJpe4J06777v8sMD6exrcjQyafTLNvN56ilAXxF5GqxfKqgjC', '2023-03-01 13:45:13.563375', 200),
(109, 'about3', 'city3', '3@gmail.com', 'fullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.631383', 'https://github.com/3', '3.com', 'https://vk.com/3', 'nickname3', '$2a$10$6vYbC2Bb8jHtY8mMmPTTo.79Y1ckReyzVQsr10CboktgsoxCxhIFO', '2023-03-01 13:45:13.631383', 200),
(110, 'about4', 'city4', '4@gmail.com', 'fullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.69844', 'https://github.com/4', '4.com', 'https://vk.com/4', 'nickname4', '$2a$10$9RTuQcqtHMt6J9i7ONyznelYVRCrRp6Z3zKmiUg0ulgULyjs0XIem', '2023-03-01 13:45:13.69844', 200),
(111, 'about5', 'city5', '5@gmail.com', 'fullname5', 'https://img.com/5', false, true, '2023-03-01 13:45:13.76366', 'https://github.com/5', '5.com', 'https://vk.com/5', 'nickname5', '$2a$10$STxp2IlLSM1EJYG3/1ynuemJe7wvROVBiVJLrkW7GnLGikLW1IXxC', '2023-03-01 13:45:13.76366', 200)
;

INSERT INTO public.reputation ("id", "count", "type", "author_id") VALUES
(101, 15, 1, 101),
(102, 15, 1, 102),
(103, 15, 1, 103),
(104, 15, 1, 104),
(105, 15, 1, 105),
(106, 15, 1, 106),
(107, 15, 1, 107),
(108, 15, 1, 108),
(109, 15, 1, 109),
(110, 15, 1, 110),
(111, 15, 1, 111)
;

INSERT INTO public.question (id, description, is_deleted, last_redaction_date, persist_date, title, user_id) VALUES
(101, 'Asked by user id 101', false, '2023-03-11 17:18:55.618418', '2023-03-11 17:18:55.618418', 'question user id 101', 101),
(102, 'Asked by user id 102', false, '2023-03-11 17:18:55.66548', '2023-03-11 17:18:55.66548', 'question user id 102', 102),
(103, 'Asked by user id 103', false, '2023-03-11 17:18:55.704083', '2023-03-11 17:18:55.704083', 'question user id 103', 103),
(104, 'Asked by user id 104', false, '2023-03-11 17:18:55.723817', '2023-03-11 17:18:55.723817', 'question user id 104', 104),
(105, 'Asked by user id 105', false, '2023-03-11 17:18:55.740637', '2023-03-11 17:18:55.740637', 'question user id 105', 105),
(106, 'Asked by user id 106', false, '2023-03-11 17:18:55.753564', '2023-03-11 17:18:55.753564', 'question user id 106', 106),
(107, 'Asked by user id 107', false, '2023-03-11 17:18:55.765247', '2023-03-11 17:18:55.765247', 'question user id 107', 107),
(108, 'Asked by user id 108', false, '2023-03-11 17:18:55.77676', '2023-03-11 17:18:55.77676', 'question user id 108', 108),
(109, 'Asked by user id 109', false, '2023-03-11 17:18:55.786738', '2023-03-11 17:18:55.786738', 'question user id 109', 109),
(110, 'Asked by user id 110', false, '2023-03-11 17:18:55.797092', '2023-03-11 17:18:55.797092', 'question user id 110', 110)
;

INSERT INTO public.votes_on_questions (id, persist_date, vote, question_id, user_id) VALUES
(101, '2023-03-12 17:18:55.753564', 'up', 101, 101),
(102, '2023-03-12 17:18:55.753564', 'down', 102, 102),
(103, '2023-03-12 17:18:55.753564', 'up', 103, 103),
(104, '2023-03-12 17:18:55.753564', 'down', 104, 104),
(105, '2023-03-12 17:18:55.753564', 'up', 105, 105),
(106, '2023-03-12 17:18:55.753564', 'down', 106, 106),
(107, '2023-03-12 17:18:55.753564', 'up', 107, 107),
(108, '2023-03-12 17:18:55.753564', 'down', 108, 108),
(109, '2023-03-12 17:18:55.753564', 'up', 109, 109),
(110, '2023-03-12 17:18:55.753564', 'down', 110, 110)
;

INSERT INTO public.question_has_tag (question_id, tag_id) VALUES
(101, 101),
(102, 101),
(102, 102),
(102, 103),
(102, 104),
(103, 101),
(103, 102),
(103, 103),
(104, 101),
(104, 102),
(105, 102),
(106, 101),
(107, 101),
(107, 102),
(108, 102),
(108, 103),
(109, 104),
(110, 101)
;