insert into authors(name) values ('Pushkin');

insert into genres(name) values ('Horror');

insert into books(name, author_id, genre_id) values ('Book 1', 1, 1);

insert into books_comments(comment, book_id) values ('Ololo', 1);
