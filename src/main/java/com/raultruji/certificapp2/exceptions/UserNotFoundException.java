package com.raultruji.certificapp2.exceptions;

public class UserNotFoundException extends RuntimeException{

	UserNotFoundException(Long id){
		super("No se pudo encontrar el empleado "+id);
	}
}
