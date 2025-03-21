INSERT INTO "public"."roles"("id", "name") VALUES(1, 'ROLE_USER') RETURNING "id", "name";
INSERT INTO "public"."roles"("id", "name") VALUES(2, 'ROLE_ADMIN') RETURNING "id", "name";
