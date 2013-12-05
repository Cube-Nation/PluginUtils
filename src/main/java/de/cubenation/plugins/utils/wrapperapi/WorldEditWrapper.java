package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.RegionSelector;

public class WorldEditWrapper {
    private static WorldEditPlugin worldEditPlugin = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        WorldEditWrapper.log = log;
    }

    public static Selection getSelection(Player player) {
        if (worldEditPlugin == null) {
            loadPlugin();
        }

        if (worldEditPlugin != null) {
            return new Selection(worldEditPlugin.getSelection(player));
        }
        return null;
    }

    public static WorldEditPlugin loadPlugin() {
        if (worldEditPlugin == null) {
            worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
            if (worldEditPlugin == null) {
                log.info("WorldEdit not found");
                return null;
            }
        }
        return worldEditPlugin;
    }

    public static class Selection implements com.sk89q.worldedit.bukkit.selections.Selection {
        private com.sk89q.worldedit.bukkit.selections.Selection selection;

        public Selection(com.sk89q.worldedit.bukkit.selections.Selection selection) {
            this.selection = selection;
        }

        @Override
        public boolean contains(Location arg0) {
            return selection.contains(arg0);
        }

        @Override
        public int getArea() {
            return selection.getArea();
        }

        @Override
        public int getHeight() {
            return selection.getHeight();
        }

        @Override
        public int getLength() {
            return selection.getLength();
        }

        @Override
        public Location getMaximumPoint() {
            return selection.getMaximumPoint();
        }

        @Override
        public Location getMinimumPoint() {
            return selection.getMinimumPoint();
        }

        @Override
        public Vector getNativeMaximumPoint() {
            return selection.getNativeMaximumPoint();
        }

        @Override
        public Vector getNativeMinimumPoint() {
            return selection.getNativeMinimumPoint();
        }

        @Override
        public RegionSelector getRegionSelector() {
            return selection.getRegionSelector();
        }

        @Override
        public int getWidth() {
            return selection.getWidth();
        }

        @Override
        public World getWorld() {
            return selection.getWorld();
        }
    }
}
