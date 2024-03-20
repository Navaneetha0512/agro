package com.jsp.agro.util;

import java.util.List;

import com.jsp.agro.entity.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;
}
