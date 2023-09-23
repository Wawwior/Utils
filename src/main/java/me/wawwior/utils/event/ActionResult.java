package me.wawwior.utils.event;

public enum ActionResult {

    FAIL,
    PASS,
    SUCCESS;

    public boolean isFail() {
        return this == FAIL;
    }

    public boolean isPass() {
        return this == PASS;
    }

    public boolean isSuccess() {
        return this == SUCCESS;
    }

    public ActionResult or(ActionResult other) {
        return this == FAIL ? other : this;
    }

    public ActionResult and(ActionResult other) {
        return this == SUCCESS ? other : this;
    }

}
