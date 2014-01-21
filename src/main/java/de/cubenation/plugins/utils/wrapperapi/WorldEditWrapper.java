package de.cubenation.plugins.utils.wrapperapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    public static void loadPlugin() {
        if (worldEditPlugin == null) {
            worldEditPlugin = (com.sk89q.worldedit.bukkit.WorldEditPlugin) Bukkit.getServer().getPluginManager()
                    .getPlugin(WrapperManager.PLUGIN_NAME_WORLD_EDIT);
            if (worldEditPlugin == null) {
                log.info(WrapperManager.PLUGIN_NAME_WORLD_EDIT + " not found");
            }
        }
    }

    public static WorldEdit getWorldEdit() {
        if (worldEditPlugin == null) {
            loadPlugin();
        }

        if (worldEditPlugin != null) {
            return new WorldEdit(worldEditPlugin.getWorldEdit());
        }
        return null;
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

    public static ServerInterface getServerInterface() {
        if (worldEditPlugin == null) {
            loadPlugin();
        }

        if (worldEditPlugin != null) {
            return new ServerInterface(worldEditPlugin.getServerInterface());
        }
        return null;
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

        public RegionSelector getRegionSelector(LocalWorld bWorld) {
            return new RegionSelector(localSession.getRegionSelector(bWorld.localWorld));
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

    public static class BlockVector {
        public com.sk89q.worldedit.BlockVector blockVector;

        public BlockVector(com.sk89q.worldedit.BlockVector blockVector) {
            this.blockVector = blockVector;
        }

        public BlockVector(int x, int y, int z) {
            blockVector = new com.sk89q.worldedit.BlockVector(x, y, z);
        }

        public int getBlockX() {
            return blockVector.getBlockX();
        }

        public int getBlockZ() {
            return blockVector.getBlockZ();
        }

        public double getX() {
            return blockVector.getX();
        }

        public double getY() {
            return blockVector.getY();
        }

        public double getZ() {
            return blockVector.getZ();
        }

        public int getBlockY() {
            return blockVector.getBlockY();
        }
    }

    public static class BlockVector2D {
        com.sk89q.worldedit.BlockVector2D blockVector2D;

        public BlockVector2D(com.sk89q.worldedit.BlockVector2D blockVector2D) {
            this.blockVector2D = blockVector2D;
        }

        public int getBlockX() {
            return blockVector2D.getBlockX();
        }

        public int getBlockZ() {
            return blockVector2D.getBlockZ();
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

        public EditSession(BukkitWorld bWorld, int maxBlocks) {
            editSession = new com.sk89q.worldedit.EditSession(bWorld.localWorld, maxBlocks);
        }

        public void setBlock(BlockVector blockVector, BaseBlock cobble) throws MaxChangedBlocksException {
            try {
                editSession.setBlock(blockVector.blockVector, cobble.baseBlock);
            } catch (com.sk89q.worldedit.MaxChangedBlocksException e) {
                throw new MaxChangedBlocksException(e);
            }
        }

        public void flushQueue() {
            editSession.flushQueue();
        }

        public int getBlockType(Vector pt) {
            return editSession.getBlockType(pt.vector);
        }

        public void setBlock(BlockVector vector, SignBlock welcomeSign) throws MaxChangedBlocksException {
            try {
                editSession.setBlock(vector.blockVector, welcomeSign.signBlock);
            } catch (com.sk89q.worldedit.MaxChangedBlocksException e) {
                throw new MaxChangedBlocksException(e);
            }
        }

        public void replaceBlocks(CuboidRegion restoreRegion, HashSet<BaseBlock> newHashSet, BaseBlock baseBlock) throws MaxChangedBlocksException {
            HashSet<com.sk89q.worldedit.blocks.BaseBlock> map = null;

            if (newHashSet != null) {
                map = new HashSet<com.sk89q.worldedit.blocks.BaseBlock>();
                Iterator<BaseBlock> iterator = newHashSet.iterator();
                while (iterator.hasNext()) {
                    map.add(iterator.next().baseBlock);
                }
            }

            try {
                editSession.replaceBlocks(restoreRegion.cuboidRegion, map, baseBlock.baseBlock);
            } catch (com.sk89q.worldedit.MaxChangedBlocksException e) {
                throw new MaxChangedBlocksException(e);
            }
        }
    }

    public static class SignBlock {
        private com.sk89q.worldedit.blocks.SignBlock signBlock;

        public SignBlock(int signPost, int i, String[] strings) {
            signBlock = new com.sk89q.worldedit.blocks.SignBlock(signPost, i, strings);
        }
    }

    public static class BaseBlock {
        private com.sk89q.worldedit.blocks.BaseBlock baseBlock;

        public BaseBlock(int i, int j) {
            baseBlock = new com.sk89q.worldedit.blocks.BaseBlock(i, j);
        }

        public BaseBlock(int i) {
            baseBlock = new com.sk89q.worldedit.blocks.BaseBlock(i);
        }
    }

    public static class BukkitWorld extends LocalWorld {
        public BukkitWorld(World world) {
            super(new com.sk89q.worldedit.bukkit.BukkitWorld(world));
        }
    }

    public static class LocalWorld {
        protected com.sk89q.worldedit.LocalWorld localWorld;

        public LocalWorld(com.sk89q.worldedit.LocalWorld localWorld) {
            this.localWorld = localWorld;
        }
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

    public static class MaxChangedBlocksException extends Exception {
        private static final long serialVersionUID = -2621044030640945259L;

        public MaxChangedBlocksException(com.sk89q.worldedit.MaxChangedBlocksException e) {
            super(e);
        }
    }

    public static class InvalidSnapshotException extends Exception {
        private static final long serialVersionUID = 7307139106494852893L;

        public InvalidSnapshotException(com.sk89q.worldedit.snapshots.InvalidSnapshotException e) {
            super(e);
        }
    }

    public static class DataException extends Exception {
        private static final long serialVersionUID = 5806521052111023788L;

        public DataException(com.sk89q.worldedit.data.DataException e) {
            super(e);
        }
    }

    public static class IncompleteRegionException extends Exception {
        private static final long serialVersionUID = 458988897980179094L;

        public IncompleteRegionException(com.sk89q.worldedit.IncompleteRegionException e) {
            super(e);
        }
    }

    public static class Selection {
        public com.sk89q.worldedit.bukkit.selections.Selection selection;

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

    public static class CuboidRegion {
        private com.sk89q.worldedit.regions.CuboidRegion cuboidRegion;

        public CuboidRegion(BlockVector minimumPoint, BlockVector maximumPoint) {
            cuboidRegion = new com.sk89q.worldedit.regions.CuboidRegion(minimumPoint.blockVector, maximumPoint.blockVector);
        }
    }

    public static class RegionSelector {
        private com.sk89q.worldedit.regions.RegionSelector regionSelector;

        public RegionSelector(com.sk89q.worldedit.regions.RegionSelector regionSelector) {
            this.regionSelector = regionSelector;
        }

        public void clear() {
            regionSelector.clear();
        }

        public void selectPrimary(BlockVector minimumPoint) {
            regionSelector.selectPrimary(minimumPoint.blockVector);
        }

        public void selectSecondary(BlockVector maximumPoint) {
            regionSelector.selectSecondary(maximumPoint.blockVector);
        }

        public void learnChanges() {
            regionSelector.learnChanges();
        }

        public void explainRegionAdjust(BukkitPlayer bPlayer, LocalSession session) {
            regionSelector.explainRegionAdjust(bPlayer.localPlayer, session.localSession);
        }
    }

    public static class ChunkStore {
        private com.sk89q.worldedit.data.ChunkStore chunkStore;

        public ChunkStore(com.sk89q.worldedit.data.ChunkStore chunkStore) {
            this.chunkStore = chunkStore;
        }

        public void close() throws IOException {
            chunkStore.close();
        }
    }

    public static class Vector2D {
        private com.sk89q.worldedit.Vector2D vector2D;

        public Vector2D(com.sk89q.worldedit.Vector2D vector2D) {
            this.vector2D = vector2D;
        }

        public Vector2D add(Vector2D other) {
            return new Vector2D(this.vector2D.add(other.vector2D));
        }
    }

    public static class SnapshotRestore {
        private com.sk89q.worldedit.snapshots.SnapshotRestore snapshotRestore;

        public SnapshotRestore(ChunkStore chunkStore, EditSession session, CuboidRegion restoreRegion) {
            snapshotRestore = new com.sk89q.worldedit.snapshots.SnapshotRestore(chunkStore.chunkStore, session.editSession, restoreRegion.cuboidRegion);
        }

        public void restore() throws MaxChangedBlocksException {
            try {
                snapshotRestore.restore();
            } catch (com.sk89q.worldedit.MaxChangedBlocksException e) {
                throw new MaxChangedBlocksException(e);
            }
        }

        public boolean hadTotalFailure() {
            return snapshotRestore.hadTotalFailure();
        }

        public List<Vector2D> getErrorChunks() {
            List<com.sk89q.worldedit.Vector2D> errorChunks = snapshotRestore.getErrorChunks();

            List<Vector2D> list = null;
            if (errorChunks != null) {
                list = new ArrayList<Vector2D>();
                for (com.sk89q.worldedit.Vector2D errorChunk : errorChunks) {
                    list.add(new Vector2D(errorChunk));
                }
            }

            return list;
        }

        public List<Vector2D> getMissingChunks() {
            List<com.sk89q.worldedit.Vector2D> missingChunks = snapshotRestore.getMissingChunks();

            List<Vector2D> list = null;
            if (missingChunks != null) {
                list = new ArrayList<Vector2D>();
                for (com.sk89q.worldedit.Vector2D errorChunk : missingChunks) {
                    list.add(new Vector2D(errorChunk));
                }
            }

            return list;
        }

        public String getLastErrorMessage() {
            return snapshotRestore.getLastErrorMessage();
        }
    }

    public static class BlockID {
        public static final int AIR = com.sk89q.worldedit.blocks.BlockID.AIR;
        public static final int STONE = com.sk89q.worldedit.blocks.BlockID.STONE;
        public static final int GRASS = com.sk89q.worldedit.blocks.BlockID.GRASS;
        public static final int DIRT = com.sk89q.worldedit.blocks.BlockID.DIRT;
        public static final int COBBLESTONE = com.sk89q.worldedit.blocks.BlockID.COBBLESTONE;
        public static final int WOOD = com.sk89q.worldedit.blocks.BlockID.WOOD;
        public static final int SAPLING = com.sk89q.worldedit.blocks.BlockID.SAPLING;
        public static final int BEDROCK = com.sk89q.worldedit.blocks.BlockID.BEDROCK;
        public static final int WATER = com.sk89q.worldedit.blocks.BlockID.WATER;
        public static final int STATIONARY_WATER = com.sk89q.worldedit.blocks.BlockID.STATIONARY_WATER;
        public static final int LAVA = com.sk89q.worldedit.blocks.BlockID.LAVA;
        public static final int STATIONARY_LAVA = com.sk89q.worldedit.blocks.BlockID.STATIONARY_LAVA;
        public static final int SAND = com.sk89q.worldedit.blocks.BlockID.SAND;
        public static final int GRAVEL = com.sk89q.worldedit.blocks.BlockID.GRAVEL;
        public static final int GOLD_ORE = com.sk89q.worldedit.blocks.BlockID.GOLD_ORE;
        public static final int IRON_ORE = com.sk89q.worldedit.blocks.BlockID.IRON_ORE;
        public static final int COAL_ORE = com.sk89q.worldedit.blocks.BlockID.COAL_ORE;
        public static final int LOG = com.sk89q.worldedit.blocks.BlockID.LOG;
        public static final int LEAVES = com.sk89q.worldedit.blocks.BlockID.LEAVES;
        public static final int SPONGE = com.sk89q.worldedit.blocks.BlockID.SPONGE;
        public static final int GLASS = com.sk89q.worldedit.blocks.BlockID.GLASS;
        public static final int LAPIS_LAZULI_ORE = com.sk89q.worldedit.blocks.BlockID.LAPIS_LAZULI_ORE;
        public static final int LAPIS_LAZULI_BLOCK = com.sk89q.worldedit.blocks.BlockID.LAPIS_LAZULI_BLOCK;
        public static final int DISPENSER = com.sk89q.worldedit.blocks.BlockID.DISPENSER;
        public static final int SANDSTONE = com.sk89q.worldedit.blocks.BlockID.SANDSTONE;
        public static final int NOTE_BLOCK = com.sk89q.worldedit.blocks.BlockID.NOTE_BLOCK;
        public static final int BED = com.sk89q.worldedit.blocks.BlockID.BED;
        public static final int POWERED_RAIL = com.sk89q.worldedit.blocks.BlockID.POWERED_RAIL;
        public static final int DETECTOR_RAIL = com.sk89q.worldedit.blocks.BlockID.DETECTOR_RAIL;
        public static final int PISTON_STICKY_BASE = com.sk89q.worldedit.blocks.BlockID.PISTON_STICKY_BASE;
        public static final int WEB = com.sk89q.worldedit.blocks.BlockID.WEB;
        public static final int LONG_GRASS = com.sk89q.worldedit.blocks.BlockID.LONG_GRASS;
        public static final int DEAD_BUSH = com.sk89q.worldedit.blocks.BlockID.DEAD_BUSH;
        public static final int PISTON_BASE = com.sk89q.worldedit.blocks.BlockID.PISTON_BASE;
        public static final int PISTON_EXTENSION = com.sk89q.worldedit.blocks.BlockID.PISTON_EXTENSION;
        public static final int CLOTH = com.sk89q.worldedit.blocks.BlockID.CLOTH;
        public static final int PISTON_MOVING_PIECE = com.sk89q.worldedit.blocks.BlockID.PISTON_MOVING_PIECE;
        public static final int YELLOW_FLOWER = com.sk89q.worldedit.blocks.BlockID.YELLOW_FLOWER;
        public static final int RED_FLOWER = com.sk89q.worldedit.blocks.BlockID.RED_FLOWER;
        public static final int BROWN_MUSHROOM = com.sk89q.worldedit.blocks.BlockID.BROWN_MUSHROOM;
        public static final int RED_MUSHROOM = com.sk89q.worldedit.blocks.BlockID.RED_MUSHROOM;
        public static final int GOLD_BLOCK = com.sk89q.worldedit.blocks.BlockID.GOLD_BLOCK;
        public static final int IRON_BLOCK = com.sk89q.worldedit.blocks.BlockID.IRON_BLOCK;
        public static final int DOUBLE_STEP = com.sk89q.worldedit.blocks.BlockID.DOUBLE_STEP;
        public static final int STEP = com.sk89q.worldedit.blocks.BlockID.STEP;
        public static final int BRICK = com.sk89q.worldedit.blocks.BlockID.BRICK;
        public static final int TNT = com.sk89q.worldedit.blocks.BlockID.TNT;
        public static final int BOOKCASE = com.sk89q.worldedit.blocks.BlockID.BOOKCASE;
        public static final int MOSSY_COBBLESTONE = com.sk89q.worldedit.blocks.BlockID.MOSSY_COBBLESTONE;
        public static final int OBSIDIAN = com.sk89q.worldedit.blocks.BlockID.OBSIDIAN;
        public static final int TORCH = com.sk89q.worldedit.blocks.BlockID.TORCH;
        public static final int FIRE = com.sk89q.worldedit.blocks.BlockID.FIRE;
        public static final int MOB_SPAWNER = com.sk89q.worldedit.blocks.BlockID.MOB_SPAWNER;
        public static final int WOODEN_STAIRS = com.sk89q.worldedit.blocks.BlockID.WOODEN_STAIRS;
        public static final int OAK_WOOD_STAIRS = com.sk89q.worldedit.blocks.BlockID.OAK_WOOD_STAIRS;
        public static final int CHEST = com.sk89q.worldedit.blocks.BlockID.CHEST;
        public static final int REDSTONE_WIRE = com.sk89q.worldedit.blocks.BlockID.REDSTONE_WIRE;
        public static final int DIAMOND_ORE = com.sk89q.worldedit.blocks.BlockID.DIAMOND_ORE;
        public static final int DIAMOND_BLOCK = com.sk89q.worldedit.blocks.BlockID.DIAMOND_BLOCK;
        public static final int WORKBENCH = com.sk89q.worldedit.blocks.BlockID.WORKBENCH;
        public static final int CROPS = com.sk89q.worldedit.blocks.BlockID.CROPS;
        public static final int SOIL = com.sk89q.worldedit.blocks.BlockID.SOIL;
        public static final int FURNACE = com.sk89q.worldedit.blocks.BlockID.FURNACE;
        public static final int BURNING_FURNACE = com.sk89q.worldedit.blocks.BlockID.BURNING_FURNACE;
        public static final int SIGN_POST = com.sk89q.worldedit.blocks.BlockID.SIGN_POST;
        public static final int WOODEN_DOOR = com.sk89q.worldedit.blocks.BlockID.WOODEN_DOOR;
        public static final int LADDER = com.sk89q.worldedit.blocks.BlockID.LADDER;
        public static final int MINECART_TRACKS = com.sk89q.worldedit.blocks.BlockID.MINECART_TRACKS;
        public static final int COBBLESTONE_STAIRS = com.sk89q.worldedit.blocks.BlockID.COBBLESTONE_STAIRS;
        public static final int WALL_SIGN = com.sk89q.worldedit.blocks.BlockID.WALL_SIGN;
        public static final int LEVER = com.sk89q.worldedit.blocks.BlockID.LEVER;
        public static final int STONE_PRESSURE_PLATE = com.sk89q.worldedit.blocks.BlockID.STONE_PRESSURE_PLATE;
        public static final int IRON_DOOR = com.sk89q.worldedit.blocks.BlockID.IRON_DOOR;
        public static final int WOODEN_PRESSURE_PLATE = com.sk89q.worldedit.blocks.BlockID.WOODEN_PRESSURE_PLATE;
        public static final int REDSTONE_ORE = com.sk89q.worldedit.blocks.BlockID.REDSTONE_ORE;
        public static final int GLOWING_REDSTONE_ORE = com.sk89q.worldedit.blocks.BlockID.GLOWING_REDSTONE_ORE;
        public static final int REDSTONE_TORCH_OFF = com.sk89q.worldedit.blocks.BlockID.REDSTONE_TORCH_OFF;
        public static final int REDSTONE_TORCH_ON = com.sk89q.worldedit.blocks.BlockID.REDSTONE_TORCH_ON;
        public static final int STONE_BUTTON = com.sk89q.worldedit.blocks.BlockID.STONE_BUTTON;
        public static final int SNOW = com.sk89q.worldedit.blocks.BlockID.SNOW;
        public static final int ICE = com.sk89q.worldedit.blocks.BlockID.ICE;
        public static final int SNOW_BLOCK = com.sk89q.worldedit.blocks.BlockID.SNOW_BLOCK;
        public static final int CACTUS = com.sk89q.worldedit.blocks.BlockID.CACTUS;
        public static final int CLAY = com.sk89q.worldedit.blocks.BlockID.CLAY;
        public static final int REED = com.sk89q.worldedit.blocks.BlockID.REED;
        public static final int JUKEBOX = com.sk89q.worldedit.blocks.BlockID.JUKEBOX;
        public static final int FENCE = com.sk89q.worldedit.blocks.BlockID.FENCE;
        public static final int PUMPKIN = com.sk89q.worldedit.blocks.BlockID.PUMPKIN;
        public static final int NETHERSTONE = com.sk89q.worldedit.blocks.BlockID.NETHERSTONE;
        public static final int NETHERRACK = com.sk89q.worldedit.blocks.BlockID.NETHERRACK;
        public static final int SLOW_SAND = com.sk89q.worldedit.blocks.BlockID.SLOW_SAND;
        public static final int LIGHTSTONE = com.sk89q.worldedit.blocks.BlockID.LIGHTSTONE;
        public static final int PORTAL = com.sk89q.worldedit.blocks.BlockID.PORTAL;
        public static final int JACKOLANTERN = com.sk89q.worldedit.blocks.BlockID.JACKOLANTERN;
        public static final int CAKE_BLOCK = com.sk89q.worldedit.blocks.BlockID.CAKE_BLOCK;
        public static final int REDSTONE_REPEATER_OFF = com.sk89q.worldedit.blocks.BlockID.REDSTONE_REPEATER_OFF;
        public static final int REDSTONE_REPEATER_ON = com.sk89q.worldedit.blocks.BlockID.REDSTONE_REPEATER_ON;
        public static final int LOCKED_CHEST = com.sk89q.worldedit.blocks.BlockID.LOCKED_CHEST;
        public static final int TRAP_DOOR = com.sk89q.worldedit.blocks.BlockID.TRAP_DOOR;
        public static final int SILVERFISH_BLOCK = com.sk89q.worldedit.blocks.BlockID.SILVERFISH_BLOCK;
        public static final int STONE_BRICK = com.sk89q.worldedit.blocks.BlockID.STONE_BRICK;
        public static final int BROWN_MUSHROOM_CAP = com.sk89q.worldedit.blocks.BlockID.BROWN_MUSHROOM_CAP;
        public static final int RED_MUSHROOM_CAP = com.sk89q.worldedit.blocks.BlockID.RED_MUSHROOM_CAP;
        public static final int IRON_BARS = com.sk89q.worldedit.blocks.BlockID.IRON_BARS;
        public static final int GLASS_PANE = com.sk89q.worldedit.blocks.BlockID.GLASS_PANE;
        public static final int MELON_BLOCK = com.sk89q.worldedit.blocks.BlockID.MELON_BLOCK;
        public static final int PUMPKIN_STEM = com.sk89q.worldedit.blocks.BlockID.PUMPKIN_STEM;
        public static final int MELON_STEM = com.sk89q.worldedit.blocks.BlockID.MELON_STEM;
        public static final int VINE = com.sk89q.worldedit.blocks.BlockID.VINE;
        public static final int FENCE_GATE = com.sk89q.worldedit.blocks.BlockID.FENCE_GATE;
        public static final int BRICK_STAIRS = com.sk89q.worldedit.blocks.BlockID.BRICK_STAIRS;
        public static final int STONE_BRICK_STAIRS = com.sk89q.worldedit.blocks.BlockID.STONE_BRICK_STAIRS;
        public static final int MYCELIUM = com.sk89q.worldedit.blocks.BlockID.MYCELIUM;
        public static final int LILY_PAD = com.sk89q.worldedit.blocks.BlockID.LILY_PAD;
        public static final int NETHER_BRICK = com.sk89q.worldedit.blocks.BlockID.NETHER_BRICK;
        public static final int NETHER_BRICK_FENCE = com.sk89q.worldedit.blocks.BlockID.NETHER_BRICK_FENCE;
        public static final int NETHER_BRICK_STAIRS = com.sk89q.worldedit.blocks.BlockID.NETHER_BRICK_STAIRS;
        public static final int NETHER_WART = com.sk89q.worldedit.blocks.BlockID.NETHER_WART;
        public static final int ENCHANTMENT_TABLE = com.sk89q.worldedit.blocks.BlockID.ENCHANTMENT_TABLE;
        public static final int BREWING_STAND = com.sk89q.worldedit.blocks.BlockID.BREWING_STAND;
        public static final int CAULDRON = com.sk89q.worldedit.blocks.BlockID.CAULDRON;
        public static final int END_PORTAL = com.sk89q.worldedit.blocks.BlockID.END_PORTAL;
        public static final int END_PORTAL_FRAME = com.sk89q.worldedit.blocks.BlockID.END_PORTAL_FRAME;
        public static final int END_STONE = com.sk89q.worldedit.blocks.BlockID.END_STONE;
        public static final int DRAGON_EGG = com.sk89q.worldedit.blocks.BlockID.DRAGON_EGG;
        public static final int REDSTONE_LAMP_OFF = com.sk89q.worldedit.blocks.BlockID.REDSTONE_LAMP_OFF;
        public static final int REDSTONE_LAMP_ON = com.sk89q.worldedit.blocks.BlockID.REDSTONE_LAMP_ON;
        public static final int DOUBLE_WOODEN_STEP = com.sk89q.worldedit.blocks.BlockID.DOUBLE_WOODEN_STEP;
        public static final int WOODEN_STEP = com.sk89q.worldedit.blocks.BlockID.WOODEN_STEP;
        public static final int COCOA_PLANT = com.sk89q.worldedit.blocks.BlockID.COCOA_PLANT;
        public static final int SANDSTONE_STAIRS = com.sk89q.worldedit.blocks.BlockID.SANDSTONE_STAIRS;
        public static final int EMERALD_ORE = com.sk89q.worldedit.blocks.BlockID.EMERALD_ORE;
        public static final int ENDER_CHEST = com.sk89q.worldedit.blocks.BlockID.ENDER_CHEST;
        public static final int TRIPWIRE_HOOK = com.sk89q.worldedit.blocks.BlockID.TRIPWIRE_HOOK;
        public static final int TRIPWIRE = com.sk89q.worldedit.blocks.BlockID.TRIPWIRE;
        public static final int EMERALD_BLOCK = com.sk89q.worldedit.blocks.BlockID.EMERALD_BLOCK;
        public static final int SPRUCE_WOOD_STAIRS = com.sk89q.worldedit.blocks.BlockID.SPRUCE_WOOD_STAIRS;
        public static final int BIRCH_WOOD_STAIRS = com.sk89q.worldedit.blocks.BlockID.BIRCH_WOOD_STAIRS;
        public static final int JUNGLE_WOOD_STAIRS = com.sk89q.worldedit.blocks.BlockID.JUNGLE_WOOD_STAIRS;
        public static final int COMMAND_BLOCK = com.sk89q.worldedit.blocks.BlockID.COMMAND_BLOCK;
        public static final int BEACON = com.sk89q.worldedit.blocks.BlockID.BEACON;
        public static final int COBBLESTONE_WALL = com.sk89q.worldedit.blocks.BlockID.COBBLESTONE_WALL;
        public static final int FLOWER_POT = com.sk89q.worldedit.blocks.BlockID.FLOWER_POT;
        public static final int CARROTS = com.sk89q.worldedit.blocks.BlockID.CARROTS;
        public static final int POTATOES = com.sk89q.worldedit.blocks.BlockID.POTATOES;
        public static final int WOODEN_BUTTON = com.sk89q.worldedit.blocks.BlockID.WOODEN_BUTTON;
        public static final int HEAD = com.sk89q.worldedit.blocks.BlockID.HEAD;
        public static final int ANVIL = com.sk89q.worldedit.blocks.BlockID.ANVIL;
        public static final int TRAPPED_CHEST = com.sk89q.worldedit.blocks.BlockID.TRAPPED_CHEST;
        public static final int PRESSURE_PLATE_LIGHT = com.sk89q.worldedit.blocks.BlockID.PRESSURE_PLATE_LIGHT;
        public static final int PRESSURE_PLATE_HEAVY = com.sk89q.worldedit.blocks.BlockID.PRESSURE_PLATE_HEAVY;
        public static final int COMPARATOR_OFF = com.sk89q.worldedit.blocks.BlockID.COMPARATOR_OFF;
        public static final int COMPARATOR_ON = com.sk89q.worldedit.blocks.BlockID.COMPARATOR_ON;
        public static final int DAYLIGHT_SENSOR = com.sk89q.worldedit.blocks.BlockID.DAYLIGHT_SENSOR;
        public static final int REDSTONE_BLOCK = com.sk89q.worldedit.blocks.BlockID.REDSTONE_BLOCK;
        public static final int QUARTZ_ORE = com.sk89q.worldedit.blocks.BlockID.QUARTZ_ORE;
        public static final int HOPPER = com.sk89q.worldedit.blocks.BlockID.HOPPER;
        public static final int QUARTZ_BLOCK = com.sk89q.worldedit.blocks.BlockID.QUARTZ_BLOCK;
        public static final int QUARTZ_STAIRS = com.sk89q.worldedit.blocks.BlockID.QUARTZ_STAIRS;
        public static final int ACTIVATOR_RAIL = com.sk89q.worldedit.blocks.BlockID.ACTIVATOR_RAIL;
        public static final int DROPPER = com.sk89q.worldedit.blocks.BlockID.DROPPER;
        public static final int STAINED_CLAY = com.sk89q.worldedit.blocks.BlockID.STAINED_CLAY;
        public static final int HAY_BLOCK = com.sk89q.worldedit.blocks.BlockID.HAY_BLOCK;
        public static final int CARPET = com.sk89q.worldedit.blocks.BlockID.CARPET;
        public static final int HARDENED_CLAY = com.sk89q.worldedit.blocks.BlockID.HARDENED_CLAY;
        public static final int COAL_BLOCK = com.sk89q.worldedit.blocks.BlockID.COAL_BLOCK;
    };

    public static class ServerInterface {
        private com.sk89q.worldedit.ServerInterface serverInterface;

        public ServerInterface(com.sk89q.worldedit.ServerInterface serverInterface) {
            this.serverInterface = serverInterface;
        }
    }

    public static class LocalPlayer {
        protected com.sk89q.worldedit.LocalPlayer localPlayer;
    }

    public static class BukkitPlayer extends LocalPlayer {
        public BukkitPlayer(ServerInterface serverInterface, Player player) {
            localPlayer = new com.sk89q.worldedit.bukkit.BukkitPlayer(worldEditPlugin, serverInterface.serverInterface, player);
        }

        public LocalWorld getWorld() {
            return new LocalWorld(localPlayer.getWorld());
        }
    }

    public static class Snapshot {
        private com.sk89q.worldedit.snapshots.Snapshot snapshot;

        public Snapshot(com.sk89q.worldedit.snapshots.Snapshot snapshot) {
            this.snapshot = snapshot;
        }

        public ChunkStore getChunkStore() throws DataException, IOException {
            try {
                return new ChunkStore(snapshot.getChunkStore());
            } catch (com.sk89q.worldedit.data.DataException e) {
                throw new DataException(e);
            }
        }
    }

    public static class WorldEdit {
        private com.sk89q.worldedit.WorldEdit worldEdit;

        public WorldEdit(com.sk89q.worldedit.WorldEdit worldEdit) {
            this.worldEdit = worldEdit;
        }

        public LocalConfiguration getConfiguration() {
            return new LocalConfiguration(worldEdit.getConfiguration());
        }
    }

    public static class LocalConfiguration {
        private com.sk89q.worldedit.LocalConfiguration configuration;

        public LocalConfiguration(com.sk89q.worldedit.LocalConfiguration configuration) {
            this.configuration = configuration;
        }

        public File getWorkingDirectory() {
            return configuration.getWorkingDirectory();
        }

        public SnapshotRepository getSnapshotRepo() {
            return new SnapshotRepository(configuration.snapshotRepo);
        }

        public void setSnapshotRepo(SnapshotRepository snapshotRepo) {
            configuration.snapshotRepo = snapshotRepo.snapshotRepo;
        }
    }

    public static class SnapshotRepository {
        private com.sk89q.worldedit.snapshots.SnapshotRepository snapshotRepo;

        public SnapshotRepository(com.sk89q.worldedit.snapshots.SnapshotRepository snapshotRepo) {
            this.snapshotRepo = snapshotRepo;
        }

        public Snapshot getSnapshot(String baseName) throws InvalidSnapshotException {
            try {
                return new Snapshot(snapshotRepo.getSnapshot(baseName));
            } catch (com.sk89q.worldedit.snapshots.InvalidSnapshotException e) {
                throw new InvalidSnapshotException(e);
            }
        }
    }
}
