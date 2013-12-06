package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class LogBlockWrapper {
    private static de.diddiz.LogBlock.LogBlock logBlock;
    private static Logger log;

    public static void setLogger(Logger log) {
        LogBlockWrapper.log = log;
    }

    public static void loadPlugin() {
        if (logBlock == null) {
            logBlock = (de.diddiz.LogBlock.LogBlock) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.Plugins.LOG_BLOCK.getName());
            if (logBlock == null) {
                log.info(WrapperManager.Plugins.LOG_BLOCK.getName() + " not found");
            }
        }
    }
}
