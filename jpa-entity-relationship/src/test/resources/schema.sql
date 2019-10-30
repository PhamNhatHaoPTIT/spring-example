create table book_category (
    id int,
    name varchar (255),
    primary key (id)
);

create table book (
    id int,
    name varchar (255),
    book_category_id int,
    primary key (id),
    foreign key (book_category_id) references book_category (id)
);
