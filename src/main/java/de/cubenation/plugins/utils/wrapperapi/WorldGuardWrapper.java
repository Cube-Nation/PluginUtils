package de.cubenation.plugins.utils.wrapperapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.wrapperapi.WorldEditWrapper.BlockVector;
import de.cubenation.plugins.utils.wrapperapi.WorldEditWrapper.BlockVector2D;
import de.cubenation.plugins.utils.wrapperapi.WorldGuardWrapper.ApplicableRegionSet;
import de.cubenation.plugins.utils.wrapperapi.WorldGuardWrapper.ProtectedCuboidRegion;
import de.cubenation.plugins.utils.wrapperapi.WorldGuardWrapper.ProtectedRegion;
import de.cubenation.plugins.utils.wrapperapi.WorldGuardWrapper.StateFlag;

public class WorldGuardWrapper {
    private static com.sk89q.worldguard.bukkit.WorldGuardPlugin worldGuardPlugin = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        WorldGuardWrapper.log = log;
    }

    public static com.sk89q.worldguard.bukkit.WorldGuardPlugin loadPlugin() {
        if (worldGuardPlugin == null) {
            worldGuardPlugin = (com.sk89q.worldguard.bukkit.WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
            if (worldGuardPlugin == null) {
                log.info("WorldGuard not found");
                return null;
            }
        }
        return worldGuardPlugin;
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

        public Object getFlag(StateFlag build) {
            return protectedRegion.getFlag(build.stateFlag);
        }

        public void setFlag(StateFlag build, Object object) {
            protectedRegion.setFlag(build.stateFlag, object);
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

    public static GlobalRegionManager getGlobalRegionManager() {
        return new GlobalRegionManager(worldGuardPlugin.getGlobalRegionManager());
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
        public static StateFlag GREET_MESSAGE;
        public static StateFlag BUILD;
    }

    public static class StateFlag {
        public enum State {
            DENY, ALLOW
        }
    }

    @SuppressWarnings("serial")
    public static class UnsupportedIntersectionException extends Exception {
        public UnsupportedIntersectionException(com.sk89q.worldguard.protection.UnsupportedIntersectionException e) {
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
