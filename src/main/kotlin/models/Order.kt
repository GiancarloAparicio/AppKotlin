package models

import javafx.scene.control.TableColumn

class Order {
    var id :Int = 0
    lateinit var product : String
    var quantity : Int = 0
    var price : Double = 0.0
    var subTotal : Double = 0.0
    lateinit var actions :  String
}