package me.wawwior.utils.event.v2;

public interface CallbackConsumer<I extends CallbackInfo> {
	
	ActionResult accept(I info);
	
}
