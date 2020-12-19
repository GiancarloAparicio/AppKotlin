package app.observer.interfaces

interface IObserver {

    fun event(type: String, data: Any)
}