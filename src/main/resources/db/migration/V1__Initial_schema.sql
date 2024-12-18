create table if not exists category
(
    id         bigint generated by default as identity
        primary key,
    image_url  varchar(255),
    name       varchar(255) not null,
    created_at timestamp(6),
    updated_at timestamp(6)
);

create table if not exists product
(
    id          bigint generated by default as identity
        primary key,
    created_at  timestamp(6),
    description varchar(255),
    image_url   varchar(255),
    name        varchar(255)     not null,
    price       double precision not null,
    category_id bigint
        constraint fk_product_category
            references category,
    updated_at  timestamp(6)
);

create table if not exists users
(
    id         bigint generated by default as identity
        primary key,
    created_at timestamp(6),
    email      varchar(255) not null,
    password   varchar(255) not null,
    role       varchar(255) not null
        constraint chk_users_role
            check (role IN ('USER', 'ADMIN')),
    username   varchar(255)
);

create table if not exists cart
(
    id          bigint generated by default as identity
        primary key,
    total_price numeric(38, 2),
    user_id     bigint unique
        constraint fk_cart_user
            references users
);

create table if not exists cart_item
(
    id         bigint generated by default as identity
        primary key,
    price      numeric(38, 2),
    quantity   integer,
    cart_id    bigint
        constraint fk_cart_item_cart
            references cart,
    product_id bigint
        constraint fk_cart_item_product
            references product
);

create table if not exists orders
(
    id          bigint generated by default as identity
        primary key,
    order_date  timestamp(6)     not null,
    total_price double precision not null,
    user_id     bigint           not null
        constraint fk_order_user
            references users
);

create table if not exists order_item
(
    id         bigint generated by default as identity
        primary key,
    price      double precision not null,
    quantity   integer          not null,
    order_id   bigint           not null
        constraint fk_order_item_order
            references orders,
    product_id bigint           not null
        constraint fk_order_item_product
            references product
);

create table if not exists orders_items
(
    order_id bigint not null
        constraint fk_orders_items_order
            references orders,
    items_id bigint not null unique
        constraint fk_orders_items_cart_item
            references cart_item
);
