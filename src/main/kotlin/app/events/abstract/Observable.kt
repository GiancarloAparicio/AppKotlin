package app.events.abstract

import app.events.interfaces.IObserver

abstract class Observable {

    private var listListeners : MutableList<IObserver> = mutableListOf()


    fun addListener( listener : IObserver ){
        listListeners.add( listener )
    }

    fun throwEvent( data: Any ){
        var iterators = listListeners.iterator()

        while( iterators.hasNext() ){

            iterators.next().event( data )
        }
    }

}