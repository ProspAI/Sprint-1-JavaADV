package br.com.fiap.jadv.prospai.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerUtil {

    public static ResponseEntity<Object> handleException(Exception ex, HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }

    public static ResponseEntity<Object> handleNotFoundException(Exception ex, String message) {
        return handleException(ex, HttpStatus.NOT_FOUND, message);
    }

    public static ResponseEntity<Object> handleBadRequestException(Exception ex, String message) {
        return handleException(ex, HttpStatus.BAD_REQUEST, message);
    }
}
