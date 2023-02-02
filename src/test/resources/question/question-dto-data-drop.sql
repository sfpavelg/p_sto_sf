DELETE FROM reputation where id BETWEEN 1 and 100;

DELETE FROM answer where id BETWEEN 1 and 100;

DELETE FROM question_viewed where id BETWEEN 1 and 100;

DELETE FROM question_has_tag where question_id BETWEEN 1 and 100;

DELETE FROM votes_on_questions where id BETWEEN 1 and 100;

DELETE FROM question where id BETWEEN 1 and 100;

DELETE FROM user_entity where id BETWEEN 1 and 100;

DELETE FROM tag where id BETWEEN 1 and 100;

DELETE FROM "role"where id BETWEEN 1 and 100;








-- INSERT INTO "question_has_tag"
-- ("question_id", "tag_id")
-- VALUES (1,1),
--        (1,2),
--        (2,3),
--        (2,4),
--        (3,5),
--        (3,6),
--        (4,7),
--        (4,8),
--        (5,9),
--        (5,10);









