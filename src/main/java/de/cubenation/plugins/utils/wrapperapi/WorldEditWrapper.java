package de.cubenation.plugins.utils.wrapperapi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldEditWrapper {
    private static com.sk89q.worldedit.bukkit.WorldEditPlugin worldEditPlugin = null;
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

    public static LocalSession getSession(Player player) {
        if (worldEditPlugin == null) {
            loadPlugin();
        }

        if (worldEditPlugin != null) {
            return new LocalSession(worldEditPlugin.getSession(player));
        }
        return null;
    }

    public static com.sk89q.worldedit.bukkit.WorldEditPlugin loadPlugin() {
        if (worldEditPlugin == null) {
            worldEditPlugin = (com.sk89q.worldedit.bukkit.WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
            if (worldEditPlugin == null) {
                log.info("WorldEdit not found");
                return null;
            }
        }
        return worldEditPlugin;
    }

    public static class LocalSession {
        protected com.sk89q.worldedit.LocalSession localSession;

        public LocalSession(com.sk89q.worldedit.LocalSession session) {
            localSession = session;
        }

        public Region getSelection(LocalWorld localWorld) throws IncompleteRegionException {
            try {
                return new Region(localSession.getSelection(localWorld.localWorld));
            } catch (com.sk89q.worldedit.IncompleteRegionException e) {
                throw new IncompleteRegionException(e);
            }
        }
    }

    public static class Region {
        private com.sk89q.worldedit.regions.Region region;

        public Region(com.sk89q.worldedit.regions.Region region) {
            this.region = region;
        }

        public Vector getMinimumPoint() {
            return new Vector(region.getMinimumPoint());
        }

        public Vector getMaximumPoint() {
            return new Vector(region.getMaximumPoint());
        }
    }

    public static class EditSession {
        private com.sk89q.worldedit.EditSession editSession;

        public EditSession(LocalWorld world, int maxBlocks, BlockBag blockBag) {
            editSession = new com.sk89q.worldedit.EditSession(world.localWorld, maxBlocks, blockBag.blockBag);
        }

        public EditSession(LocalWorld world, int maxBlocks) {
            editSession = new com.sk89q.worldedit.EditSession(world.localWorld, maxBlocks);
        }
    }

    public static class BukkitWorld extends LocalWorld {
        public BukkitWorld(World world) {
            localWorld = new com.sk89q.worldedit.bukkit.BukkitWorld(world);
        }
    }

    public static class LocalWorld {
        protected com.sk89q.worldedit.LocalWorld localWorld;
    }

    public static class BlockBag {
        private com.sk89q.worldedit.bags.BlockBag blockBag;
    }

    public static class Vector {
        private com.sk89q.worldedit.Vector vector;

        public Vector(int blockX, int blockY, int blockZ) {
            vector = new com.sk89q.worldedit.Vector(blockX, blockY, blockZ);
        }

        public Vector(com.sk89q.worldedit.Vector vector) {
            this.vector = vector;
        }

        public int getBlockX() {
            return vector.getBlockX();
        }

        public int getBlockY() {
            return vector.getBlockY();
        }

        public int getBlockZ() {
            return vector.getBlockZ();
        }

        public Vector subtract(Vector min) {
            return new Vector(vector.subtract(min.vector));
        }

        public Vector add(Vector vector2) {
            return new Vector(vector.add(vector2.vector));
        }
    }

    public static class SchematicFormat {
        public static class MCEDIT {
            public static void save(CuboidClipboard clipboard, File schematicFile) throws DataException, IOException {
                try {
                    com.sk89q.worldedit.schematic.SchematicFormat.MCEDIT.save(clipboard.cuboidClipboard, schematicFile);
                } catch (com.sk89q.worldedit.data.DataException e) {
                    throw new DataException(e);
                }
            }

            public static CuboidClipboard load(File schematicFile) throws IOException, DataException {
                try {
                    return new CuboidClipboard(com.sk89q.worldedit.schematic.SchematicFormat.MCEDIT.load(schematicFile));
                } catch (com.sk89q.worldedit.data.DataException e) {
                    throw new DataException(e);
                }
            }
        }
    }

    public static class CuboidClipboard {
        private com.sk89q.worldedit.CuboidClipboard cuboidClipboard;

        public CuboidClipboard(com.sk89q.worldedit.CuboidClipboard cuboidClipboard) {
            this.cuboidClipboard = cuboidClipboard;
        }

        public CuboidClipboard(Vector size, Vector origin) {
            this.cuboidClipboard = new com.sk89q.worldedit.CuboidClipboard(size.vector, origin.vector);
        }

        public void paste(EditSession editSession, Vector newOrigin, boolean noAir) throws MaxChangedBlocksException {
            try {
                cuboidClipboard.paste(editSession.editSession, newOrigin.vector, noAir);
            } catch (com.sk89q.worldedit.MaxChangedBlocksException e) {
                throw new MaxChangedBlocksException(e);
            }
        }

        public void copy(EditSession eSession) {
            cuboidClipboard.copy(eSession.editSession);
        }
    }

    @SuppressWarnings("serial")
    public static class MaxChangedBlocksException extends Exception {
        public MaxChangedBlocksException(com.sk89q.worldedit.MaxChangedBlocksException e) {
            super(e);
        }
    }

    @SuppressWarnings("serial")
    public static class DataException extends Exception {
        public DataException(com.sk89q.worldedit.data.DataException e) {
            super(e);
        }
    }

    @SuppressWarnings("serial")
    public static class IncompleteRegionException extends Exception {
        public IncompleteRegionException(com.sk89q.worldedit.IncompleteRegionException e) {
            super(e);
        }
    }

    public static class Selection {
        private com.sk89q.worldedit.bukkit.selections.Selection selection;

        public Selection(com.sk89q.worldedit.bukkit.selections.Selection selection) {
            this.selection = selection;
        }

        public Location getMaximumPoint() {
            return selection.getMaximumPoint();
        }

        public Location getMinimumPoint() {
            return selection.getMinimumPoint();
        }

        public World getWorld() {
            return selection.getWorld();
        }
    }
}
