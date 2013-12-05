package de.cubenation.plugins.utils.wrapperapi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class ShopkeepersWrapper {
    private static com.nisovin.shopkeepers.ShopkeepersPlugin shopkeepersPlugin;
    private static Logger log;

    public static void setLogger(Logger log) {
        ShopkeepersWrapper.log = log;
    }

    public static com.nisovin.shopkeepers.ShopkeepersPlugin loadPlugin() {
        if (shopkeepersPlugin == null) {
            shopkeepersPlugin = (com.nisovin.shopkeepers.ShopkeepersPlugin) Bukkit.getServer().getPluginManager().getPlugin("Shopkeepers");
            if (shopkeepersPlugin == null) {
                log.info("Shopkeepers not found");
                return null;
            }
        }
        return shopkeepersPlugin;
    }

    public static void removeActiveShopkeeper(String id) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (shopkeepersPlugin == null) {
            loadPlugin();
        }

        if (shopkeepersPlugin != null) {
            Field field = com.nisovin.shopkeepers.ShopkeepersPlugin.class.getDeclaredField("activeShopkeepers");
            field.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, com.nisovin.shopkeepers.Shopkeeper> map = (Map<String, com.nisovin.shopkeepers.Shopkeeper>) field.get(shopkeepersPlugin);

            map.remove(id);
        }
    }

    public static void removeShopkeepers(String chunk, Shopkeeper shopkeeper) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        if (shopkeepersPlugin == null) {
            loadPlugin();
        }

        if (shopkeepersPlugin != null) {
            Field field = com.nisovin.shopkeepers.ShopkeepersPlugin.class.getDeclaredField("allShopkeepersByChunk");
            field.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, List<com.nisovin.shopkeepers.Shopkeeper>> map = (Map<String, List<com.nisovin.shopkeepers.Shopkeeper>>) field.get(shopkeepersPlugin);

            map.get(chunk).remove(shopkeeper.shopkeeper);
        }
    }

    public static void save() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shopkeepersPlugin == null) {
            loadPlugin();
        }

        if (shopkeepersPlugin != null) {
            Method saveShopkeepers = com.nisovin.shopkeepers.ShopkeepersPlugin.class.getDeclaredMethod("save");
            saveShopkeepers.setAccessible(true);
            saveShopkeepers.invoke(shopkeepersPlugin);
        }
    }

    public static List<Shopkeeper> getShopkeepersInChunk(String worldName, int x, int z) {
        if (shopkeepersPlugin == null) {
            loadPlugin();
        }

        if (shopkeepersPlugin != null) {
            List<com.nisovin.shopkeepers.Shopkeeper> shopkeepersInChunks = shopkeepersPlugin.getShopkeepersInChunk(worldName, x, z);

            ArrayList<Shopkeeper> list = new ArrayList<Shopkeeper>();

            for (com.nisovin.shopkeepers.Shopkeeper shopkeepersInChunk : shopkeepersInChunks) {
                list.add(new Shopkeeper(shopkeepersInChunk));
            }

            return list;
        }
        return null;
    }

    public static class Shopkeeper {
        private com.nisovin.shopkeepers.Shopkeeper shopkeeper;

        public Shopkeeper(com.nisovin.shopkeepers.Shopkeeper shopkeeper) {
            this.shopkeeper = shopkeeper;
        }

        public String getId() {
            return shopkeeper.getId();
        }

        public String getChunk() {
            return shopkeeper.getChunk();
        }

        public ShopObject getShopObject() {
            return new ShopObject(shopkeeper.getShopObject());
        }

        public int getX() {
            return shopkeeper.getX();
        }

        public int getY() {
            return shopkeeper.getY();
        }

        public int getZ() {
            return shopkeeper.getZ();
        }
    }

    public static class ShopObject {
        private com.nisovin.shopkeepers.shopobjects.ShopObject shopObject;

        public ShopObject(com.nisovin.shopkeepers.shopobjects.ShopObject shopObject) {
            this.shopObject = shopObject;
        }

        public void delete() {
            shopObject.delete();
        }
    }
}
