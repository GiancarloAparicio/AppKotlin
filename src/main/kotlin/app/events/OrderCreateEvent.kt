package app.events

import app.events.abstract.Observable

class OrderCreateEvent : Observable() {

    companion object{

        private var instance : OrderCreateEvent? = null

        fun getInstance() : OrderCreateEvent {

            if( instance == null ){
                instance = OrderCreateEvent()
            }

            return instance as OrderCreateEvent
        }
    }

}