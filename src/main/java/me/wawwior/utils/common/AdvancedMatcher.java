package me.wawwior.utils.common;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvancedMatcher {
	
	private final List<String> args = new ArrayList<>();
	
	private final String regex;
	
	private AdvancedMatcher(String regex) {
		
		Pattern pattern = Pattern.compile("\\$(\\w+)");
		Matcher matcher = pattern.matcher(regex);
		
		while (matcher.find()) {
			args.add(matcher.group(1));
		}
		
		this.regex = regex.replaceAll("\\$(\\w+)", "(?<$1>\\\\w+)");
		
	}
	
	public static AdvancedMatcher of(String regex) {
		return new AdvancedMatcher(regex);
	}
	
	public Optional<OptionalMapWrapper<String, String>> matches(String message) {
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		
		Map<String, String> builder = new HashMap<>();
		
		if (matcher.matches()) {
			for (String arg : args) {
				builder.put(arg, matcher.group(arg));
			}
			return Optional.of(OptionalMapWrapper.of(builder));
		}
		
		return Optional.empty();
		
	}
	
}
