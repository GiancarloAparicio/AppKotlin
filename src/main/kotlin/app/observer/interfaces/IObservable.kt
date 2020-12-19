package app.observer.interfaces

interface IObservable {

     fun addListener(listener : IObserver)

     fun throwEvent(type: String, data: Any)

}