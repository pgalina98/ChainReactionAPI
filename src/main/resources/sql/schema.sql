DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user
(
    id_user BIGSERIAL PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE
);

DROP TABLE IF EXISTS public.product;
CREATE TABLE public.product
(
    id_product BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    model VARCHAR(255),
    assist_speed INTEGER,
    battery_range INTEGER,
    charging_time FLOAT,
    weight FLOAT,
    price DECIMAL(12,2) NOT NULL,
    color SMALLINT,
    available_quantity BIGINT NOT NULL,
    type SMALLINT NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
);