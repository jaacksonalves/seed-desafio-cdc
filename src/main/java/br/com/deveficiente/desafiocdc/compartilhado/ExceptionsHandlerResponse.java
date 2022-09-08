package br.com.deveficiente.desafiocdc.compartilhado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ExceptionsHandlerResponse(Map<String, List<String>> erros, LocalDateTime ocorridoEm) {
}
