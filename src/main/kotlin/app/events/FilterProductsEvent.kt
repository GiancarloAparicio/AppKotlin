package app.events

import app.events.abstract.Observable

class FilterProductsEvent : Observable() {

    companion object{

        private var instance : FilterProductsEvent? = null

        fun getInstance() : FilterProductsEvent {

            if( instance == null ){
                instance = FilterProductsEvent()
            }

            return instance as FilterProductsEvent
        }
    }

}