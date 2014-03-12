package de.cubenation.plugins.utils.testapi;

import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Query;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

public class AbstractDatabaseTest extends AbstractTest {
    protected EbeanServer dbConnection;

    public void setUp(List<Class<?>> classes) {
        super.setUp();

        ServerConfig db = new ServerConfig();

        Bukkit.getServer().configureDbConfig(db);
        db.setDefaultServer(false);
        db.setRegister(false);
        db.setClasses(classes);
        db.setName("test");

        dbConnection = EbeanServerFactory.create(db);

        if (dbConnection == null) {
            throw new RuntimeException("could not setup database");
        }

        try {
            for (Class<?> clas : classes) {
                dbConnection.find(clas).findRowCount();
            }
        } catch (PersistenceException ex) {
            SpiEbeanServer serv = (SpiEbeanServer) dbConnection;
            DdlGenerator gen = serv.getDdlGenerator();

            gen.runScript(false, gen.generateCreateDdl());
        }

        // delete data from before runnig tests
        for (Class<?> clas : classes) {
            Query<?> query = dbConnection.find(clas);

            List<?> beans = query.findList();

            dbConnection.delete(beans);
        }
    }
}
