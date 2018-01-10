DROP TABLE IF EXISTS snippet;
CREATE TABLE snippet
(
    id varchar(36) NOT NULL,
    title varchar(200) NOT NULL,
    keywords varchar(250) DEFAULT NULL,
    description varchar(500) DEFAULT NULL,
    created date NOT NULL,
    modified date NOT NULL,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS language;
CREATE TABLE language
(
    id varchar(36) NOT NULL,
    name varchar(250) NOT NULL,
    syntax varchar(250) DEFAULT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS code;
CREATE TABLE code
(
    id varchar(36) NOT NULL,
    source varchar(5000) NOT NULL,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS cross_snippet_language_code;
CREATE TABLE cross_snippet_language_code
(
    snippet_id varchar(36) NOT NULL,
    language_id varchar(36) NOT NULL,
    code_id varchar(36) NOT NULL,
    PRIMARY KEY (snippet_id)
);
