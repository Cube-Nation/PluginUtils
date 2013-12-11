package de.cubenation.plugins.utils.wrapperapi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class LWCWrapper {
    private static com.griefcraft.lwc.LWC lwcPlugin = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        LWCWrapper.log = log;
    }

    public static void loadPlugin() {
        if (lwcPlugin == null) {
            com.griefcraft.lwc.LWCPlugin lwc = (com.griefcraft.lwc.LWCPlugin) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_LWC);
            if (lwc == null) {
                log.info(WrapperManager.PLUGIN_NAME_LWC + " not found");
                lwcPlugin = null;
                return;
            }
            lwcPlugin = lwc.getLWC();
        }
    }

    public static List<Protection> loadProtections(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        if (lwcPlugin == null) {
            loadPlugin();
        }

        if (lwcPlugin != null) {
            List<com.griefcraft.model.Protection> loadProtections = lwcPlugin.getPhysicalDatabase().loadProtections(arg0, arg1, arg2, arg3, arg4, arg5, arg6);

            List<Protection> protections = null;

            if (loadProtections != null) {
                protections = new ArrayList<Protection>();
                for (com.griefcraft.model.Protection loadProtection : loadProtections) {
                    protections.add(new Protection(loadProtection));
                }
            }

            return protections;
        }
        return null;
    }

    public static class Protection {
        private com.griefcraft.model.Protection protection;

        public Protection(com.griefcraft.model.Protection protection) {
            this.protection = protection;
        }

        public String getOwner() {
            return protection.getOwner();
        }

        public void remove() {
            protection.remove();
        }
    }
}
