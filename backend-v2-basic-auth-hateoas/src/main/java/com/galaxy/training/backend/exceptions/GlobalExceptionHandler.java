package com.galaxy.training.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galaxy.training.backend.dto.out.ErrorResponseDto;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ 
        EscuelaConductorNoExistenteException.class, 
        DepartamentoNoExistenteException.class,
        ProvinciaNoExistenteException.class, 
        DistritoNoExistenteException.class })
    public ResponseEntity<ErrorResponseDto> handleNotFound(RuntimeException ex) {
        ErrorResponseDto body = new ErrorResponseDto(
            HttpStatus.NOT_FOUND.value(), 
            ex.getClass().getSimpleName(),
            ex.getMessage() == null ? "Recurso no encontrado" : ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EscuelaConductorDuplicadoException.class)
    public ResponseEntity<ErrorResponseDto> handleConflict(EscuelaConductorDuplicadoException ex) {
        ErrorResponseDto body = new ErrorResponseDto(
            HttpStatus.CONFLICT.value(), 
            ex.getClass().getSimpleName(),
            ex.getMessage() == null ? "Recurso duplicado" : ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ 
        MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception ex) {
        String message = ex.getMessage();
        ErrorResponseDto body = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            ex.getClass().getSimpleName(),
            message == null ? "Solicitud inválida" : message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAll(Exception ex) {
        ErrorResponseDto body = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getSimpleName(),
                ex.getMessage() == null ? "Error interno del servidor" : ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
