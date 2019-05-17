package com.adundar.userservice.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result<T> {

	private final boolean success;
	private final T payload;
	private final HttpStatus statusCode;
	private final String errorMessage;

	@JsonCreator
	public Result(@JsonProperty("success") boolean success, @JsonProperty("payload") T payload,
			@JsonProperty("statusCode") HttpStatus statusCode, @JsonProperty("errorMessage") String errorMessage) {
		this.success = success;
		this.payload = payload;
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public static final <T> Result<T> success(HttpStatus statusCode, T payload) {
		return new Result<T>(true, payload, statusCode, null);
	}

	public static final <T> Result<T> success(HttpStatus statusCode) {
		return new Result<T>(true, null, statusCode, null);
	}

	public static final <T> Result<T> fail(HttpStatus statusCode, String errorMessage) {
		return new Result<T>(false, null, statusCode, errorMessage);
	}

	public static final <T> Result<T> fail(HttpStatus statusCode, T payload) {
		return new Result<T>(false, payload, statusCode, null);
	}

	public static final <T> Result<T> fail(HttpStatus statusCode) {
		return new Result<T>(false, null, statusCode, null);
	}

	public final boolean isSuccess() {
		return success;
	}

	public final T getPayload() {
		return payload;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Result");
		sb.append("[");
		sb.append("success=");
		sb.append(success);
		sb.append(", ");
		sb.append("payload=");
		sb.append(payload);
		sb.append(", ");
		sb.append("statusCode=");
		sb.append(statusCode);
		sb.append(", ");
		sb.append("errorMessage=");
		sb.append(errorMessage);
		sb.append("]");
		return sb.toString();
	}
}
