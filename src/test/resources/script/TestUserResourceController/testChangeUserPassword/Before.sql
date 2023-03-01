INSERT INTO "role"
("id", "name")
VALUES (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', '4@gmail.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$10$GoxB/NSJ2y9tVnrSdmn9Ce7ExVT5aAgdBKgCNsDR4lz/ZcVrbOqTG', '2023-01-21 15:17:18.280368', 200);