package com.jsp.agro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  EquipmentNotFoundException extends RuntimeException {
	private String msg="Equipment you are trying to fetch is Not Found";
}
