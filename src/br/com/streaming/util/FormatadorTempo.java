package br.com.streaming.util;

public class FormatadorTempo {

    public static String formatarSegundos(int segundos) {
        if (segundos < 0) {
            segundos = 0;
        }
        int minutos = segundos / 60;
        int resto = segundos % 60;
        return String.format("%02d:%02d", minutos, resto);
    }
}
