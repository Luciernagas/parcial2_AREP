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

    private static String formatoSecuencia(String numero) {
        String resp =  "{ \"operation\" : \"collatzsequence\", " +
                "\"input \": " + numero  +
                "\"output\" : \"" + getConjentura(Integer.parseInt(numero)) + "\" }";
        System.out.println(resp);
        return resp;
    }

    private static int getConjentura(int numero){
        int resultado = numero;
        while (resultado > 1){
            if(resultado % 2 == 0){
                resultado = resultado/ 2;
            } else {
                resultado = (3 * resultado) + 1;
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
