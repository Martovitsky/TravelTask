

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;


public class JDBCExample extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        super.start();
        final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:postgresql://localhost:5432/postgres")
                .put("driver_class", "org.postgresql.Driver")
                .put("max_pool_size", 3)
                .put("user", "postgres")
                .put("password", "123"));

        client.getConnection(conn -> {
            if (conn.failed()) {
                System.err.println(conn.cause().getMessage());
                return;
            }

            final SQLConnection connection = conn.result();

            //connection.execute("create table test(id int primary key, name varchar(255))", res -> {
              //  if (res.failed()) {
                //    throw new RuntimeException(res.cause());
                //}
                // insert some test data
                connection.execute("insert into test values(1, 'Hello')", insert -> {
                    // query some data
                    connection.query("select * from test", rs -> {
                        for (JsonArray line : rs.result().getResults()) {
                            System.out.println(line.encode());
                        }

                        // and close the connection
                        connection.close(done -> {
                            if (done.failed()) {
                                throw new RuntimeException(done.cause());
                            }
                        });
                    });
                });
            });
       // });
    }
}
