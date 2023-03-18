ALTER TABLE public.tag DISABLE TRIGGER ALL;
DELETE FROM public.tag;
ALTER TABLE public.tag ENABLE TRIGGER ALL;
DELETE FROM public.user_entity;
DELETE FROM public.role;

INSERT INTO public.tag(id, description, name, persist_date)
VALUES (101, 'description5', 'name5', current_date),
       (102, 'description4', 'name4', current_date),
       (103, 'description3', 'name3', current_date),
       (104, 'description2', 'name2', current_date),
       (105, 'description1', 'name1', current_date);



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