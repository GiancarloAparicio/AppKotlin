package view.home.dashboard._delivery.components

import app.DAO.ProductDAO
import app.models.Product
import com.jfoenix.controls.JFXMasonryPane
import tornadofx.Fragment

class MasonryProductsList : Fragment() {

    //Properties
    private var productsLists : MutableList<Product> = ProductDAO.getAll()

    //Root
    override val root = JFXMasonryPane()

    init{
        addProductsComponentsList()
    }

    private fun addProductsComponentsList(){
        for( product in productsLists ){
            root.add( ProductComponent( product ) )
        }
    }

    fun orderComponentsBy( category : String){
        var orderedList = sortListToProductsBy( category )
        removeChildren()

        for( product in orderedList ){
            root.add( ProductComponent( product ) )
        }
    }

    fun filterComponentsBy( category : String){

        var filteredList = filterListToProductsBy( category )
        removeChildren()

        for( product in filteredList ){
            root.add( ProductComponent( product ) )
        }
    }

    /**
     * Functions helpers
     */

    private fun removeChildren(){
        root.children.clear()
    }

    private fun sortListToProductsBy( category : String ): MutableList<Product> {
        var auxList : List<Product>

        when (category) {
            "Name"-> auxList = productsLists.sortedWith( compareBy( { it.name }  ) )
            "Price"-> auxList = productsLists.sortedWith( compareBy( { it.price }  ) )
            "Description"-> auxList = productsLists.sortedWith( compareBy( { it.id }  ) )
            else -> {
                auxList = productsLists
                println("Error: Category not found")
            }
        }

        return auxList as MutableList<Product>
    }

    private fun filterListToProductsBy( category : String ): MutableList<Product> {
        return ProductDAO.getFilteredProducts( category )
    }

}