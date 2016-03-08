package com.cs407.tylerphelps.cs407hw2;

/**
 * Created by njaunich on 2/18/16.
 */
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.cs407.tylerphelps.cs407hw2"); //Scheme for GreenDAO ORM
        createDB(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java/");
        //where you want to store the generated classes.
    }

    private static void createDB(Schema schema) {

        //Events
        Entity Calevent = schema.addEntity("CalEvent");
        Calevent.addIdProperty();
        Calevent.addStringProperty("name");
        Calevent.addStringProperty("location");
        Calevent.addStringProperty("startTime");
        Calevent.addStringProperty("endTime");
        Calevent.addStringProperty("date");
        Calevent.addBooleanProperty("display");

    }

}