package me.wawwior.utils.event

enum class ActionResult {

    FAIL,
    PASS,
    SUCCESS;

    fun isFail() = this == FAIL

    fun isPass() = this == PASS

    fun isSuccess() = this == SUCCESS

    fun shouldContinue() = isPass()

}