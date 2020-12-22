package app.events

import app.events.abstract.Observable

class UserLoginEvent : Observable() {

    companion object{

        private var instance : Observable? = null

        fun getInstance() : UserLoginEvent {

            if( instance == null ){
                instance = UserLoginEvent()
            }

            return instance as UserLoginEvent
        }
    }

}