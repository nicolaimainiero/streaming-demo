DROP TABLE IF EXISTS "author";
DROP SEQUENCE IF EXISTS "author_id_seq";

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS author_id_seq;

-- Table Definition
CREATE TABLE "public"."author" (
    "id" int8 NOT NULL DEFAULT nextval('author_id_seq'::regclass),
    "first_name" varchar(255),
    "last_name" varchar(255),
    "year_of_birth" int4,
    PRIMARY KEY ("id")
);