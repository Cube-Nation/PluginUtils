package de.cubenation.plugins.utils.testapi;

import org.bukkit.Bukkit;

public class AbstractTest {
    public void setUp() {
        if (Bukkit.getServer() == null) {
            Bukkit.setServer(new TestServer());
        }
    }
}
