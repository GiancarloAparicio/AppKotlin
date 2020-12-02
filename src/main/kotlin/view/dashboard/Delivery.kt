package view.dashboard

import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import models.Product
import repositories.ProductRepository
import tornadofx.View

class Delivery : View() {

    override val root : BorderPane by fxml()

    @FXML
    lateinit var comboBoxProduct : ComboBox<String>

    @FXML
    lateinit var inputQuantity : TextField


    init{
        val products : MutableList<Product> =ProductRepository.getAll()

        for (product in products){
            comboBoxProduct.items.add(product.name)
        }

    }

    fun setQuantity(){
        val quantity : String = inputQuantity.text
        val regex : Regex = Regex(pattern = "^[0-9]+$")
        val quantityIsNumber : Boolean = regex.containsMatchIn(quantity)

        inputQuantity.style = if(quantityIsNumber) "-fx-text-inner-color : #2FA14C;"
                                              else "-fx-text-inner-color : #E23A2D;"

    }
}