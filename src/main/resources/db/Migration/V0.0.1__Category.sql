create table CATEGORY
(
    CATEGORY_ID int auto_increment not null,
    NAME        VARCHAR(255)       not null,
    constraint CATEGORY_pk
        primary key (CATEGORY_ID)
);


INSERT INTO CATEGORY (NAME)
VALUES ('ART SPOŻYWCZE');
INSERT INTO CATEGORY (NAME)
VALUES ('CHEMIA');
INSERT INTO CATEGORY (NAME)
VALUES ('MOTORYZACJA');
INSERT INTO CATEGORY (NAME)
VALUES ('DLA DOMU');
