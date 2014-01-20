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
        public static final int FENCE = com.sk89q.worldedit.blocks.BlockID.FENCE;
        public static final int TORCH = com.sk89q.worldedit.blocks.BlockID.TORCH;
        public static final int AIR = com.sk89q.worldedit.blocks.BlockID.AIR;
        public static final int LEAVES = com.sk89q.worldedit.blocks.BlockID.LEAVES;
        public static final int LOG = com.sk89q.worldedit.blocks.BlockID.LOG;
        public static final int LONG_GRASS = com.sk89q.worldedit.blocks.BlockID.LONG_GRASS;
        public static final int DEAD_BUSH = com.sk89q.worldedit.blocks.BlockID.DEAD_BUSH;
        public static final int RED_FLOWER = com.sk89q.worldedit.blocks.BlockID.RED_FLOWER;
        public static final int YELLOW_FLOWER = com.sk89q.worldedit.blocks.BlockID.YELLOW_FLOWER;
        public static final int SAPLING = com.sk89q.worldedit.blocks.BlockID.SAPLING;
        public static final int REED = com.sk89q.worldedit.blocks.BlockID.REED;
        public static final int SNOW = com.sk89q.worldedit.blocks.BlockID.SNOW;
        public static final int VINE = com.sk89q.worldedit.blocks.BlockID.VINE;
        public static final int SIGN_POST = com.sk89q.worldedit.blocks.BlockID.SIGN_POST;
        public static final int CHEST = com.sk89q.worldedit.blocks.BlockID.CHEST;
        public static final int COBBLESTONE_WALL = com.sk89q.worldedit.blocks.BlockID.COBBLESTONE_WALL;
        public static final int LIGHTSTONE = com.sk89q.worldedit.blocks.BlockID.LIGHTSTONE;
        // TODO
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
