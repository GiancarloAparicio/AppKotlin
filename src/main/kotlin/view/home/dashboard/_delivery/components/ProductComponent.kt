package view.home.dashboard._delivery.components

import app.events.OrderCreateEvent
import app.events.interfaces.IObserver
import app.models.Product
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.text.FontWeight
import tornadofx.*
import view.home.dashboard._delivery.Delivery

class ProductComponent( data : Product) : Fragment(), IObserver {

    //Properties
    var imageUrl : String = data.url
    var productName : String = data.name
    var price: Double = data.price
    var description : String = "Description"

    //Aux
    var addedStatus : Boolean = false
    private var product = data

    //Events
    var orderCreateEvent = OrderCreateEvent.getInstance()

    //Views
    private val delivery: Delivery by inject()

    //Components
    private lateinit var buttonProduct : Button

    //Root
    override val root = initializeComponent()

    init {
        orderCreateEvent.addListener(this)
    }


    /**
     * Functions GUI
     */

    private fun action(){
        if( addedStatus ){
            delivery.removeProductToList( product )
        }else{
            delivery.addProductToList( product )
        }
    }

    override fun event(typeEvent: String, data: Any) {
        if( addedStatus ){
            buttonProduct.fire()
        }
    }


    /**
     * Functions helpers
     */

    private fun changeStatusButton() : String {
        addedStatus = !addedStatus
        return if( addedStatus ) "Remove" else "Add"
    }

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
                    buttonProduct = button ("Add"){

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
                            text = changeStatusButton()

                            style{
                                if( addedStatus ){
                                    backgroundColor += c("#EE254F")
                                }else{
                                    backgroundColor += c("#F6A000")
                                }

                                textFill = c("white")
                                fontWeight = FontWeight.EXTRA_BOLD
                                fontSize = 10.px
                                padding =  box( 0.5.px )
                                cursor = Cursor.HAND
                            }
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