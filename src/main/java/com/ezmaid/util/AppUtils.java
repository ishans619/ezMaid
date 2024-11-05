package com.ezmaid.util;

import java.util.function.Consumer;

import org.springframework.validation.ObjectError;

public class AppUtils {

	public static Consumer<? super ObjectError> fetchErrors(StringBuilder sbError) {
		return error -> {
				sbError.append(error.getDefaultMessage() + ", ");
		};
	}
}
