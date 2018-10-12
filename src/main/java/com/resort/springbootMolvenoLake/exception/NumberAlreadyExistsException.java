package com.resort.springbootMolvenoLake.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Room number already exists")
public class NumberAlreadyExistsException extends RuntimeException{
}