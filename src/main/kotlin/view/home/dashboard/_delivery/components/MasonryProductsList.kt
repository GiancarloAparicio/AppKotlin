package view.home.dashboard._delivery.components

import app.DAO.ProductDAO
import com.jfoenix.controls.JFXMasonryPane
import tornadofx.Fragment

class MasonryProductsList : Fragment() {

    override val root = JFXMasonryPane()

    init{
        addProductComponentList()
    }

    private fun addProductComponentList(){
        var productsLists = ProductDAO.getAll()

        for( product in productsLists ){
            root.add(ProductComponent( product ))
        }
    }

}