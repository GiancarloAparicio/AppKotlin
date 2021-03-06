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


   SELECT users.email,
       		users.name,
       		users.last_name,
       		users.create_at,
       		users.update_at,
       		users.create_at,
       		string_agg( role_id , ',' ) AS roles
       		FROM users JOIN role_user ON users.email = role_user.email
       		WHERE users.email = @email
       		GROUP BY users.email,
       				users.name,
       				users.last_name,
       				users.create_at,
       				users.update_at,
       				users.create_at
END;

CREATE PROC assignRoleToUser(@email VARCHAR(100), @role VARCHAR(30))
AS BEGIN
    INSERT INTO role_user (role_id,email)
            VALUES (@role,@email);

      SELECT users.email,
          		users.name,
          		users.last_name,
          		users.create_at,
          		users.update_at,
          		users.create_at,
          		string_agg( role_id , ',' ) AS roles
          		FROM users JOIN role_user ON users.email = role_user.email
          		WHERE users.email = @email
          		GROUP BY users.email,
          				users.name,
          				users.last_name,
          				users.create_at,
          				users.update_at,
          				users.create_at
END

CREATE PROC loginUser(@email VARCHAR(100), @password VARCHAR(64))
AS BEGIN
       SELECT users.email,
           		users.name,
           		users.last_name,
           		users.create_at,
           		users.update_at,
           		users.create_at,
           		string_agg( role_id , ',' ) AS roles
           		FROM users JOIN role_user ON users.email = role_user.email
           		WHERE users.email = @email AND users.password = @password
           		GROUP BY users.email,
           				users.name,
           				users.last_name,
           				users.create_at,
           				users.update_at,
           				users.create_at

END;

CREATE PROC getAllUsers
AS BEGIN

   SELECT users.email,
       		users.name,
       		users.last_name,
       		users.create_at,
       		users.update_at,
       		users.create_at,
       		string_agg( role_id , ',' ) AS roles
       		FROM users JOIN role_user ON users.email = role_user.email
       		GROUP BY users.email,
       				users.name,
       				users.last_name,
       				users.create_at,
       				users.update_at,
       				users.create_at
END;


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
            	              WHERE images.image_able_type = 'product' AND products.id = @identity

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
    SELECT TOP 7 products.id,
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


CREATE PROC createOrderTest(@email VARCHAR(100), @description VARCHAR(100), @created DATE)
AS BEGIN
    DECLARE @identity INT;
    INSERT INTO orders (email  ,description, create_at)
        VALUES (@email ,@description, @created );

    SET @identity = SCOPE_IDENTITY();

    SELECT * from orders WHERE id = @identity;
END;


CREATE PROC createOrderDetails(@product_id INT, @quantity SMALLINT, @order_id INT)
AS BEGIN
    INSERT INTO order_product (quantity,order_id,product_id)
        VALUES (@quantity,@order_id,@product_id);

    SELECT orders.id,
           		orders.email,
           		SUM( order_product.quantity * products.price ) AS total,
           		orders.create_at,
           		orders.update_at,
           		orders.description,
           		orders.delete_at
           		FROM orders JOIN order_product ON orders.id = order_product.order_id
           					JOIN products ON products.id = order_product.product_id
           					WHERE orders.delete_at IS NULL AND orders.id = @order_id
           					GROUP BY orders.id,
           							 orders.email,
           							 orders.description,
           							 orders.create_at,
           							 orders.update_at,
           							 orders.delete_at
           				    ORDER BY orders.id ASC

END;

CREATE PROC getAllOrders
AS BEGIN

    SELECT orders.id,
       		orders.email,
       		SUM( order_product.quantity * products.price ) AS total,
       		orders.create_at,
       		orders.update_at,
       		orders.description,
       		orders.delete_at
       		FROM orders JOIN order_product ON orders.id = order_product.order_id
       					JOIN products ON products.id = order_product.product_id
       					WHERE orders.delete_at IS NULL
       					GROUP BY orders.id,
       							 orders.email,
       							 orders.description,
       							 orders.create_at,
       							 orders.update_at,
       							 orders.delete_at
       				    ORDER BY orders.id ASC
END;

CREATE PROC softDeleteOrder(@id INT)
AS BEGIN

    UPDATE orders
        SET delete_at = GETDATE()
        WHERE id = @id;

    SELECT * from orders WHERE id = @id;
END;

CREATE PROC filterOrdersByDate( @date VARCHAR(30) )
AS BEGIN
     SELECT orders.id,
                		orders.email,
                		SUM( order_product.quantity * products.price ) AS total,
                		orders.create_at,
                		orders.update_at,
                		orders.description,
                		orders.delete_at
                		FROM orders JOIN order_product ON orders.id = order_product.order_id
                					JOIN products ON products.id = order_product.product_id
                					WHERE orders.delete_at IS NULL AND
                					orders.create_at BETWEEN @date AND GETDATE()
                					GROUP BY orders.id,
                							 orders.email,
                							 orders.description,
                							 orders.create_at,
                							 orders.update_at,
                							 orders.delete_at
                				    ORDER BY orders.create_at ASC;
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

