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

	public boolean shouldContinue() {
		return this == PASS;
	}

}
