package de.cubenation.plugins.utils.wrapperapi;

import java.util.List;
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
            vault = (net.milkbowl.vault.Vault) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_VAULT);
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

        public EconomyResponse bankBalance(String arg0) {
            return new EconomyResponse(economy.bankBalance(arg0));
        }

        public EconomyResponse bankDeposit(String arg0, double arg1) {
            return new EconomyResponse(economy.bankDeposit(arg0, arg1));
        }

        public EconomyResponse bankHas(String arg0, double arg1) {
            return new EconomyResponse(economy.bankHas(arg0, arg1));
        }

        public EconomyResponse bankWithdraw(String arg0, double arg1) {
            return new EconomyResponse(economy.bankWithdraw(arg0, arg1));
        }

        public EconomyResponse createBank(String arg0, String arg1) {
            return new EconomyResponse(economy.createBank(arg0, arg1));
        }

        public boolean createPlayerAccount(String arg0) {
            return economy.createPlayerAccount(arg0);
        }

        public String currencyNamePlural() {
            return economy.currencyNamePlural();
        }

        public String currencyNameSingular() {
            return economy.currencyNameSingular();
        }

        public EconomyResponse deleteBank(String arg0) {
            return new EconomyResponse(economy.deleteBank(arg0));
        }

        public EconomyResponse depositPlayer(String arg0, double arg1) {
            return new EconomyResponse(economy.depositPlayer(arg0, arg1));
        }

        public String format(double arg0) {
            return economy.format(arg0);
        }

        public int fractionalDigits() {
            return economy.fractionalDigits();
        }

        public double getBalance(String arg0) {
            return economy.getBalance(arg0);
        }

        public List<String> getBanks() {
            return economy.getBanks();
        }

        public String getName() {
            return economy.getName();
        }

        public boolean has(String arg0, double arg1) {
            return economy.has(arg0, arg1);
        }

        public boolean hasAccount(String arg0) {
            return economy.hasAccount(arg0);
        }

        public boolean hasBankSupport() {
            return economy.hasBankSupport();
        }

        public EconomyResponse isBankMember(String arg0, String arg1) {
            return new EconomyResponse(economy.isBankMember(arg0, arg1));
        }

        public EconomyResponse isBankOwner(String arg0, String arg1) {
            return new EconomyResponse(economy.isBankOwner(arg0, arg1));
        }

        public boolean isEnabled() {
            return economy.isEnabled();
        }

        public EconomyResponse withdrawPlayer(String arg0, double arg1) {
            return new EconomyResponse(economy.withdrawPlayer(arg0, arg1));
        }
    }

    public static class EconomyResponse {
        private net.milkbowl.vault.economy.EconomyResponse economyResponse;

        public EconomyResponse(net.milkbowl.vault.economy.EconomyResponse economyResponse) {
            this.economyResponse = economyResponse;
        }

        public boolean transactionSuccess() {
            return economyResponse.transactionSuccess();
        }
    }

    public static class ChatService {
        public ChatService(net.milkbowl.vault.chat.Chat chatService) {
        }

        // TODO
    }
}
