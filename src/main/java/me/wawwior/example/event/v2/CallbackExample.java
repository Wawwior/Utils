package me.wawwior.example.event.v2;

import me.wawwior.example.event.v2.callbacks.IntegerCallback;
import me.wawwior.utils.common.Timer;
import me.wawwior.utils.event.v2.ActionResult;

public class CallbackExample {

	public static ActionResult onIntEvent(IntegerCallback.IntegerCallbackInfo ci) {
		System.out.println(ci.getValue());
		return ActionResult.CONTINUE;
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		Timer timeAll = new Timer();
		IntegerCallback.INSTANCE.register(CallbackExample::onIntEvent);
		timer.log(System.out, s -> "Listener registration: " + s + "\n").reset();
		IntegerCallback.INSTANCE.post(new IntegerCallback.IntegerCallbackInfo(10));
		timer.log(System.out, s -> "Event posting: " + s + "\n");
		timeAll.log(System.out, s -> "Total time: " + s + "\n");
	}
	
}
