package me.wawwior.utils.event.implementation

import me.wawwior.utils.event.Event

class ArrayBackedEvent<T>(private val arrayFactory: (Int) -> Array<T>, private val invokerFactory: (Array<T>) -> T) : Event<T> {

    private var listeners: Array<T> = arrayFactory(0)

    private var invoker: T = invokerFactory(listeners)

    fun buildInvoker() {
        invoker = invokerFactory(listeners)
    }

    override fun register(listener: T) {
        val newListeners = arrayFactory(listeners.size + 1)
        System.arraycopy(listeners, 0, newListeners, 0, listeners.size)
        newListeners[listeners.size] = listener
        listeners = newListeners
    }

    override fun invoker() = invoker
}