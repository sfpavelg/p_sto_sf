DELETE
FROM "reputation"
WHERE id between 100 and 150;

DELETE
FROM "user_entity"
WHERE id between 100 and 150;

DELETE
FROM "role"
WHERE id in (1, 2)