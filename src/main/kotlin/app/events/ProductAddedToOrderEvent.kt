package app.events

import app.events.abstract.Observable

class ProductAddedToOrderEvent : Observable() {

    companion object{

        private var instance : ProductAddedToOrderEvent? = null

        fun getInstance() : ProductAddedToOrderEvent {

            if( instance == null ){
                instance = ProductAddedToOrderEvent()
            }

            return instance as ProductAddedToOrderEvent
        }
    }

}