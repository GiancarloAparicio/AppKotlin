package app.database.factories

import app.DAO.ProductDAO
import app.DAO.UserDAO
import app.database.factories.abstract.Factory
import app.models.Order
import app.models.Product
import app.models.User
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.util.*


class OrderFactory : Factory() {

    override fun create(quantity : Int ): Any {

        var listOrders = mutableListOf<Order>()
        var listUsers : MutableList<User> = UserDAO.getAll()

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var minDate : Date = GregorianCalendar(2000, Calendar.JANUARY, 1).getTime()
        var maxDate : Date = GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime()


        try {
            for(item in 1..quantity){
                var indexUser : Int= faker.number().numberBetween( 1, 42 )

                var user : User = listUsers.get( indexUser )
                var description = "Cake Delivery"
                var created : Date = faker.date().between(minDate, maxDate)

                var storeProcedure = "{CALL createOrderTest(?,?,?)}"
                val params : Array<Any?> = arrayOf(
                                                user.email,
                                                description,
                                                sdf.format( created.time )
                                            )
                val data: ResultSet = dataBase.execStoreProcedure( storeProcedure, params )

                if ( data.next() ) {
                    listOrders.add( Order( data,true ) )
                }
            }
        }catch (e:Error){
            println("Error: Constraint UNIQUE")
        }

        return listOrders
    }

    fun assignOrderDetails(): MutableList<Order> {
        var listOrder = getOrderListEmpty()
        var listProduct = ProductDAO.getAll()

        var storeProcedure = "{CALL createOrderDetails(?,?,?)}"

        var newListOrder = mutableListOf<Order>()

        for ( order in listOrder ){
            var indexProduct : Int= faker.number().numberBetween( 1, 42 )

            var product : Product = listProduct.get( indexProduct )
            var quantity : Int= faker.number().numberBetween( 1, 10 )

            val params : Array<Any?> = arrayOf(
                        product.id,
                        quantity,
                        order.id
            )

            val data: ResultSet = dataBase.execStoreProcedure( storeProcedure ,params )

            while( data.next() ) {
                newListOrder.add( Order( data ) )
            }
        }

        return newListOrder
    }

    private fun getOrderListEmpty(): MutableList<Order> {
        var query = "select orders.*, \n" +
                "       order_product.order_id\n" +
                "       from orders \n" +
                "       left join order_product on orders.id = order_product.order_id \n" +
                "       where order_product.order_id IS NULL"

        var listOrders = mutableListOf<Order>()
        val data: ResultSet = dataBase.execStoreProcedure( query )

        while( data.next() ) {
            listOrders.add( Order( data,true ) )
        }

        return listOrders
    }

}