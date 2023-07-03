alter table single_chat
    alter column use_two_id drop not null,
    alter column user_one_id drop not null;