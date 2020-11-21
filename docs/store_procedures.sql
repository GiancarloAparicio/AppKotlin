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
    @password VARCHAR(32),
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

CREATE PROC createWharehouse(@name VARCHAR(30))
AS BEGIN
    INSERT INTO wharehouses (name)
        VALUES (@name);
    
    SELECT * FROM wharehouses;
END;

CREATE PROC createLot(@expires DATE,@wharehouse INT )
AS BEGIN
    INSERT INTO lots (expires_at, wharehouse_id)
        VALUES (@expires,@wharehouse);

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


CREATE PROC createPurcharse(
    @provider_id INT,
    @description VARCHAR(100)
 )
AS BEGIN
    DECLARE @identity INT;
    INSERT INTO purcharses (provider_id  ,description)
        VALUES (@provider_id, @description );

    SET @identity = SCOPE_IDENTITY();

    SELECT * from purcharses WHERE id = @identity;

END;


CREATE PROC createPurcharseDetails(@purcharse_id INT, @quantity SMALLINT, @supply_id INT)
AS BEGIN
    INSERT INTO purcharse_supply (quantity,purcharse_id,supply_id)
        VALUES (@quantity,@purcharse_id,@supply_id);

END;


CREATE PROC createSupplies(@name VARCHAR(30), @category_id INT, @wharehouse_id INT)
AS BEGIN
    INSERT INTO supplies (name,category_supply_id,wharehouse_id)
        VALUES (@name, @category_id, @wharehouse_id);
END


CREATE PROC createProvider(@company VARCHAR(40), @ruc VARCHAR(11))
AS BEGIN
    INSERT INTO providers (company,ruc)
        VALUES (@company , @ruc );
END



/******EXEC*****/

EXEC createPermit 'create products'; 
EXEC createPermit 'read products'; 
EXEC createPermit 'update products'; 
EXEC createPermit 'delete products';
EXEC createPermit 'delete users'; 
EXEC createPermit 'buy products'; 


EXEC createRole 'admin', 'administrador de la app'; 
EXEC createRole 'user', 'usuario consumidor' ;


EXEC assignPermitToRoles 'admin' , 1;
EXEC assignPermitToRoles 'admin' , 2;
EXEC assignPermitToRoles 'admin' , 3;
EXEC assignPermitToRoles 'admin' , 4;
EXEC assignPermitToRoles 'admin' , 5;
EXEC assignPermitToRoles 'user' , 2;


EXEC createUser 'user@user.com', 'secret', 'Erick', 'Giancarlo' 
EXEC createUser 'user2@user.com', 'secret2', 'Erick2', 'Giancarlo2'





EXEC createWharehouse 'almacen1';
EXEC createWharehouse 'almacen2';


EXEC createCategoryProduct 'pasteles';
EXEC createCategoryProduct 'panes';
EXEC createCategoryProduct 'galletas';
EXEC createCategoryProduct 'postres';

EXEC createLot '2020-12-12', 1 ;

EXEC createProduct 'Pastel chocolate', 200.00 , 1, 1 ;
EXEC createProduct 'Pastel fresa', 180.00 , 2, 1 ;






EXEC createProvider 'gloria' ,'123456789123';
EXEC createProvider 'Nestle' ,'12345678913';


EXEC createPurcharse 2, 'Oden de Gloria'; 
EXEC createPurcharse 3, 'Oden de Nestle';
