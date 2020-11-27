/*
*	Author: Ramos Aparicio
*	GitHub: https://github.com/GiancarloAparicio
*/

/*******SQL Server******/

IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'cake_delivery')
BEGIN
  CREATE DATABASE cake_delivery
END;

USE cake_delivery;





IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='users' AND xtype='U' )
BEGIN
    CREATE TABLE users (
      id INT NOT NULL IDENTITY(1,1),
      email VARCHAR(100) NOT NULL CHECK (email LIKE '_%@__%.__%')  UNIQUE,
      password VARCHAR(64) NOT NULL,
      name VARCHAR(40),
      last_name VARCHAR(40),
      create_at DATE DEFAULT GETDATE(),
      update_at DATE NULL,
      delete_at DATE NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='permits' AND xtype='U')
BEGIN
    CREATE TABLE permits (
      id INT NOT NULL IDENTITY(1,1),
      accion VARCHAR(30) NOT NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='roles' AND xtype='U' )
BEGIN
    CREATE TABLE roles (
      role VARCHAR(30) NOT NULL,
      description VARCHAR(100) NOT NULL,
      PRIMARY KEY (role)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='permit_role' AND xtype='U' )
BEGIN
    CREATE TABLE permit_role (
      permit_id INT NOT NULL FOREIGN KEY REFERENCES permits(id),
      role_id VARCHAR(30) NOT NULL FOREIGN KEY REFERENCES roles(role)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='role_user' AND xtype='U' )
BEGIN
    CREATE TABLE role_user (
      role_id VARCHAR(30) NOT NULL FOREIGN KEY REFERENCES roles(role),
      user_id VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES users(email)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='orders' AND xtype='U' )
BEGIN
    CREATE TABLE orders (
      id INT NOT NULL IDENTITY(1,1),
      user_id VARCHAR(100),
      description VARCHAR(100),
      create_at DATE DEFAULT GETDATE(),
      update_at DATE NULL,
      delete_at DATE NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='warehouses' AND xtype='U' )
BEGIN
    CREATE TABLE warehouses (
      id INT NOT NULL IDENTITY(1,1),
      name VARCHAR(30) NOT NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='lots' AND xtype='U' )
BEGIN
    CREATE TABLE lots (
      id INT NOT NULL IDENTITY(1,1),
      expires_at DATE NOT NULL,
      warehouse_id INT NOT NULL FOREIGN KEY REFERENCES warehouses(id),
      PRIMARY KEY (id)
    );
END;

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='categories_products' AND xtype='U' )
BEGIN
    CREATE TABLE categories_products  (
      id INT NOT NULL IDENTITY(1,1),
      category_product VARCHAR(30) NOT NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='products' AND xtype='U' )
BEGIN
    CREATE TABLE products (
      id INT NOT NULL IDENTITY(1,1),
      name VARCHAR(30) NOT NULL,
      price MONEY NOT NULL CHECK (0<price),
      category_product_id INT NOT NULL FOREIGN KEY REFERENCES categories_products(id),
      lot_id INT NOT NULL FOREIGN KEY REFERENCES lots(id),
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='order_product' AND xtype='U' )
BEGIN
    CREATE TABLE order_product (
      quantity SMALLINT NOT NULL CHECK (0<quantity),
      order_id INT  NOT NULL FOREIGN KEY REFERENCES orders(id),
      product_id INT NOT NULL FOREIGN KEY REFERENCES products(id)
    );
END;







IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='address' AND xtype='U' )
BEGIN
    CREATE TABLE address (
      address_able_id INT NOT NULL ,
      address_able_type VARCHAR(30) NOT NULL,
      country VARCHAR(30) NOT NULL,
      city VARCHAR(30) NOT NULL,
      direction VARCHAR(30) NOT NULL,
      PRIMARY KEY (address_able_id, address_able_type)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='providers' AND xtype='U' )
BEGIN
    CREATE TABLE providers (
      id INT NOT NULL IDENTITY(1,1),
      company VARCHAR(40) NOT NULL,
      ruc VARCHAR(11) NOT NULL CHECK (LEN(ruc)=11),
      create_at DATE DEFAULT GETDATE(),
      update_at DATE NULL,
      delete_at DATE NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='images' AND xtype='U' )
BEGIN
    CREATE TABLE images (
      image_able_id INT NOT NULL,
      image_able_type VARCHAR(30) NOT NULL,
      url VARCHAR(100) NOT NULL,
      name VARCHAR(40) NOT NULL,
      create_at DATE DEFAULT GETDATE(),
      update_at DATE NULL,
      delete_at DATE NULL,
      PRIMARY KEY (image_able_id, image_able_type)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='purchases' AND xtype='U' )
BEGIN
    CREATE TABLE purchases (
      id INT NOT NULL IDENTITY(1,1),
      provider_id INT NOT NULL FOREIGN KEY REFERENCES providers(id),
      description VARCHAR(100) NOT NULL,
      create_at DATE DEFAULT GETDATE(),
      update_at DATE NULL,
      delete_at DATE NULL,
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='supplies' AND xtype='U' )
BEGIN
    CREATE TABLE supplies (
      id INT NOT NULL IDENTITY(1,1),
      name VARCHAR(30) NOT NULL,
      category_supply_id INT NOT NULL  FOREIGN KEY REFERENCES categories_supplies(id),
      warehouse_id INT NOT NULL  FOREIGN KEY REFERENCES warehouses(id),
      PRIMARY KEY (id)
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='purchase_supply' AND xtype='U' )
BEGIN
    CREATE TABLE purchase_supply (
      quantity SMALLINT NOT NULL CHECK (0<quantity),
      purchase_id INT NOT NULL FOREIGN KEY REFERENCES purchases(id),
      supply_id INT NOT NULL FOREIGN KEY REFERENCES supplies(id),
    );
END;



IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='categories_supplies' AND xtype='U' )
BEGIN
    CREATE TABLE categories_supplies (
      id INT NOT NULL IDENTITY(1,1),
      category_supply VARCHAR(30) NOT NULL,
      PRIMARY KEY (id)
    );
END;
