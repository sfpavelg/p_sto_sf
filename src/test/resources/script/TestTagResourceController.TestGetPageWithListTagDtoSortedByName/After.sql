ALTER TABLE public.tag DISABLE TRIGGER ALL;
DELETE FROM public.tag;
ALTER TABLE public.tag ENABLE TRIGGER ALL;
DELETE FROM public.user_entity;
DELETE FROM public.role;