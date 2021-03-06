package view.home.dashboard._delivery

import app.DAO.ProductDAO
import app.DTO.ProductInOrderTableDTO
import app.events.FilterProductsEvent
import app.events.SortProductsEvent
import app.events.types.EventsTypes
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

    //Components
    val masonryProductsLayout = MasonryProductsList()

    //Shared
    var productListForOrder = mutableListOf<ProductInOrderTableDTO>()

    //Events
    var filterProductsEvent = FilterProductsEvent.getInstance()
    var sortProductsEvent = SortProductsEvent.getInstance()

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
        val existsSelectedItems : Boolean = 0 < productListForOrder.size

        if( existsSelectedItems ){
            changeSceneToConfirmOrder()
        }else{
            println("Not selected items")
        }

    }

    fun changeComboBoxFilterBy(){
        val category = comboBoxFilterBy.selectedItem.toString()
        filterProductsEvent.throwEvent( EventsTypes.FILTER_PRODUCTS, category )
    }

    fun changeComboBoxOrderBy(){
        val sort = comboBoxOrderBy.selectedItem.toString()

        sortProductsEvent.throwEvent( EventsTypes.SORT_PRODUCTS, sort )
        masonryProductsLayout.sortComponentsBy( sort )
    }

    fun addProductToList( product : Product){
        val productDTO = ProductInOrderTableDTO( index ,product.name, 1, product.price)
        productListForOrder.add( productDTO )
        index++
    }

    fun removeProductToList( product : Product ){
        val index = getItemIndexToDelete( product.name )
        productListForOrder.removeAt( index )
    }

    /**
     * Functions helpers
     */

    private fun changeSceneToConfirmOrder(){
        homeView.dashBoard.center = confirmDelivery.root
        confirmDelivery.restartTableOrderWithoutPay()
    }

    private fun initializeMasonryLayoutToProducts(){
        masonryListProducts.add( masonryProductsLayout.root )
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