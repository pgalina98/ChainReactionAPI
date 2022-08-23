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
        CONSTRAINT rent_product_bike_id_product_fk
            REFERENCES public.product,
    id_user BIGINT NOT NULL
        CONSTRAINT rent_user_id_user_fk
            REFERENCES public.user,
    id_helmet BIGINT NOT NULL
        CONSTRAINT rent_product_helmet_id_product_fk
            REFERENCES public.product,
    helmet_size SMALLINT NOT NULL,
    location SMALLINT NOT NULL,
    date DATE NOT NULL,
    active_from TIME NOT NULL,
    active_to TIME NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.notification;
CREATE TABLE public.notification
(
    id_notification BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL
        CONSTRAINT notification_user_id_user_fk
            REFERENCES public.user,
    notification_title TEXT NOT NULL,
    notification_text TEXT NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.discount_code;
CREATE TABLE public.discount_code
(
    id_discount_code BIGSERIAL PRIMARY KEY,
    code TEXT NOT NULL UNIQUE,
    discount DOUBLE PRECISION NOT NULL,
    active_from DATE NOT NULL,
    active_to DATE NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.order;
CREATE TABLE public.order
(
    id_order BIGSERIAL PRIMARY KEY,
    buyer VARCHAR(100) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    delivery_type SMALLINT NOT NULL,
    city VARCHAR(100),
    address VARCHAR(100),
    zip_code VARCHAR(100),
    payment_method SMALLINT NOT NULL,
    card_number VARCHAR(100),
    expiration_date VARCHAR(100),
    cardholder VARCHAR(100),
    cvv VARCHAR(100),
    id_discount_code BIGINT
        CONSTRAINT order_discount_code_id_discount_code_fk
            REFERENCES public.discount_code,
    total DOUBLE PRECISION NOT NULL,
    created_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_by VARCHAR(100) DEFAULT CURRENT_USER NOT NULL,
    modified_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.order_product_map;
CREATE TABLE public.order_product_map
(
    id_order_product_map BIGSERIAL PRIMARY KEY,
    id_product BIGINT NOT NULL,
    id_order BIGINT NOT NULL,
    quantity SMALLINT
);
