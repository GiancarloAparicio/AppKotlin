package app.events

import app.events.abstract.Observable

class SortProductsEvent : Observable() {

    companion object{

        private var instance : SortProductsEvent? = null

        fun getInstance() : SortProductsEvent {

            if( instance == null ){
                instance = SortProductsEvent()
            }

            return instance as SortProductsEvent
        }
    }

}