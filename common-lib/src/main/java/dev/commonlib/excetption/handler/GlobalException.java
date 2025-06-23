package dev.commonlib.excetption.handler;

import dev.commonlib.excetption.AlreadyExists;
import dev.commonlib.excetption.NotFoundException;
import dev.commonlib.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException  {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ExceptionResponse notFound(NotFoundException notfoundException) {
        return ExceptionResponse.
                builder()
                .status(HttpStatus.NOT_FOUND)
                .exceptionClassName(NotFoundException.class.getName())
                .message(notfoundException.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    public ExceptionResponse badRequest(AlreadyExists e) {
        return ExceptionResponse.
                builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionClassName(AlreadyExists.class.getName())
                .message(e.getMessage())
                .build();
    }
}
