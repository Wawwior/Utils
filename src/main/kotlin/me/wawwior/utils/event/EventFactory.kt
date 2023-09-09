package me.wawwior.utils.event

import com.google.common.collect.MapMaker
import me.wawwior.utils.event.implementation.ArrayBackedEvent
import java.util.*

object EventFactory {
    private val ARRAY_BACKED_EVENTS : MutableSet<ArrayBackedEvent<*>> = Collections.newSetFromMap(WeakHashMap())

    @JvmStatic
    fun <T> createArrayBackedEvent(arrayFactory: (Int) -> Array<T>, invokerFactory: (Array<T>) -> T) : Event<T> {
        val event = ArrayBackedEvent(arrayFactory, invokerFactory)
        ARRAY_BACKED_EVENTS.add(event as ArrayBackedEvent<*>)
        return event
    }

    @JvmStatic
    fun <T> createArrayBackedEvent(arrayFactory: (Int) -> Array<T>, emptyInvoker: T, invokerFactory: (Array<T>) -> T) : Event<T> {
        return createArrayBackedEvent(arrayFactory) { listeners ->
            if (listeners.isEmpty()) {
                emptyInvoker
            } else if (listeners.size == 1) {
                listeners[0]
            } else {
                invokerFactory(listeners)
            }
        }
    }

    @JvmStatic
    fun rebuildAllInvokers() {
        ARRAY_BACKED_EVENTS.forEach { it.buildInvoker() }
    }
}