DELETE FROM MESSAGE WHERE id BETWEEN 100 and 200;

DELETE FROM single_chat WHERE chat_id = 100;
DELETE FROM single_chat WHERE chat_id = 101;
DELETE FROM single_chat WHERE chat_id = 102;

DELETE FROM "user_entity" WHERE id BETWEEN 100 and 200;
DELETE FROM "role" WHERE id = 100;
DELETE FROM "role" WHERE id = 200;

DELETE FROM group_chat WHERE chat_id = 100;
DELETE FROM group_chat WHERE chat_id = 101;
DELETE FROM group_chat WHERE chat_id = 102;

DELETE FROM chat WHERE id = 100;
DELETE FROM chat WHERE id = 101;
DELETE FROM chat WHERE id = 102;




