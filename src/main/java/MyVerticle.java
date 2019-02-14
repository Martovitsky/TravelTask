import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class MyVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        super.start();

        vertx.eventBus().consumer("getFromDB", this::getFromDB);
        vertx.eventBus().consumer("setToDB", this::setToDB);
        vertx.eventBus().publish("getFromDB","getAll");
        vertx.eventBus().publish("setToDB","getAll");
    }

    private void setToDB(Message<Travel> tMessage) {
        try(Session session = HibernateUtil.getSession()){

            System.out.println("Complete");
            session.beginTransaction();
            session.save(tMessage);
            session.getTransaction().commit();

        }
    }


    private void getFromDB(Message<String> tMessage) {
        List<Travel> list = null;
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Travel");


            list = (List<Travel>)  ((org.hibernate.query.Query) query).list();
            session.getTransaction().commit();

        }catch (Throwable cause){
            cause.printStackTrace();
        }
        if (list!= null &&!list.isEmpty()){
            for(Travel travel:list){
                System.out.println(travel);
            }
        }

    }



}
