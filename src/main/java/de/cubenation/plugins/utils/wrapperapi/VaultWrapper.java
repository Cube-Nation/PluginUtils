package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultWrapper {
    private static net.milkbowl.vault.Vault vault;
    private static Logger log;

    public static void setLogger(Logger log) {
        VaultWrapper.log = log;
    }

    public static void loadPlugin() {
        if (vault == null) {
            vault = (net.milkbowl.vault.Vault) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.Plugins.VAULT.getName());
            if (vault == null) {
                log.warning("Coult not find Vault plugin, but economy is enabled. Please install Vault or disable economy.");
            }
        }
    }

    public static Economy getService() {
        if (vault == null) {
            loadPlugin();
        }

        if (vault != null) {
            RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = Bukkit.getServer().getServicesManager()
                    .getRegistration(net.milkbowl.vault.economy.Economy.class);
            if (rsp != null) {
                net.milkbowl.vault.economy.Economy economy = rsp.getProvider();
                log.info("Connected to " + economy.getName() + " for economy support.");

                return new Economy(economy);
            } else {
                log.warning("Vault could not find any economy plugin to connect to. Please install one or disable economy.");
            }
        }
        return null;
    }

    public static class Economy {
        private net.milkbowl.vault.economy.Economy economy;

        public Economy(net.milkbowl.vault.economy.Economy economy) {
            this.economy = economy;
        }

        public String format(int cityPrice) {
            return economy.format(cityPrice);
        }

        public boolean has(String name, int cityPrice) {
            return economy.has(name, cityPrice);
        }

        public void withdrawPlayer(String name, int cityPrice) {
            economy.withdrawPlayer(name, cityPrice);
        }
    }
}
