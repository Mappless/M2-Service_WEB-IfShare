CREATE TABLE IF NOT EXISTS user (
	user_id TEXT PRIMARY KEY,
   	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	address TEXT NOT NULL,
	mail TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    product_id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    description NOT NULL,
    available INTEGER (available IN (0, 1)) NOT NULL
);

CREATE TABLE IF NOT EXISTS offer (
    offer_id INTEGER PRIMARY KEY AUTOINCREMENT,
    seller_id TEXT NOT NULL,
    product_id TEXT NOT NULL,
    product_state TEXT CHECK(product_state IN ('VERY_GOOD', 'GOOD', 'NORMAL', 'BAD', 'VERY_BAD')) NOT NULL,
    price REAL CHECK(price >= 0) NOT NULL,
    quantity INTEGER CHECK(quantity >= 0),
    available INTEGER CHECK (available IN (0, 1)) NOT NULL,
    FOREIGN KEY(seller_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE IF NOT EXISTS purchase (
    client_id INTEGER,
    offer_id INTEGER,
    quantity INTEGER CHECK(quantity > 0),
    status TEXT CHECK(status IN ('BOOKED', 'WAITING', 'SENT')),
    PRIMARY KEY (command_id, offer_id),
    FOREIGN KEY(client_id) REFERENCES user(user_id),
    FOREIGN KEY(offer_id) REFERENCES offer(offer_id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS purchase_client_index ON purchase(client_id);
CREATE UNIQUE INDEX IF NOT EXISTS purchase_offer_index ON purchase(offer_id);