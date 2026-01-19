
CREATE TABLE IF NOT EXISTS dish (
    id_dish SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dish_type VARCHAR(50) NOT NULL,
    selling_price NUMERIC
);

-- Créer la table DishIngredient
CREATE TABLE IF NOT EXISTS dish_ingredient (
    id_serial SERIAL PRIMARY KEY,
    id_dish INT NOT NULL,
    id_ingredient INT NOT NULL,
    quantity_required NUMERIC NOT NULL,
    unit VARCHAR(50),
    FOREIGN KEY (id_dish) REFERENCES dish(id_dish),
    FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient)
);

-- Vider les tables
DELETE FROM dish_ingredient;
DELETE FROM dish;

-- Insérer les plats exacts
INSERT INTO dish (name, dish_type, selling_price) VALUES
    ('Salade fraiche', 'START', 3500.00),
    ('Poulet grillé', 'MAIN', 12000.00),
    ('Riz aux légumes', 'MAIN', NULL),
    ('Gâteau au chocolat', 'DESSERT', 8000.00),
    ('Salade de fruits', 'DESSERT', NULL);

-- Insérer les relations exactes du DishIngredient
INSERT INTO dish_ingredient (id_dish, id_ingredient, quantity_required, unit) VALUES
    (1, 1, 0.20, 'KG'),
    (1, 2, 0.15, 'KG'),
    (2, 3, 1.00, 'KG'),
    (4, 4, 0.30, 'KG'),
    (4, 5, 0.20, 'KG');

-- Mettre à jour les prix pour que les coûts correspondent aux résultats attendus
UPDATE ingredient SET price = 1250 WHERE id_ingredient = 1;
UPDATE ingredient SET price = 0 WHERE id_ingredient = 2;
UPDATE ingredient SET price = 4500 WHERE id_ingredient = 3;
UPDATE ingredient SET price = 4000 WHERE id_ingredient = 4;
UPDATE ingredient SET price = 1000 WHERE id_ingredient = 5;
