package io.meritu.meritubackend.controller;

import io.meritu.meritubackend.domain.dto.ErrorFieldDTO;
import io.meritu.meritubackend.domain.dto.ErrorResponseDTO;
import io.meritu.meritubackend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //TODO: MUDAR O JEITO QUE LOGA O ERRO DEPOIS

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();

        List<ErrorFieldDTO> erroCampos = e.getFieldErrors().stream()
                .map(fe -> new ErrorFieldDTO(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                erroCampos);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleUsuarioNaoEncontradoException(UserNotFoundException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(),
                e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleLoginException(BadCredentialsException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(),
                "Usuário e/ou senha incorreto(s)",
                null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleException(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno: " + e.getMessage());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleInvalidEmployeeException(TeamNotFoundException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleInvalidEmployeeException(InvalidEmployeeException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleInvalidEmployeeException(EmployeeNotFoundException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
    }

    @ExceptionHandler(GoalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleGoalNotFound(GoalNotFoundException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
                e.getMessage());
    }

    @ExceptionHandler(InvalidOperationGoalException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleInvalidOperationGoalException(InvalidOperationGoalException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.CONFLICT.value(),
                e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleAuthException(AuthException e) {
        return new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(),
                e.getMessage());
    }

}
