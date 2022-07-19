INSERT INTO public.user (fullname, username, email, password, is_admin) VALUES ('Patrik Galina', 'pgalina', 'pgalina@chain-reaction.com', '$2a$12$pygemk1zrkmkO7u0ylov2uUz0QtAHoqW4uRoWiXaT6pu9IaBllbgS', false);
INSERT INTO public.user (fullname, username, email, password, is_admin) VALUES ('admin', 'admin', 'admin@chain-reaction.com', '$2a$12$HZU2IovUhhEvpP1mftH5qeAoKSQEvrqqDzBQSARdV.2I1MKX9ZJS.', true);

INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4', 25, 70, 3.5, 18.9, 2990.00, 1, 3, 1);
INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4', 25, 70, 3.5, 18.9, 2990.00, 2, 0, 1);
INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4', 25, 70, 3.5, 18.9, 2990.00, 3, 5, 1);
INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4ST', 20, 70, 3, 19.2, 2590.00, 1, 0, 1);
INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4ST', 20, 70, 3, 19.2, 2590.00, 2, 3, 1);
INSERT INTO public.product (name, model, assist_speed, battery_range, charging_time, weight, price, color, available_quantity, type) VALUES ('Cowboy', '4ST', 20, 70, 3, 19.2, 2590.00, 3, 0, 1);