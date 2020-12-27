package app.database.factories


fun main(){

    //Create 25 products
    var productFactory = ProductFactory()
    productFactory.create( 50 )

    //Create 20 users
    var userFactory = UserFactory()
    userFactory.create( 40 )

    //Create 3000 orders
    var orderFactory = OrderFactory()
    orderFactory.create( 3000 )
    orderFactory.assignOrderDetails()

}