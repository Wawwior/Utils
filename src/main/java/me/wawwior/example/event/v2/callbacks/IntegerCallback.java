package me.wawwior.example.event.v2.callbacks;

import me.wawwior.utils.common.Modifier;
import me.wawwior.utils.event.v2.CallbackInfo;
import me.wawwior.utils.event.v2.CallbackProvider;

public class IntegerCallback extends CallbackProvider<IntegerCallback.IntegerCallbackInfo> {
	
	public static final IntegerCallback INSTANCE = new IntegerCallback();
	
	public static class IntegerCallbackInfo extends CallbackInfo {
		
		protected int value;
		
		public IntegerCallbackInfo(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		public void modifyValue(Modifier<Integer> modifier) {
			value = modifier.modify(value);
		}
		
	}

}
