package view.home.dashboard._home.components

import app.models.Product
import javafx.scene.text.FontWeight
import tornadofx.*

class LastProductAdded ( data : Product ) : Fragment() {

     var url : String = data.url
     var product : String = data.name
     var price: Double = data.price
     var description : String = "Description"

    //TODO: Agregar el campo description en la tabla products, actualizar los store procedures tambien

    companion object{
        fun convertListToComponents( list :MutableList<Product> ) : MutableList<LastProductAdded> {

            var componentsLatestProducts : MutableList<LastProductAdded> = mutableListOf();

            for( product in list){
                componentsLatestProducts.add( LastProductAdded( product ) )
            }

            return componentsLatestProducts
        }
    }

    override val root = hbox {

        imageview( url, lazyload = false  ) {
            fitHeight = 40.0
            fitWidth = 40.0

        }

        vbox {
            hbox {
                label ( product ) {
                    prefWidth = 150.0
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        textFill = c("#1ba0eb")
                        fontSize = 12.px
                    }

                }

                label ("$${price.toString()}") {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        textFill = c("#ffffff")
                        backgroundColor += c("#2FA14C")
                        padding = box(2.px)
                        fontSize = 10.px
                    }
                }

                prefHeight = 20.0
            }

            label ( description ) {
                style {
                    textFill = c("#a1a1a1")
                    fontSize = 8.px
                }
            }

            style{
                padding = box(
                    top = 5.px,
                    right = 5.px,
                    left = 5.px,
                    bottom = 5.px
                )
            }
        }


        maxHeight = 65.0
        minHeight = 65.0

        style{
            borderColor += box(
                top = c("#ffffff"),
                right = c("#ffffff"),
                left = c("#F4F5F9"),
                bottom = c("#ffffff")
            )

            padding = box(3.px)
        }
    }

}