package me.wawwior.utils.event

interface Event<T> {

    fun register(listener: T)

    fun invoker(): T

}