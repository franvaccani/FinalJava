CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(75) NOT NULL,
    lastname VARCHAR(75) NOT NULL,
    docnumber VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(150) NOT NULL,
    code VARCHAR(50) UNIQUE NOT NULL,
    stock INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE invoice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE invoice_details (
    invoice_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    priceAtPurchase DOUBLE NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
