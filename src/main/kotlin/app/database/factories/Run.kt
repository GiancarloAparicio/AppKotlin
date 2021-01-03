package app.database.factories


fun main(){

    //Create 50 products
    var productFactory = ProductFactory()
    productFactory.create( 50 )

    //Create 40 users
    var userFactory = UserFactory()
    userFactory.create( 40 )

    //Create 6000 orders
    var orderFactory = OrderFactory()
    orderFactory.create( 5024 )
    orderFactory.assignOrderDetails()

}