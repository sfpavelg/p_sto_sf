DELETE FROM question_has_tag where question_id BETWEEN 1 and 200;

DELETE FROM question where id BETWEEN 1 and 200;

DELETE FROM user_entity where id BETWEEN 100 and 200;

DELETE FROM tag where id BETWEEN 100 and 200;

DELETE FROM "role"where id BETWEEN 100 and 200;
