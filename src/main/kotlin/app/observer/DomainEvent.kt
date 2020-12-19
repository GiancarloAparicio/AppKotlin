package app.observer

import app.observer.interfaces.IObservable
import app.observer.interfaces.IObserver

class DomainEvent : IObservable{

    var listListener : MutableList<IObserver> = mutableListOf()

    companion object{

        private var instance : DomainEvent? = null

        fun getInstance() : DomainEvent{

            if( instance == null ){
                instance = DomainEvent()
            }

            return instance as DomainEvent
        }
    }

    override fun addListener( listener : IObserver ) {

        listListener.add(listener)
    }

    override fun throwEvent( type : String, data : Any ) {

        var iterators = listListener.iterator()

        while( iterators.hasNext() ){

            iterators.next().event( type, data )
        }

    }


}