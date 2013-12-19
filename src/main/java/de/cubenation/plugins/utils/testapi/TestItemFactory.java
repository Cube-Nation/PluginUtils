package de.cubenation.plugins.utils.testapi;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestItemFactory implements ItemFactory {
    @Override
    public ItemMeta getItemMeta(Material material) {
        return null;
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
        return true;
    }

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {
        if (meta1 == null && meta2 == null) {
            return true;
        } else if (meta1 == null) {
            return false;
        } else if (meta2 == null) {
            return false;
        }
        return meta1.getDisplayName().equals(meta2.getDisplayName());
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
        return meta;
    }

    @Override
    public Color getDefaultLeatherColor() {
        return null;
    }
}
