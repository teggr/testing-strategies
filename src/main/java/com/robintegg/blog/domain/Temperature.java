package com.robintegg.blog.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {

	private Measurement measurement;
	private int value;

	@JsonCreator
	public Temperature(@JsonProperty("measurement") Measurement measurement, @JsonProperty("value") int value) {
		super();
		this.measurement = measurement;
		this.value = value;
	}

	public int as(Measurement target) {
		return target.convert(this.measurement, value);
	}

	@Override
	public String toString() {
		return "Temperature [measurement=" + measurement + ", value=" + value + "]";
	}

}
