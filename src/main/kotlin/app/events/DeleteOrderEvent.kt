package app.events

import app.events.abstract.Observable

class DeleteOrderEvent: Observable() {

    companion object{

        private var instance : DeleteOrderEvent? = null

        fun getInstance() : DeleteOrderEvent {

            if( instance == null ){
                instance = DeleteOrderEvent()
            }

            return instance as DeleteOrderEvent
        }
    }

}