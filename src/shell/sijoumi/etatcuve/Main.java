package shell.sijoumi.etatcuve;

import java.io.IOException;

public final class Main {

    private static final String HOST = "10.10.10.102";
    private static final String PORT = "10001";
    private static final String OUTPUT_FILE = "D:\\Stok.txt";

    private Main() {
        // Empêche l'instanciation de la classe
    }

    public static void main(String[] args) {
        try {
            startTelnet();
        } catch (IOException e) {
            System.err.println("Erreur lors du lancement de Telnet : " + e.getMessage());
        }
    }

    private static void startTelnet() throws IOException {
        new ProcessBuilder(
                "cmd",
                "/c",
                "start",
                "telnet",
                HOST,
                PORT,
                "-f",
                OUTPUT_FILE
        ).start();
    }
}