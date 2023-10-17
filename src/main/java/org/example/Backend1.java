package org.example;

import static spark.Spark.*;

public class Backend1 {
    public static void main(String[] args) {

        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("keystores/ecikeystore.p12", "123456", null, null);
        port(getPort());
        get("/hello", (req, res) -> "Hello World From Backend1");
        get("/queryB2", (req, res) -> {
            String url = "https://localhost:5001/hello";
            String response = URLReader.readURL(url);
            return response;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
