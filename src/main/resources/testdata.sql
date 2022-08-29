INSERT INTO public.app_users (id, name, role) VALUES ('7cb014a4-c2c9-4801-bccd-373601af15c2', 'dayan', 'TEACHER');
INSERT INTO public.app_users (id, name, role) VALUES ('ad9e2fe3-4613-4410-a94a-c827e485e07f', 'tharin', 'TEACHER');
INSERT INTO public.app_users (id, name, role) VALUES ('2bbc6278-b2ce-4b02-96d7-d568ce792511', 'alexa', 'READER');
INSERT INTO public.app_users (id, name, role) VALUES ('3554c73b-2b15-4df2-b39f-3e0afee44443', 'youyi', 'READER');
INSERT INTO public.app_users (id, name, role) VALUES ('72d43163-8ebb-4c0a-a3a0-05c6ceccc8a5', 'kimgech', 'READER');

INSERT INTO public.categories (id, name) VALUES (1, 'android');
INSERT INTO public.categories (id, name) VALUES (2, 'blockchain');
INSERT INTO public.categories (id, name) VALUES (3, 'springboot');

INSERT INTO public.articles (id, description, is_published, title, teacher_id) VALUES ('dd2348f4-c62c-47e7-91cd-6877bf935e45', 'cool technology', false, 'android 13', 'ad9e2fe3-4613-4410-a94a-c827e485e07f');
INSERT INTO public.articles (id, description, is_published, title, teacher_id) VALUES ('bea55a7f-f957-4440-b29f-43c47946f01f', 'always new technology', false, 'kotlin microservices', '7cb014a4-c2c9-4801-bccd-373601af15c2');

INSERT INTO public.comments (id, caption, article_id) VALUES ('8fe51e9a-2efa-479e-be05-bfd40afee2ef', 'very cool', 'dd2348f4-c62c-47e7-91cd-6877bf935e45');
INSERT INTO public.comments (id, caption, article_id) VALUES ('a271ed6a-db5e-401c-b6f8-ec44dfd14af4', 'thanks for your help', 'dd2348f4-c62c-47e7-91cd-6877bf935e45');
INSERT INTO public.comments (id, caption, article_id) VALUES ('4331f25d-dab3-445e-b82d-ac78db998e39', 'wow, amazing', 'bea55a7f-f957-4440-b29f-43c47946f01f');
INSERT INTO public.comments (id, caption, article_id) VALUES ('7700d99c-40a2-4744-9aba-224eaa1e8f9a', 'can you make a tutorial with golang?', 'bea55a7f-f957-4440-b29f-43c47946f01f');

INSERT INTO public.article_categories (article_id, category_id) VALUES ('dd2348f4-c62c-47e7-91cd-6877bf935e45', 1);
INSERT INTO public.article_categories (article_id, category_id) VALUES ('bea55a7f-f957-4440-b29f-43c47946f01f', 3);

INSERT INTO public.user_bookmarked_articles (app_user_id, bookmarked_article_id) VALUES ('3554c73b-2b15-4df2-b39f-3e0afee44443', 'bea55a7f-f957-4440-b29f-43c47946f01f');
INSERT INTO public.user_bookmarked_articles (app_user_id, bookmarked_article_id) VALUES ('72d43163-8ebb-4c0a-a3a0-05c6ceccc8a5', 'bea55a7f-f957-4440-b29f-43c47946f01f');
INSERT INTO public.user_bookmarked_articles (app_user_id, bookmarked_article_id) VALUES ('2bbc6278-b2ce-4b02-96d7-d568ce792511', 'dd2348f4-c62c-47e7-91cd-6877bf935e45');
INSERT INTO public.user_bookmarked_articles (app_user_id, bookmarked_article_id) VALUES ('3554c73b-2b15-4df2-b39f-3e0afee44443', 'dd2348f4-c62c-47e7-91cd-6877bf935e45');

