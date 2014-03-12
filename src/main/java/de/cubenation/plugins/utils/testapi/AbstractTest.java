package de.cubenation.plugins.utils.testapi;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import com.avaje.ebeaninternal.server.lib.sql.TransactionIsolation;

public class AbstractTest {
    public void setUp() {
        if (Bukkit.getServer() == null) {
            Server server = getServer();

            Bukkit.setServer(server);
        }
    }

    public static Server getServer() {
        Server server = mock(Server.class);
        when(server.getName()).thenReturn("Test Server");
        when(server.getVersion()).thenReturn("1.0");
        when(server.getBukkitVersion()).thenReturn("1.0");

        Logger serverLogger = Logger.getLogger("TestServer");
        when(server.getLogger()).thenReturn(serverLogger);

        BukkitScheduler scheduler = mock(BukkitScheduler.class);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                final Runnable task = (Runnable) args[1];

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                        task.run();
                    }
                }.start();
                return null;
            }
        }).when(scheduler).runTask(any(Plugin.class), any(Runnable.class));
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Runnable task = (Runnable) args[1];
                task.run();
                return null;
            }
        }).when(scheduler).runTaskAsynchronously(any(Plugin.class), any(Runnable.class));

        when(server.getScheduler()).thenReturn(scheduler);

        ItemFactory itemFactory = mock(ItemFactory.class);
        doAnswer(new Answer<Boolean>() {
            public Boolean answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                ItemMeta meta1 = (ItemMeta) args[0];
                ItemMeta meta2 = (ItemMeta) args[1];

                if (meta1 == null && meta2 == null) {
                    return true;
                } else if (meta1 == null) {
                    return false;
                } else if (meta2 == null) {
                    return false;
                }
                return meta1.getDisplayName().equals(meta2.getDisplayName());
            }
        }).when(itemFactory).equals(any(ItemMeta.class), any(ItemMeta.class));

        when(server.getItemFactory()).thenReturn(itemFactory);

        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                ServerConfig config = (ServerConfig) args[0];

                DataSourceConfig ds = new DataSourceConfig();
                ds.setDriver("org.sqlite.JDBC");
                ds.setUrl("jdbc:sqlite::memory:");
                ds.setUsername("bukkit");
                ds.setPassword("walrus");
                ds.setIsolationLevel(TransactionIsolation.getLevel("SERIALIZABLE"));

                config.setDatabasePlatform(new SQLitePlatform());
                config.getDatabasePlatform().getDbDdlSyntax().setIdentity("");

                config.setDataSourceConfig(ds);
                return null;
            }
        }).when(server).configureDbConfig(any(ServerConfig.class));

        return server;
    }
}
