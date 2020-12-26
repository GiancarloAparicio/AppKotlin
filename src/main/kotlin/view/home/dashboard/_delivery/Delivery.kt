package view.home.dashboard._delivery

import app.DAO.ProductDAO
import app.DTO.ProductInOrderTableDTO
import app.models.Product
import javafx.scene.control.ComboBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.selectedItem
import view.home.HomeView
import view.home.dashboard._delivery.components.MasonryProductsList


class Delivery : View() {

    //Views
    private val homeView: HomeView by inject()
    private val confirmDelivery: ConfirmDelivery by inject()

    //Items to GUI
    private val masonryListProducts : VBox by fxid()
    private val comboBoxOrderBy : ComboBox<String> by fxid()
    private val comboBoxFilterBy : ComboBox<String> by fxid()

    //Shared
    var productListForOrder = mutableListOf<ProductInOrderTableDTO>()

    //Aux
    var index : Int = 0

    //Root
    override val root : BorderPane by fxml()

    init {
        initializeComboBoxOrderBy()
        initializeComboBoxFilterBy()
        initializeMasonryLayoutToProducts()
    }

    /**
     * Functions GUI
     */

    fun generateOrder(){
        var existsSelectedItems : Boolean = 0 < productListForOrder.size

        if( existsSelectedItems ){
            changeSceneToConfirmOrder()
        }else{
            println("Not selected items")
        }

    }

    fun changeComboBoxFilterBy(){
        println( comboBoxFilterBy.selectedItem )
    }

    fun changeComboBoxOrderBy(){
        println( comboBoxOrderBy.selectedItem )
    }

    fun addProductToList( product : Product){
        var productDTO = ProductInOrderTableDTO( index ,product.name, 1, product.price)
        productListForOrder.add( productDTO )
        index++
    }

    fun removeProductToList( product : Product ){
        var index = getItemIndexToDelete( product.name )
        productListForOrder.removeAt( index )
    }

    /**
     * Functions helpers
     */

    private fun changeSceneToConfirmOrder(){
        homeView.dashBoard.center = confirmDelivery.root

        confirmDelivery.initializeTableOrderWithoutPay()
    }

    private fun initializeMasonryLayoutToProducts(){
        masonryListProducts.add( MasonryProductsList().root )
    }

    private fun initializeComboBoxOrderBy(){
        val items : MutableList<String> = mutableListOf("Name", "Price", "Description")

        for ( item in items ){
            comboBoxOrderBy.items.add( item )
        }
    }

    private fun initializeComboBoxFilterBy(){
        val categories : MutableList<String> = ProductDAO.getCategories()

        for ( category in categories ){
            comboBoxFilterBy.items.add( category )
        }
    }

    private fun getItemIndexToDelete( name : String) : Int {
        var item : Int? = null
        for ( (index, element) in productListForOrder.withIndex()){
            if( element.product == name ){
                item = index
                break
            }
        }

        return item as Int
    }

}