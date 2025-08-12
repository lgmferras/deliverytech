package com.deliverytech.delivery_api.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> baseBody(int status, String error, String message) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", OffsetDateTime.now().toString());
    body.put("status", status);
    body.put("error", error);
    body.put("message", message);
    return body;
}

// Fallback para qualquer exceção não tratada
@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
    Map<String, Object> body = baseBody(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
}

// 404 quando rota não encontrada (requer propriedades no application.properties)
@ExceptionHandler(NoHandlerFoundException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
    String msg = "Rota não encontrada: " + ex.getRequestURL();
    Map<String, Object> body = baseBody(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            msg
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
}

// Parâmetro obrigatório ausente
@ExceptionHandler(MissingServletRequestParameterException.class)
public ResponseEntity<Map<String, Object>> handleMissingParam(MissingServletRequestParameterException ex) {
    String msg = "Parâmetro obrigatório ausente: " + ex.getParameterName();
    Map<String, Object> body = baseBody(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            msg
    );
    return ResponseEntity.badRequest().body(body);
}

// Tipo de argumento inválido (ex: converter String para Long falhou)
@ExceptionHandler(MethodArgumentTypeMismatchException.class)
public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido";
    String msg = String.format("Parâmetro '%s' com valor '%s' não pôde ser convertido para %s",
            ex.getName(), ex.getValue(), requiredType);
    Map<String, Object> body = baseBody(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            msg
    );
    return ResponseEntity.badRequest().body(body);
}

// Corpo JSON inválido ou não legível
@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex) {
    Map<String, Object> body = baseBody(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "Corpo da requisição inválido ou não legível"
    );
    return ResponseEntity.badRequest().body(body);
}

// Erros de validação em @RequestBody com @Valid
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> body = baseBody(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "Erro de validação"
    );

    Map<String, String> fields = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                    fe -> fe.getField(),
                    fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "inválido",
                    (a, b) -> a // em caso de duplicata, mantém o primeiro
            ));
    body.put("fields", fields);
    return ResponseEntity.badRequest().body(body);
}

// Erros de validação em parâmetros de query/path com @Valid (BindException)
@ExceptionHandler(BindException.class)
public ResponseEntity<Map<String, Object>> handleBind(BindException ex) {
    Map<String, Object> body = baseBody(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "Erro de validação"
    );

    Map<String, String> fields = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                    fe -> fe.getField(),
                    fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "inválido",
                    (a, b) -> a
            ));
    body.put("fields", fields);
    return ResponseEntity.badRequest().body(body);
}

}
