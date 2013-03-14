package de.cubenation.plugins.utils.commandapi.testutils;

import java.util.Collection;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class TestBlock implements Block {
    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
    }

    @Override
    public byte getData() {
        return 0;
    }

    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return null;
    }

    @Override
    public Block getRelative(BlockFace face) {
        return null;
    }

    @Override
    public Block getRelative(BlockFace face, int distance) {
        return null;
    }

    @Override
    public Material getType() {
        return null;
    }

    @Override
    public int getTypeId() {
        return 0;
    }

    @Override
    public byte getLightLevel() {
        return 0;
    }

    @Override
    public byte getLightFromSky() {
        return 0;
    }

    @Override
    public byte getLightFromBlocks() {
        return 0;
    }

    @Override
    public World getWorld() {
        return new TestWorld() {
            @Override
            public String getName() {
                return "testWorld";
            }
        };
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Location getLocation(Location loc) {
        return null;
    }

    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    public void setData(byte data) {
    }

    @Override
    public void setData(byte data, boolean applyPhysics) {
    }

    @Override
    public void setType(Material type) {
    }

    @Override
    public boolean setTypeId(int type) {
        return false;
    }

    @Override
    public boolean setTypeId(int type, boolean applyPhysics) {
        return false;
    }

    @Override
    public boolean setTypeIdAndData(int type, byte data, boolean applyPhysics) {
        return false;
    }

    @Override
    public BlockFace getFace(Block block) {
        return null;
    }

    @Override
    public BlockState getState() {
        return null;
    }

    @Override
    public Biome getBiome() {
        return null;
    }

    @Override
    public void setBiome(Biome bio) {
    }

    @Override
    public boolean isBlockPowered() {
        return false;
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return false;
    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        return false;
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        return false;
    }

    @Override
    public int getBlockPower(BlockFace face) {
        return 0;
    }

    @Override
    public int getBlockPower() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isLiquid() {
        return false;
    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public double getHumidity() {
        return 0;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public boolean breakNaturally() {
        return false;
    }

    @Override
    public boolean breakNaturally(ItemStack tool) {
        return false;
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return null;
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        return null;
    }
}
