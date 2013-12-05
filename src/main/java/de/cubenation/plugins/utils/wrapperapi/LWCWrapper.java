package de.cubenation.plugins.utils.wrapperapi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;

public class LWCWrapper {
    private static LWC lwcPlugin = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        LWCWrapper.log = log;
    }

    public static List<Protection> loadProtections(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        if (lwcPlugin == null) {
            loadPlugin();
        }

        if (lwcPlugin != null) {
            List<com.griefcraft.model.Protection> loadProtections = lwcPlugin.getPhysicalDatabase().loadProtections(arg0, arg1, arg2, arg3, arg4, arg5, arg6);

            List<Protection> protections = new ArrayList<Protection>();

            for (com.griefcraft.model.Protection loadProtection : loadProtections) {
                protections.add(new Protection(loadProtection));
            }

            return protections;
        }
        return null;
    }

    public static LWC loadPlugin() {
        if (lwcPlugin == null) {
            LWCPlugin lwc = (LWCPlugin) Bukkit.getServer().getPluginManager().getPlugin("LWC");
            if (lwc == null) {
                log.info("LWC not found");
                return null;
            }
            lwcPlugin = lwc.getLWC();
        }
        return lwcPlugin;
    }

    public static class Protection extends com.griefcraft.model.Protection {
        private com.griefcraft.model.Protection protection;

        public Protection(com.griefcraft.model.Protection protection) {
            this.protection = protection;
        }

        @Override
        public String getOwner() {
            return protection.getOwner();
        }

        @Override
        public void remove() {
            protection.remove();
        }
    }
}
