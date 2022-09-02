package me.wawwior.utils.reflection;

import mx.kenzie.mimic.Mimic;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

public class ConditionalProxyWrapper {
	
	private ConditionalProxyWrapper() {}
	
	@SuppressWarnings("unchecked")
	public static <T> T wrap(T t, Supplier<Boolean> condition) {
		return (T) Mimic.create(t.getClass())
				.forward(t)
				.override(
						(o, methodErasure, objects) -> {
							if (condition.get()) {
								return methodErasure.reflect().invoke(t, objects);
							}
							return null;
						},
						Arrays
								.stream(
										t.getClass().getMethods()
								)
								.filter(m -> m.getAnnotation(Unconditional.class) == null)
								.toArray(Method[]::new)
				).build();
	}
	
}
