/**USER***AUTH**/

CREATE PROC createPermit(@accion VARCHAR(30))
AS BEGIN
    INSERT INTO permits (accion)
        VALUES (@accion);

END;

CREATE PROC createRole(@role VARCHAR(30),@description VARCHAR(100))
AS BEGIN
     INSERT INTO roles (role,description)
        VALUES (@role,@description);

END;

CREATE PROC assignPermitToRoles(@role VARCHAR(30), @accion INT)
AS BEGIN
    INSERT INTO permit_role (permit_id,role_id)
        VALUES (@accion,@role );

    SELECT * FROM permit_role;

END;

CREATE PROC createUser(
    @email VARCHAR(100),
    @password VARCHAR(64),
    @name VARCHAR(40),
    @last_name VARCHAR(40)
    )
AS BEGIN

    INSERT INTO users (email,password,name,last_name) 
        VALUES (@email,@password,@name,@last_name);

    INSERT INTO role_user (role_id,user_id) 
        VALUES ('user',@email);

END;

CREATE PROC loginUser(@email VARCHAR(100), @password VARCHAR(64))
AS BEGIN
    SELECT TOP 1 id,email,name,last_name ,create_at ,update_at ,delete_at FROM users
    WHERE email = @email AND password = @password;
END


/**PRODUCT**/

CREATE PROC createCategoryProduct(@category VARCHAR(30))
AS BEGIN
    INSERT INTO categories_products (category_product)
        VALUES (@category);
    
    SELECT * FROM categories_products;
END;

CREATE PROC createWarehouse(@name VARCHAR(30))
AS BEGIN
    INSERT INTO warehouses (name)
        VALUES (@name);
    
    SELECT * FROM warehouses;
END;

CREATE PROC createLot(@expires DATE,@warehouse INT )
AS BEGIN
    INSERT INTO lots (expires_at, warehouse_id)
        VALUES (@expires,@warehouse);

END;

CREATE PROC createProduct(@name VARCHAR(30), @price MONEY, @category_id INT ,@lot_id INT)
AS BEGIN
    INSERT INTO products (name, price, category_product_id,lot_id)
        VALUES (@name,@price,@category_id,@lot_id);

END;


/**ORDERS**/

CREATE PROC createOrder(@user_id VARCHAR(100), @description VARCHAR(100))
AS BEGIN
    DECLARE @identity INT;
    INSERT INTO orders (user_id  ,description)
        VALUES (@user_id ,@description );

    SET @identity = SCOPE_IDENTITY();

    SELECT * from orders WHERE id = @identity;
END;


CREATE PROC createOrderDetails(@product_id INT, @quantity SMALLINT, @order_id INT)
AS BEGIN
    INSERT INTO order_product (quantity,order_id,product_id)
        VALUES (@quantity,@order_id,@product_id);

END;


/**PURCHASES**/

CREATE PROC createProvider(@company VARCHAR(40), @ruc VARCHAR(11))
AS BEGIN
    INSERT INTO providers (company,ruc)
        VALUES (@company , @ruc );
END


CREATE PROC createPurchase(
    @provider_id INT,
    @description VARCHAR(100)
 )
AS BEGIN
    DECLARE @identity INT;
    INSERT INTO purchases (provider_id  ,description)
        VALUES (@provider_id, @description );

    SET @identity = SCOPE_IDENTITY();

    SELECT * from purchases WHERE id = @identity;

END;


CREATE PROC createPurchaseDetails(@purchase_id INT, @quantity SMALLINT, @supply_id INT)
AS BEGIN
    INSERT INTO purchase_supply (quantity,purchase_id,supply_id)
        VALUES (@quantity,@purchase_id,@supply_id);

END;


CREATE PROC createSupplies(@name VARCHAR(30), @category_id INT, @warehouse_id INT)
AS BEGIN
    INSERT INTO supplies (name,category_supply_id,warehouse_id)
        VALUES (@name, @category_id, @warehouse_id);

    SELECT * FROM supplies WHERE name = @name AND category_supply_id = @category_id AND warehouse_id = @warehouse_id;
END

CREATE PROC createCategorySupply(@category VARCHAR(30))
AS BEGIN
    INSERT INTO categories_supplies (category_supply)
        VALUES (@category);

    SELECT * FROM categories_supplies WHERE category_supply = @category;
END


