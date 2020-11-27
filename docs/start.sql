
{CALL createPermit('create user')};
{CALL createPermit('read user')};
{CALL createPermit('update user')};
{CALL createPermit('delete user')};

{CALL createPermit('create product')};
{CALL createPermit('read product')};
{CALL createPermit('update product')};
{CALL createPermit('delete product')};

{CALL createPermit('create order')};
{CALL createPermit('read order')};
{CALL createPermit('update order')};
{CALL createPermit('delete order')};

{CALL createPermit('create purchase')};
{CALL createPermit('read purchase')};
{CALL createPermit('update purchase')};
{CALL createPermit('delete purchase')};

{CALL createRole('admin','admin for the app')};
{CALL createRole('provider', 'supply provider')};
{CALL createRole('user', 'standard user')};

{CALL assignPermitToRoles('admin',1)}; /*create user */
{CALL assignPermitToRoles('admin',2)}; /*read user   */
{CALL assignPermitToRoles('admin',3)}; /*update user */
{CALL assignPermitToRoles('admin',4)}; /*delete user */

{CALL assignPermitToRoles('admin',5)}; /*create product */
{CALL assignPermitToRoles('admin',6)}; /*read product   */
{CALL assignPermitToRoles('admin',7)}; /*update product */
{CALL assignPermitToRoles('admin',8)}; /*delete product */

{CALL assignPermitToRoles('admin',9)};  /*create purchase */
{CALL assignPermitToRoles('admin',10)}; /*read purchase   */
{CALL assignPermitToRoles('admin',11)}; /*update purchase */
{CALL assignPermitToRoles('admin',12)}; /*delete purchase */

/* TODO: Agregar permisos en el futuro
{CALL assignPermitToRoles('provider',)};
{CALL assignPermitToRoles('user',)};
*/

{CALL createUser('email','encryptPassword','name','last-name')};

/*   ADMIN erick@admin.com - admin    */
INSERT INTO users (email,password,name,last_name)
       VALUES ('erick@admin.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','erick','giancarlo');
INSERT INTO role_user (role_id,user_id)
       VALUES ('admin','erick@admin.com');

/*Products*/
{CALL createCategoryProduct('pasteles')};
{CALL createCategoryProduct('galletas')};
{CALL createCategoryProduct('postres')};
{CALL createCategoryProduct('panes')};

{CALL createWarehouse('almacen1')};
{CALL createWarehouse('almacen2')};

{CALL createLotProduct('2021-05-05',100,1)};

{CALL createProduct('Pastel de fresa', 50.0, 1 ,1)}
{CALL createProduct('Pastel de chocolate', 60.0, 1 ,1)};

/*Orders*/

{CALL createOrder(1, 'Compra test')};

{CALL createOrderDetails(1, 10, 1};
{CALL createOrderDetails(2, 5, 1};


/*Supplies*/
{CALL createCategorySupply('harina')};
{CALL createCategorySupply('mantequilla')};
{CALL createCategorySupply('chocolate')};
{CALL createCategorySupply('vainilla')};
{CALL createCategorySupply('huevos')};

{CALL createLotSupply('2021-02-05',100,1)};

{CALL createSupplies('harina blanca flor',1,1)}
{CALL createSupplies('huevos calera',5,1)}

/*Providers*/
{CALL createProvider('Gloria' ,'12345678902')}
{CALL createProvider('Nestle' ,'12345678901')}
