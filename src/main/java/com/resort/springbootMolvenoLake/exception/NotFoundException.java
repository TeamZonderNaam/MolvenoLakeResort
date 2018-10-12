package com.resort.springbootMolvenoLake.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Room not found.")
public class NotFoundException extends RuntimeException{
}
