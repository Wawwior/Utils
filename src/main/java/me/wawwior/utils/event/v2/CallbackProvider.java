package me.wawwior.utils.event.v2;

import me.wawwior.utils.event.Priority;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CallbackProvider<I extends CallbackInfo> {
	
	private final List<CallbackEntry<I>> consumers = new ArrayList<>();
	
	private boolean sorted = false;
	
	
	public void register(CallbackConsumer<I> consumer) {
		register(consumer, Priority.DEFAULT);
	}
	
	public void register(CallbackConsumer<I> consumer, int priority) {
		consumers.add(new CallbackEntry<>(consumer, priority));
		sorted = false;
	}
	
	public void post(I info) {
		if (!sorted) {
			consumers.sort(Comparator.comparingInt(CallbackEntry::priority));
			sorted = true;
		}
		for (CallbackEntry<I> c : consumers) {
			if (c.post(info) != ActionResult.CONTINUE) {
				break;
			}
		}
	}
	
	private record CallbackEntry<I extends CallbackInfo>(CallbackConsumer<I> consumer, int priority) {
		
		public ActionResult post(I info) {
			return consumer.accept(info);
		}
	}
	
}
