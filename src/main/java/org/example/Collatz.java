package org.example;

import static spark.Spark.*;

public class Collatz {
    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("hello", (req,res) -> "Hello Docker!");
        get("collatz", (req,res) -> {
            String numero = req.queryParams("value");
            return formatoSecuencia(numero);
        });
    }

    private static Object formatoSecuencia(String numero) {
        return "operation: collatzsequence" +
                "input: " + numero  +
                "output: " + getConjentura(Integer.parseInt(numero));
    }

    private static int getConjentura(int numero){
        int resultado = 0;
        while (numero > 1){
            if(numero % 2 == 0){
                resultado = numero / 2;
            } else {
                resultado = (3 * numero) + 1;
            }
        }
        return resultado;
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
