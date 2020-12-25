package view.home.dashboard._delivery

import app.DAO.ProductDAO
import app.models.Product
import javafx.scene.control.ComboBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.View
import view.home.HomeView
import view.home.dashboard._delivery.components.MasonryProductsList

class Delivery : View() {

    private val homeView: HomeView by inject()
    private val confirmDelivery: ConfirmDelivery by inject()

    private val masonryListProducts : VBox by fxid()
    private val comboBoxOrderBy : ComboBox<String> by fxid()
    private val comboBoxFilterBy : ComboBox<String> by fxid()

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
        changeSceneToConfirmOrder()
    }

    /**
     * Functions helpers
     */

    private fun changeSceneToConfirmOrder(){
        homeView.dashBoard.center = confirmDelivery.root
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

}