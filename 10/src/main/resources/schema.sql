create table GENRES(
    id bigserial,
    NAME varchar(255),
    primary key (id)
);

create table AUTHORS(
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table BOOKS(
      id bigserial,
      name varchar(255),
      genre_id bigint references GENRES (id),
      author_id bigint references AUTHORS (id),
      primary key (id)
);

create table books_comments(
    id bigserial,
    book_id bigint references books(id) on delete cascade,
    comment varchar(4000),
    primary key (id)
);