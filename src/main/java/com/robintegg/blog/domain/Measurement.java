package com.robintegg.blog.domain;

import java.util.function.Function;

public enum Measurement {

	CELCIUS(f -> 5 * (f - 32) / 9), FAHRENHEIT(c -> 9 * c / 5 + 32);

	private final Function<Integer, Integer> convertFunction;

	Measurement(Function<Integer, Integer> convertFunction) {
		this.convertFunction = convertFunction;
	}

	public int convert(Measurement measurement, int value) {
		if (measurement == this) {
			return value;
		}
		return convertFunction.apply(value);
	}

}
