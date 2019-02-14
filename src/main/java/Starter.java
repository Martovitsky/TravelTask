import io.vertx.core.Vertx;


public class Starter {
    public static void main (String [] args) {
       Vertx.vertx().deployVerticle(new MyVerticle());

    }
}
