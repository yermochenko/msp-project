DROP TABLE IF EXISTS "note";

CREATE TABLE "note" (
	"id"      SERIAL  PRIMARY KEY,
	"title"   TEXT    NOT NULL,
	"content" TEXT,
	"date"    DATE    NOT NULL,
	"done"    BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO "note"
-----------------------------------------------------
("id", "title", "content"     , "date"      , "done") VALUES
-----------------------------------------------------
(1   , 'abc'  , NULL          , '2025-08-15', FALSE ),
(2   , 'абв'  , 'test content', '2025-08-27', TRUE  ),
(3   , 'xyz'  , NULL          , '2025-09-13', FALSE ),
(4   , 'где'  , 'qwerty'      , '2025-09-29', TRUE  ),
(5   , 'mnk'  , 'asdfgh'      , '2025-09-04', FALSE );
SELECT setval('note_id_seq', 5);
