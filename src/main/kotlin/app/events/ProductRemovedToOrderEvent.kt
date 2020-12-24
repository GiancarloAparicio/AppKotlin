package app.events

import app.events.abstract.Observable

class ProductRemovedToOrderEvent  : Observable() {

    companion object{

        private var instance : ProductRemovedToOrderEvent? = null

        fun getInstance() : ProductRemovedToOrderEvent {

            if( instance == null ){
                instance = ProductRemovedToOrderEvent()
            }

            return instance as ProductRemovedToOrderEvent
        }
    }

}