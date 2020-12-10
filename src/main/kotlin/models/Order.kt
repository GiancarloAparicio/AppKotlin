package models

import javafx.scene.control.TableColumn

class Order(
            val id :Int,
            var product : String,
            var quantity : Int,
            var price : Double,
            var subTotal : Double,
            var actions :  String
            )
{

}