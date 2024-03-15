package com.jsp.agro.util;

import java.util.List;

import com.jsp.agro.entity.User;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;
}
