package br.com.streaming.util;

public class Validador {

    public static String validarEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        return email.trim();
    }

    public static String validarTexto(String texto, String nomeCampo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(nomeCampo + " inválido");
        }
        return texto.trim();
    }

    public static void validarObjeto(Object objeto, String nomeCampo) {
        if (objeto == null) {
            throw new IllegalArgumentException(nomeCampo + " inválido");
        }
    }

    public static int validarInteiroPositivo(int valor, String nomeCampo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(nomeCampo + " deve ser positivo");
        }
        return valor;
    }
}
