DROP TABLE IF EXISTS public.user CASCADE;
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

DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product
(
    id_product BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    model VARCHAR(255),
    assist_speed INTEGER,
    battery_range INTEGER,
    charging_time DOUBLE PRECISION,
    weight DOUBLE PRECISION,
    price DOUBLE PRECISION NOT NULL,
    color SMALLINT,
    available_quantity INTEGER NOT NULL,
    for_rent BOOLEAN DEFAULT FALSE NOT NULL,
    rent_price_per_hour DOUBLE PRECISION,
    image_path VARCHAR(255) NOT NULL,
    type SMALLINT NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.rent;
CREATE TABLE public.rent
(
    id_rent BIGSERIAL PRIMARY KEY,
    id_product BIGINT NOT NULL
        CONSTRAINT rent_product_id_product_fk
            REFERENCES public.product,
    id_user BIGINT NOT NULL
        CONSTRAINT rent_user_id_user_fk
            REFERENCES public.user,
    location BIGINT NOT NULL,
    date DATE NOT NULL,
    active_from TIME NOT NULL,
    active_to TIME NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);