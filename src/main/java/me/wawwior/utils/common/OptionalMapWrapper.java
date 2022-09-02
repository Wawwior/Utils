package me.wawwior.utils.common;

import java.util.Map;
import java.util.Optional;

public class OptionalMapWrapper<K, V> {
	
	private final Map<K, V> map;
	
	private OptionalMapWrapper(Map<K, V> map) {
		this.map = map;
	}
	
	public static <K, V> OptionalMapWrapper<K, V> of(Map<K, V> map) {
		return new OptionalMapWrapper<>(map);
	}
	
	public Optional<V> get(K key) {
		return Optional.ofNullable(map.get(key));
	}
}
