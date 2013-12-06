package de.cubenation.plugins.utils.wrapperapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.wrapperapi.WorldEditWrapper.BlockVector;
import de.cubenation.plugins.utils.wrapperapi.WorldEditWrapper.BlockVector2D;

public class WorldGuardWrapper {
    private static com.sk89q.worldguard.bukkit.WorldGuardPlugin worldGuardPlugin = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        WorldGuardWrapper.log = log;
    }

    public static void loadPlugin() {
        if (worldGuardPlugin == null) {
            worldGuardPlugin = (com.sk89q.worldguard.bukkit.WorldGuardPlugin) Bukkit.getServer().getPluginManager()
                    .getPlugin(WrapperManager.Plugins.WORLD_GUARD.getName());
            if (worldGuardPlugin == null) {
                log.info(WrapperManager.Plugins.WORLD_GUARD.getName() + " not found");
            }
        }
    }

    public static class ProtectedRegion {
        protected com.sk89q.worldguard.protection.regions.ProtectedRegion protectedRegion;

        public ProtectedRegion(com.sk89q.worldguard.protection.regions.ProtectedRegion protectedRegion) {
            this.protectedRegion = protectedRegion;
        }

        public List<BlockVector2D> getPoints() {
            List<com.sk89q.worldedit.BlockVector2D> points = protectedRegion.getPoints();

            List<BlockVector2D> list = new ArrayList<WorldEditWrapper.BlockVector2D>();
            for (com.sk89q.worldedit.BlockVector2D point : points) {
                list.add(new BlockVector2D(point));
            }

            return list;
        }

        public boolean contains(int x, int y, int z) {
            return protectedRegion.contains(x, y, z);
        }

        public DefaultDomain getMembers() {
            return new DefaultDomain(protectedRegion.getMembers());
        }

        public void setMembers(DefaultDomain defaultDomain) {
            protectedRegion.setMembers(defaultDomain.defaultDomain);
        }

        public Object getFlag(Flag param) {
            com.sk89q.worldguard.protection.flags.Flag<?> convParam = null;
            if (param.getName().equals("BUILD")) {
                convParam = com.sk89q.worldguard.protection.flags.DefaultFlag.BUILD;
            } else if (param.getName().equals("GREET_MESSAGE")) {
                convParam = com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE;
            }

            Object flag = protectedRegion.getFlag(convParam);

            if (param.getName().equals("BUILD")) {
                if (flag.equals(com.sk89q.worldguard.protection.flags.StateFlag.State.ALLOW)) {
                    return StateFlag.State.ALLOW;
                } else if (flag.equals(com.sk89q.worldguard.protection.flags.StateFlag.State.DENY)) {
                    return StateFlag.State.DENY;
                }
            } else if (param.getName().equals("GREET_MESSAGE")) {
                return (String) flag;
            }

            return null;
        }

        public void setFlag(Flag param1, Object param2) {
            if (param1.getName().equals("BUILD")) {
                com.sk89q.worldguard.protection.flags.StateFlag.State convParam = null;
                if (param2.equals(StateFlag.State.ALLOW)) {
                    convParam = com.sk89q.worldguard.protection.flags.StateFlag.State.ALLOW;
                } else if (param2.equals(StateFlag.State.DENY)) {
                    convParam = com.sk89q.worldguard.protection.flags.StateFlag.State.DENY;
                }

                protectedRegion.setFlag(com.sk89q.worldguard.protection.flags.DefaultFlag.BUILD, convParam);
            } else if (param1.getName().equals("GREET_MESSAGE")) {
                protectedRegion.setFlag(com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE, (String) param2);
            }
        }

        public DefaultDomain getOwners() {
            return new DefaultDomain(protectedRegion.getOwners());
        }

        public String getId() {
            return protectedRegion.getId();
        }

        public BlockVector getMinimumPoint() {
            return new BlockVector(protectedRegion.getMinimumPoint());
        }

        public BlockVector getMaximumPoint() {
            return new BlockVector(protectedRegion.getMaximumPoint());
        }

        public List<ProtectedRegion> getIntersectingRegions(ArrayList<ProtectedRegion> userRegion) throws UnsupportedIntersectionException {
            ArrayList<com.sk89q.worldguard.protection.regions.ProtectedRegion> list = new ArrayList<com.sk89q.worldguard.protection.regions.ProtectedRegion>();

            for (ProtectedRegion region : userRegion) {
                list.add(region.protectedRegion);
            }

            List<com.sk89q.worldguard.protection.regions.ProtectedRegion> intersectingRegions;
            try {
                intersectingRegions = protectedRegion.getIntersectingRegions(list);
            } catch (com.sk89q.worldguard.protection.UnsupportedIntersectionException e) {
                throw new UnsupportedIntersectionException(e);
            }

            ArrayList<ProtectedRegion> newList = new ArrayList<ProtectedRegion>();
            for (com.sk89q.worldguard.protection.regions.ProtectedRegion intersectingRegion : intersectingRegions) {
                newList.add(new ProtectedRegion(intersectingRegion));
            }

            return newList;
        }
    }

    public static boolean canBuild(Player player, Block clickedBlock) {
        if (worldGuardPlugin == null) {
            loadPlugin();
        }

        if (worldGuardPlugin != null) {
            return worldGuardPlugin.canBuild(player, clickedBlock);
        }
        return false;
    }

    public static GlobalRegionManager getGlobalRegionManager() {
        if (worldGuardPlugin == null) {
            loadPlugin();
        }

        if (worldGuardPlugin != null) {
            return new GlobalRegionManager(worldGuardPlugin.getGlobalRegionManager());
        }
        return null;
    }

    public static class GlobalRegionManager {
        private com.sk89q.worldguard.protection.GlobalRegionManager globalRegionManager;

        public GlobalRegionManager(com.sk89q.worldguard.protection.GlobalRegionManager globalRegionManager) {
            this.globalRegionManager = globalRegionManager;
        }

        public RegionManager get(World world) {
            return new RegionManager(globalRegionManager.get(world));
        }
    }

    public static class RegionManager {
        private com.sk89q.worldguard.protection.managers.RegionManager regionManager;

        public RegionManager(com.sk89q.worldguard.protection.managers.RegionManager regionManager) {
            this.regionManager = regionManager;
        }

        public ProtectedRegion getRegion(String regionName) {
            return new ProtectedRegion(regionManager.getRegion(regionName));
        }

        public ApplicableRegionSet getApplicableRegions(Location location) {
            return new ApplicableRegionSet(regionManager.getApplicableRegions(location));
        }

        public void addRegion(ProtectedCuboidRegion newRegion) {
            regionManager.addRegion(newRegion.protectedRegion);
        }

        public void removeRegion(String id) {
            regionManager.removeRegion(id);
        }

        public ApplicableRegionSet getApplicableRegions(ProtectedRegion newRegion) {
            return new ApplicableRegionSet(regionManager.getApplicableRegions(newRegion.protectedRegion));
        }

        public void save() throws ProtectionDatabaseException {
            try {
                regionManager.save();
            } catch (com.sk89q.worldguard.protection.databases.ProtectionDatabaseException e) {
                throw new ProtectionDatabaseException(e);
            }
        }
    }

    public static class DefaultDomain {
        private com.sk89q.worldguard.domains.DefaultDomain defaultDomain;

        public DefaultDomain(com.sk89q.worldguard.domains.DefaultDomain defaultDomain) {
            this.defaultDomain = defaultDomain;
        }

        public DefaultDomain() {
            defaultDomain = new com.sk89q.worldguard.domains.DefaultDomain();
        }

        public void addPlayer(String memberToAdd) {
            defaultDomain.addPlayer(memberToAdd);
        }

        public boolean contains(LocalPlayer localPlayer) {
            return defaultDomain.contains(localPlayer.localPlayer);
        }

        public void addPlayer(BukkitPlayer bukkitPlayer) {
            defaultDomain.addPlayer(bukkitPlayer.localPlayer);
        }

        public boolean contains(String memberToRemove) {
            return defaultDomain.contains(memberToRemove);
        }

        public void removePlayer(String playerName) {
            defaultDomain.removePlayer(playerName);
        }

        public Set<String> getPlayers() {
            return defaultDomain.getPlayers();
        }

        public String toUserFriendlyString() {
            return defaultDomain.toUserFriendlyString();
        }

        public int size() {
            return defaultDomain.size();
        }
    }

    public static class LocalPlayer {
        protected com.sk89q.worldguard.LocalPlayer localPlayer;
    }

    public static class BukkitPlayer extends LocalPlayer {
        public BukkitPlayer(Player player) {
            localPlayer = new com.sk89q.worldguard.bukkit.BukkitPlayer(worldGuardPlugin, player);
        }
    }

    public static class ProtectedCuboidRegion extends ProtectedRegion {
        public ProtectedCuboidRegion(String string, BlockVector fromV, BlockVector toV) {
            super(new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(string, fromV.blockVector, toV.blockVector));
        }

        public void setOwners(DefaultDomain owner) {
            protectedRegion.setOwners(owner.defaultDomain);
        }

        public String getId() {
            return protectedRegion.getId();
        }

        public void setPriority(int i) {
            protectedRegion.setPriority(i);
        }
    }

    public static class DefaultFlag {
        public static StringFlag GREET_MESSAGE = new StringFlag() {
            @Override
            public String getName() {
                return "GREET_MESSAGE";
            }
        };
        public static StateFlag BUILD = new StateFlag() {
            @Override
            public String getName() {
                return "BUILD";
            }
        };
    }

    public static class Flag {
        public String getName() {
            return "";
        }
    }

    public static class StateFlag extends Flag {
        public enum State {
            DENY, ALLOW
        }
    }

    public static class StringFlag extends Flag {
    }

    @SuppressWarnings("serial")
    public static class UnsupportedIntersectionException extends Exception {
        public UnsupportedIntersectionException(com.sk89q.worldguard.protection.UnsupportedIntersectionException e) {
            super(e);
        }
    }

    @SuppressWarnings("serial")
    public static class ProtectionDatabaseException extends Exception {
        public ProtectionDatabaseException(com.sk89q.worldguard.protection.databases.ProtectionDatabaseException e) {
            super(e);
        }
    }

    public static class ApplicableRegionSet {
        private com.sk89q.worldguard.protection.ApplicableRegionSet applicableRegionSet;

        public ApplicableRegionSet(com.sk89q.worldguard.protection.ApplicableRegionSet applicableRegionSet) {
            this.applicableRegionSet = applicableRegionSet;
        }

        public Iterator<ProtectedRegion> iterator() {
            return new Iterator<ProtectedRegion>() {
                Iterator<com.sk89q.worldguard.protection.regions.ProtectedRegion> iterator = applicableRegionSet.iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public ProtectedRegion next() {
                    return new ProtectedRegion(iterator.next());
                }

                @Override
                public void remove() {
                    iterator.remove();
                }
            };
        }
    }

    public static class BukkitUtil {
        public static BlockVector toVector(Block block) {
            return new BlockVector(com.sk89q.worldguard.bukkit.BukkitUtil.toVector(block));
        }
    }
}
