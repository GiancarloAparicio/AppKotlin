/**USER***AUTH**/

CREATE PROC createPermit(@accion VARCHAR(30))
AS BEGIN
    INSERT INTO permits (accion)
        VALUES (@accion);

    SELECT SCOPE_IDENTITY();

END;

CREATE PROC createRole(@role VARCHAR(30),@description VARCHAR(100))
AS BEGIN
     INSERT INTO roles (role,description)
        VALUES (@role,@description);

     SELECT SCOPE_IDENTITY();

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

	DECLARE @identity INT

    INSERT INTO users (email,password,name,last_name) 
        VALUES (@email,@password,@name,@last_name);

    SET @identity = SCOPE_IDENTITY();

    INSERT INTO role_user (role_id,email)
        VALUES ('user',@email);

    SELECT @identity;

END;

CREATE PROC loginUser(@email VARCHAR(100), @password VARCHAR(64))
AS BEGIN
    SELECT TOP 1 email,name,last_name ,create_at ,update_at ,delete_at FROM users
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

CREATE PROC createLotProduct(@expires DATE,@warehouse INT,@quantity SMALLINT)
AS BEGIN
    INSERT INTO products_lots (expires_at, quantity, warehouse_id)
        VALUES (@expires,@quantity,@warehouse);

    SELECT SCOPE_IDENTITY();

END;

CREATE PROC createProduct(@name VARCHAR(30), @price MONEY, @category_id INT ,@lot_id INT, @url VARCHAR(100) = NULL )
AS BEGIN

    DECLARE @identity INT;
    INSERT INTO products (name, price, category_product_id,lot_id)
        VALUES (@name,@price,@category_id,@lot_id);

    SET @identity = SCOPE_IDENTITY();

    IF @url IS NULL

        INSERT INTO images (image_able_id, image_able_type, url, name)
            VALUES (@identity,'product','@/../pictures/images/cup-cake.png','default')
    ELSE
        INSERT INTO images (image_able_id, image_able_type, url, name)
                    VALUES (@identity,'product',@url ,'product-image-'+@identity)

    SELECT @identity

END;

CREATE PROC getAllProducts
AS BEGIN
    SELECT products.id,
               images.url,
               products.name,
               products.price,
               categories_products.category_product,
               products_lots.quantity,
               products_lots.expires_at
        	FROM products JOIN categories_products ON products.category_product_id = categories_products.id
        	              JOIN products_lots ON products_lots.id = products.lot_id
        	              LEFT JOIN images ON products.id = images.image_able_id
        	              WHERE images.image_able_type = 'product'
END;

CREATE PROC getFilteredProducts( @category VARCHAR(30) )
AS BEGIN
    SELECT products.id,
        images.url,
        products.name,
        products.price,
        categories_products.category_product,
        products_lots.quantity,
        products_lots.expires_at
     FROM products JOIN categories_products ON products.category_product_id = categories_products.id
            	   JOIN products_lots ON products_lots.id = products.lot_id
            	   LEFT JOIN images ON products.id = images.image_able_id
            	   WHERE images.image_able_type = 'product' AND categories_products.category_product = @category;
END;


CREATE PROC getAllProductsCategories
AS BEGIN
    SELECT * FROM categories_products;
END;


CREATE PROC getProduct(@nameProduct VARCHAR(30))
AS BEGIN
    SELECT products.id,
           images.url,
           products.name,
           products.price,
           categories_products.category_product,
           products_lots.quantity,
           products_lots.expires_at
    	FROM products JOIN categories_products ON products.category_product_id = categories_products.id
    	              JOIN products_lots ON products_lots.id = products.lot_id
    	              LEFT JOIN images ON products.id = images.image_able_id
    	              WHERE products.name = @nameProduct AND images.image_able_type = 'product';
END;

CREATE PROC getLatestProductsAdded
AS BEGIN
    SELECT TOP 5 products.id,
           images.url,
           products.name,
           products.price,
           categories_products.category_product,
           products_lots.quantity,
           products_lots.expires_at
    	FROM products JOIN categories_products ON products.category_product_id = categories_products.id
            	      JOIN products_lots ON products_lots.id = products.lot_id
            	      LEFT JOIN images ON products.id = images.image_able_id
            	      WHERE images.image_able_type = 'product'
            	      ORDER BY products.id DESC
END;


/**ORDERS**/

CREATE PROC createOrder(@email VARCHAR(100), @description VARCHAR(100))
AS BEGIN
    DECLARE @identity INT;
    INSERT INTO orders (email  ,description)
        VALUES (@email ,@description );

    SET @identity = SCOPE_IDENTITY();

    SELECT * from orders WHERE id = @identity;
END;


CREATE PROC createOrderDetails(@product_id INT, @quantity SMALLINT, @order_id INT)
AS BEGIN
    INSERT INTO order_product (quantity,order_id,product_id)
        VALUES (@quantity,@order_id,@product_id);

    SELECT SCOPE_IDENTITY();

END;


/**PURCHASES**/

CREATE PROC createProvider(@company VARCHAR(40), @ruc VARCHAR(11))
AS BEGIN
    INSERT INTO providers (company,ruc)
        VALUES (@company , @ruc );

    SELECT SCOPE_IDENTITY();

END

CREATE PROC createCategorySupply(@category VARCHAR(30))
AS BEGIN
    INSERT INTO categories_supplies (category_supply)
        VALUES (@category);

    SELECT * FROM categories_supplies WHERE category_supply = @category;
END

CREATE PROC createLotSupply(@expires DATE,@warehouse INT,@quantity SMALLINT)
AS BEGIN
    INSERT INTO supplies_lots (expires_at, quantity, warehouse_id)
        VALUES (@expires,@quantity,@warehouse);

    SELECT SCOPE_IDENTITY()

END;

CREATE PROC createSupplies(@name VARCHAR(30), @category_id INT, @lot_id INT)
AS BEGIN
    INSERT INTO supplies (name,category_supply_id,lot_id)
        VALUES (@name, @category_id, @lot_id);

    SELECT * FROM supplies WHERE name = @name AND category_supply_id = @category_id AND lot_id = @lot_id;
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

    SELECT SCOPE_IDENTITY()

END;

