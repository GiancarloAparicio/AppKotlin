package view.home.dashboard._delivery.components

import app.models.Product
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import javafx.scene.text.FontWeight
import tornadofx.*

class ProductComponent( data : Product) : Fragment() {

    var imageUrl : String = data.url
    var productName : String = data.name
    var price: Double = data.price
    var description : String = "Description"

    override val root = initializeComponent()


    private fun action(){
        println("Product add. :)")
    }


    /**
     * Functions helpers
     */

    private fun initializeComponent(): HBox {
        return hbox {

            imageview( imageUrl, lazyload = false  ) {
                fitHeight = 40.0
                fitWidth = 40.0

            }

            vbox {
                hbox {
                    label ( productName ) {
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

                hbox{
                    label ( description ) {
                        prefWidth = 150.0
                        style {
                            textFill = c("#a1a1a1")
                            fontSize = 8.px
                        }
                    }
                    button("Add") {
                        style {
                            backgroundColor += c("#F6A000")
                            textFill = c("white")
                            fontWeight = FontWeight.EXTRA_BOLD
                            fontSize = 10.px
                            padding =  box( 0.5.px )
                            cursor = Cursor.HAND

                        }

                        action {
                            action()
                        }
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


            maxHeight = 55.0
            minHeight = 55.0

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
}