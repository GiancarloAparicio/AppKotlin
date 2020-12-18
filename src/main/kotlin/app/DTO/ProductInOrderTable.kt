package app.DTO

/*
* This class Order is used by the tables, so its structure should not be changed
* */
class ProductInOrderTable(
            val id :Int?,
            var product : String?,
            var quantity : Int?,
            var price : Double?,
            var subTotal : Double?,
            var actions :  String?
            )
{

}