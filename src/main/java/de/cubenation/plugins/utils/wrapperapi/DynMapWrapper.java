package de.cubenation.plugins.utils.wrapperapi;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DynMapWrapper {
    private static org.dynmap.DynmapAPI dynmapAPI;
    private static Logger log;

    public static void setLogger(Logger log) {
        DynMapWrapper.log = log;
    }

    public static void loadPlugin() {
        if (dynmapAPI == null) {
            dynmapAPI = (org.dynmap.DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_DYNMAP);
            if (dynmapAPI == null) {
                log.info(WrapperManager.PLUGIN_NAME_DYNMAP + " not found");
            }
        }
    }

    public static class MarkerAPI {
        private org.dynmap.markers.MarkerAPI markerAPI;

        public MarkerAPI(org.dynmap.markers.MarkerAPI markerAPI) {
            this.markerAPI = markerAPI;
        }

        public MarkerIcon getMarkerIcon(String arg0) {
            return new MarkerIcon(markerAPI.getMarkerIcon(arg0));
        }

        public MarkerSet getMarkerSet(String arg0) {
            return new MarkerSet(markerAPI.getMarkerSet(arg0));
        }

        public MarkerSet createMarkerSet(String arg0, String arg1, Set<MarkerIcon> arg2, boolean arg3) {
            Set<org.dynmap.markers.MarkerIcon> newArg = new HashSet<org.dynmap.markers.MarkerIcon>();
            for (MarkerIcon arg : arg2) {
                newArg.add(arg.markerIcon);
            }
            return new MarkerSet(markerAPI.createMarkerSet(arg0, arg1, newArg, arg3));
        }

        public MarkerIcon createMarkerIcon(String arg0, String arg1, InputStream arg2) {
            return new MarkerIcon(markerAPI.createMarkerIcon(arg0, arg1, arg2));
        }

        public PlayerSet createPlayerSet(String arg0, boolean arg1, Set<String> arg2, boolean arg3) {
            return new PlayerSet(markerAPI.createPlayerSet(arg0, arg1, arg2, arg3));
        }

        public Set<MarkerIcon> getMarkerIcons() {
            Set<org.dynmap.markers.MarkerIcon> markerIcons = markerAPI.getMarkerIcons();

            HashSet<MarkerIcon> hashSet = new HashSet<MarkerIcon>();
            for (org.dynmap.markers.MarkerIcon markerIcon : markerIcons) {
                hashSet.add(new MarkerIcon(markerIcon));
            }

            return hashSet;
        }

        public Set<MarkerSet> getMarkerSets() {
            Set<org.dynmap.markers.MarkerSet> markerSets = markerAPI.getMarkerSets();

            HashSet<MarkerSet> hashSet = new HashSet<MarkerSet>();
            for (org.dynmap.markers.MarkerSet markerSet : markerSets) {
                hashSet.add(new MarkerSet(markerSet));
            }
            return hashSet;
        }

        public PlayerSet getPlayerSet(String arg0) {
            return new PlayerSet(markerAPI.getPlayerSet(arg0));
        }

        public Set<PlayerSet> getPlayerSets() {
            Set<org.dynmap.markers.PlayerSet> playerSets = markerAPI.getPlayerSets();

            HashSet<PlayerSet> hashSet = new HashSet<PlayerSet>();
            for (org.dynmap.markers.PlayerSet playerSet : playerSets) {
                hashSet.add(new PlayerSet(playerSet));
            }
            return hashSet;
        }
    }

    public static class GenericMarker {
        protected org.dynmap.markers.GenericMarker genericMarker;

        public GenericMarker(org.dynmap.markers.GenericMarker genericMarker) {
            this.genericMarker = genericMarker;
        }

        public void deleteMarker() {
            genericMarker.deleteMarker();
        }

        public String getMarkerID() {
            return genericMarker.getMarkerID();
        }

        public MarkerSet getMarkerSet() {
            return new MarkerSet(genericMarker.getMarkerSet());
        }

        public String getNormalizedWorld() {
            return genericMarker.getNormalizedWorld();
        }

        public String getWorld() {
            return genericMarker.getWorld();
        }

        public boolean isPersistentMarker() {
            return genericMarker.isPersistentMarker();
        }

        public void setMarkerSet(MarkerSet arg0) {
            genericMarker.setMarkerSet(arg0.markerSet);
        }
    }

    public static class Marker extends GenericMarker {
        public Marker(org.dynmap.markers.Marker marker) {
            super(marker);
        }

        public String getDescription() {
            return ((org.dynmap.markers.Marker) genericMarker).getDescription();
        }

        public String getLabel() {
            return ((org.dynmap.markers.Marker) genericMarker).getLabel();
        }

        public MarkerIcon getMarkerIcon() {
            return new MarkerIcon(((org.dynmap.markers.Marker) genericMarker).getMarkerIcon());
        }

        public double getX() {
            return ((org.dynmap.markers.Marker) genericMarker).getX();
        }

        public double getY() {
            return ((org.dynmap.markers.Marker) genericMarker).getY();
        }

        public double getZ() {
            return ((org.dynmap.markers.Marker) genericMarker).getZ();
        }

        public boolean isLabelMarkup() {
            return ((org.dynmap.markers.Marker) genericMarker).isLabelMarkup();
        }

        public void setDescription(String arg0) {
            ((org.dynmap.markers.Marker) genericMarker).setDescription(arg0);
        }

        public void setLabel(String arg0) {
            ((org.dynmap.markers.Marker) genericMarker).setLabel(arg0);
        }

        public void setLabel(String arg0, boolean arg1) {
            ((org.dynmap.markers.Marker) genericMarker).setLabel(arg0, arg1);
        }

        public void setLocation(String arg0, double arg1, double arg2, double arg3) {
            ((org.dynmap.markers.Marker) genericMarker).setLocation(arg0, arg1, arg2, arg3);
        }

        public boolean setMarkerIcon(MarkerIcon arg0) {
            return ((org.dynmap.markers.Marker) genericMarker).setMarkerIcon(arg0.markerIcon);
        }
    }

    public static class MarkerIcon {
        private org.dynmap.markers.MarkerIcon markerIcon;

        public MarkerIcon(org.dynmap.markers.MarkerIcon markerIcon) {
            this.markerIcon = markerIcon;
        }

        public void deleteIcon() {
            markerIcon.deleteIcon();
        }

        public String getMarkerIconID() {
            return markerIcon.getMarkerIconID();
        }

        public String getMarkerIconLabel() {
            return markerIcon.getMarkerIconLabel();
        }

        public MarkerSize getMarkerIconSize() {
            org.dynmap.markers.MarkerIcon.MarkerSize markerIconSize = markerIcon.getMarkerIconSize();

            if (markerIconSize.equals(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_8x8)) {
                return MarkerSize.MARKER_8x8;
            } else if (markerIconSize.equals(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_16x16)) {
                return MarkerSize.MARKER_16x16;
            } else if (markerIconSize.equals(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_32x32)) {
                return MarkerSize.MARKER_32x32;
            }

            return null;
        }

        public boolean isBuiltIn() {
            return markerIcon.isBuiltIn();
        }

        public void setMarkerIconImage(InputStream arg0) {
            markerIcon.setMarkerIconImage(arg0);
        }

        public void setMarkerIconLabel(String arg0) {
            markerIcon.setMarkerIconLabel(arg0);
        }

        public static enum MarkerSize {
            MARKER_16x16(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_16x16.getSize()), MARKER_32x32(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_32x32
                    .getSize()), MARKER_8x8(org.dynmap.markers.MarkerIcon.MarkerSize.MARKER_8x8.getSize());
            private String size;

            MarkerSize(String size) {
                this.size = size;
            }

            public String getSize() {
                return size;
            }
        }
    }

    public static class MarkerSet {
        private org.dynmap.markers.MarkerSet markerSet;

        public MarkerSet(org.dynmap.markers.MarkerSet markerSet) {
            this.markerSet = markerSet;
        }

        public Marker findMarker(String regionName) {
            return new Marker(markerSet.findMarker(regionName));
        }

        public void createMarker(String regionName, String infoText, boolean b, String world, int midX, int i, int midZ, MarkerIcon cityMarkerIcon, boolean c) {
            markerSet.createMarker(regionName, infoText, b, world, midX, i, midZ, cityMarkerIcon.markerIcon, c);
        }

        public void addAllowedMarkerIcon(MarkerIcon arg0) {
            markerSet.addAllowedMarkerIcon(arg0.markerIcon);
        }

        public AreaMarker createAreaMarker(String arg0, String arg1, boolean arg2, String arg3, double[] arg4, double[] arg5, boolean arg6) {
            return new AreaMarker(markerSet.createAreaMarker(arg0, arg1, arg2, arg3, arg4, arg5, arg6));
        }

        public CircleMarker createCircleMarker(String arg0, String arg1, boolean arg2, String arg3, double arg4, double arg5, double arg6, double arg7,
                double arg8, boolean arg9) {
            return new CircleMarker(markerSet.createCircleMarker(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
        }

        public Marker createMarker(String arg0, String arg1, String arg2, double arg3, double arg4, double arg5, MarkerIcon arg6, boolean arg7) {
            return new Marker(markerSet.createMarker(arg0, arg1, arg2, arg3, arg4, arg5, arg6.markerIcon, arg7));
        }

        public Marker createMarker(String arg0, String arg1, boolean arg2, String arg3, double arg4, double arg5, double arg6, MarkerIcon arg7, boolean arg8) {
            return new Marker(markerSet.createMarker(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7.markerIcon, arg8));
        }

        public PolyLineMarker createPolyLineMarker(String arg0, String arg1, boolean arg2, String arg3, double[] arg4, double[] arg5, double[] arg6,
                boolean arg7) {
            return new PolyLineMarker(markerSet.createPolyLineMarker(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7));
        }

        public void deleteMarkerSet() {
            markerSet.deleteMarkerSet();
        }

        public AreaMarker findAreaMarker(String arg0) {
            return new AreaMarker(markerSet.findAreaMarker(arg0));
        }

        public AreaMarker findAreaMarkerByLabel(String arg0) {
            return new AreaMarker(markerSet.findAreaMarkerByLabel(arg0));
        }

        public CircleMarker findCircleMarker(String arg0) {
            return new CircleMarker(markerSet.findCircleMarker(arg0));
        }

        public CircleMarker findCircleMarkerByLabel(String arg0) {
            return new CircleMarker(markerSet.findCircleMarkerByLabel(arg0));
        }

        public Marker findMarkerByLabel(String arg0) {
            return new Marker(markerSet.findMarkerByLabel(arg0));
        }

        public PolyLineMarker findPolyLineMarker(String arg0) {
            return new PolyLineMarker(markerSet.findPolyLineMarker(arg0));
        }

        public PolyLineMarker findPolyLineMarkerByLabel(String arg0) {
            return new PolyLineMarker(markerSet.findPolyLineMarkerByLabel(arg0));
        }

        public Set<MarkerIcon> getAllowedMarkerIcons() {
            Set<org.dynmap.markers.MarkerIcon> markerIcons = markerSet.getAllowedMarkerIcons();

            Set<MarkerIcon> hashSet = new HashSet<MarkerIcon>();
            for (org.dynmap.markers.MarkerIcon markerIcon : markerIcons) {
                hashSet.add(new MarkerIcon(markerIcon));
            }

            return hashSet;
        }

        public Set<AreaMarker> getAreaMarkers() {
            Set<org.dynmap.markers.AreaMarker> areaMarkers = markerSet.getAreaMarkers();

            Set<AreaMarker> hashSet = new HashSet<AreaMarker>();
            for (org.dynmap.markers.AreaMarker areaMarker : areaMarkers) {
                hashSet.add(new AreaMarker(areaMarker));
            }

            return hashSet;
        }

        public Set<CircleMarker> getCircleMarkers() {
            Set<org.dynmap.markers.CircleMarker> circleMarkers = markerSet.getCircleMarkers();

            Set<CircleMarker> hashSet = new HashSet<CircleMarker>();
            for (org.dynmap.markers.CircleMarker circleMarker : circleMarkers) {
                hashSet.add(new CircleMarker(circleMarker));
            }

            return hashSet;
        }

        public MarkerIcon getDefaultMarkerIcon() {
            return new MarkerIcon(markerSet.getDefaultMarkerIcon());
        }

        public boolean getHideByDefault() {
            return markerSet.getHideByDefault();
        }

        public Boolean getLabelShow() {
            return markerSet.getLabelShow();
        }

        public int getLayerPriority() {
            return markerSet.getLayerPriority();
        }

        public Set<MarkerIcon> getMarkerIconsInUse() {
            Set<org.dynmap.markers.MarkerIcon> markerIcons = markerSet.getMarkerIconsInUse();

            Set<MarkerIcon> hashSet = new HashSet<MarkerIcon>();
            for (org.dynmap.markers.MarkerIcon markerIcon : markerIcons) {
                hashSet.add(new MarkerIcon(markerIcon));
            }

            return hashSet;
        }

        public String getMarkerSetID() {
            return markerSet.getMarkerSetID();
        }

        public String getMarkerSetLabel() {
            return markerSet.getMarkerSetLabel();
        }

        public Set<Marker> getMarkers() {
            Set<org.dynmap.markers.Marker> markers = markerSet.getMarkers();

            Set<Marker> hashSet = new HashSet<Marker>();
            for (org.dynmap.markers.Marker marker : markers) {
                hashSet.add(new Marker(marker));
            }

            return hashSet;
        }

        public int getMinZoom() {
            return markerSet.getMinZoom();
        }

        public Set<PolyLineMarker> getPolyLineMarkers() {
            Set<org.dynmap.markers.PolyLineMarker> polyLineMarkers = markerSet.getPolyLineMarkers();

            Set<PolyLineMarker> hashSet = new HashSet<PolyLineMarker>();
            for (org.dynmap.markers.PolyLineMarker polyLineMarker : polyLineMarkers) {
                hashSet.add(new PolyLineMarker(polyLineMarker));
            }

            return hashSet;
        }

        public boolean isAllowedMarkerIcon(MarkerIcon arg0) {
            return markerSet.isAllowedMarkerIcon(arg0.markerIcon);
        }

        public boolean isMarkerSetPersistent() {
            return markerSet.isMarkerSetPersistent();
        }

        public void removeAllowedMarkerIcon(MarkerIcon arg0) {
            markerSet.removeAllowedMarkerIcon(arg0.markerIcon);
        }

        public void setDefaultMarkerIcon(MarkerIcon arg0) {
            markerSet.setDefaultMarkerIcon(arg0.markerIcon);
        }

        public void setHideByDefault(boolean arg0) {
            markerSet.setHideByDefault(arg0);
        }

        public void setLabelShow(Boolean arg0) {
            markerSet.setLabelShow(arg0);
        }

        public void setLayerPriority(int arg0) {
            markerSet.setLayerPriority(arg0);
        }

        public void setMarkerSetLabel(String arg0) {
            markerSet.setMarkerSetLabel(arg0);
        }

        public void setMinZoom(int arg0) {
            markerSet.setMinZoom(arg0);
        }
    }

    public static class PlayerSet {
        private org.dynmap.markers.PlayerSet playerSet;

        public PlayerSet(org.dynmap.markers.PlayerSet playerSet) {
            this.playerSet = playerSet;
        }

        public void addPlayer(String arg0) {
            playerSet.addPlayer(arg0);
        }

        public void deleteSet() {
            playerSet.deleteSet();
        }

        public Set<String> getPlayers() {
            return playerSet.getPlayers();
        }

        public String getSetID() {
            return playerSet.getSetID();
        }

        public boolean isPersistentSet() {
            return playerSet.isPersistentSet();
        }

        public boolean isPlayerInSet(String arg0) {
            return playerSet.isPlayerInSet(arg0);
        }

        public boolean isSymmetricSet() {
            return playerSet.isSymmetricSet();
        }

        public void removePlayer(String arg0) {
            playerSet.removePlayer(arg0);
        }

        public void setPlayers(Set<String> arg0) {
            playerSet.setPlayers(arg0);
        }

        public void setSymmetricSet(boolean arg0) {
            playerSet.setSymmetricSet(arg0);
        }
    }

    public static class AreaMarker extends GenericMarker {
        public AreaMarker(org.dynmap.markers.AreaMarker areaMarker) {
            super(areaMarker);
        }

        public void deleteCorner(int arg0) {
            ((org.dynmap.markers.AreaMarker) genericMarker).deleteCorner(arg0);
        }

        public double getBottomY() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getBottomY();
        }

        public int getCornerCount() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getCornerCount();
        }

        public double getCornerX(int arg0) {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getCornerX(arg0);
        }

        public double getCornerZ(int arg0) {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getCornerZ(arg0);
        }

        public String getDescription() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getDescription();
        }

        public int getFillColor() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getFillColor();
        }

        public double getFillOpacity() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getFillOpacity();
        }

        public String getLabel() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getLabel();
        }

        public int getLineColor() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getLineColor();
        }

        public double getLineOpacity() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getLineOpacity();
        }

        public int getLineWeight() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getLineWeight();
        }

        public double getTopY() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).getTopY();
        }

        public boolean isLabelMarkup() {
            return ((org.dynmap.markers.AreaMarker) genericMarker).isLabelMarkup();
        }

        public void setCornerLocation(int arg0, double arg1, double arg2) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setCornerLocation(arg0, arg1, arg2);

        }

        public void setCornerLocations(double[] arg0, double[] arg1) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setCornerLocations(arg0, arg1);
        }

        public void setDescription(String arg0) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setDescription(arg0);
        }

        public void setFillStyle(double arg0, int arg1) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setFillStyle(arg0, arg1);
        }

        public void setLabel(String arg0) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setLabel(arg0);
        }

        public void setLabel(String arg0, boolean arg1) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setLabel(arg0, arg1);
        }

        public void setLineStyle(int arg0, double arg1, int arg2) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setLineStyle(arg0, arg1, arg2);
        }

        public void setRangeY(double arg0, double arg1) {
            ((org.dynmap.markers.AreaMarker) genericMarker).setRangeY(arg0, arg1);
        }
    }

    public static class CircleMarker extends GenericMarker {
        public CircleMarker(org.dynmap.markers.CircleMarker circleMarker) {
            super(circleMarker);
        }

        public double getCenterX() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getCenterX();
        }

        public double getCenterY() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getCenterY();
        }

        public double getCenterZ() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getCenterZ();
        }

        public String getDescription() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getDescription();
        }

        public int getFillColor() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getFillColor();
        }

        public double getFillOpacity() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getFillOpacity();
        }

        public String getLabel() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getLabel();
        }

        public int getLineColor() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getLineColor();
        }

        public double getLineOpacity() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getLineOpacity();
        }

        public int getLineWeight() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getLineWeight();
        }

        public double getRadiusX() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getRadiusX();
        }

        public double getRadiusZ() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).getRadiusZ();
        }

        public boolean isLabelMarkup() {
            return ((org.dynmap.markers.CircleMarker) genericMarker).isLabelMarkup();
        }

        public void setCenter(String arg0, double arg1, double arg2, double arg3) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setCenter(arg0, arg1, arg2, arg3);
        }

        public void setDescription(String arg0) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setDescription(arg0);
        }

        public void setFillStyle(double arg0, int arg1) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setFillStyle(arg0, arg1);
        }

        public void setLabel(String arg0) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setLabel(arg0);
        }

        public void setLabel(String arg0, boolean arg1) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setLabel(arg0, arg1);
        }

        public void setLineStyle(int arg0, double arg1, int arg2) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setLineStyle(arg0, arg1, arg2);
        }

        public void setRadius(double arg0, double arg1) {
            ((org.dynmap.markers.CircleMarker) genericMarker).setRadius(arg0, arg1);
        }
    }

    public static class PolyLineMarker extends GenericMarker {
        public PolyLineMarker(org.dynmap.markers.PolyLineMarker polyLineMarker) {
            super(polyLineMarker);
        }

        public void deleteCorner(int arg0) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).deleteCorner(arg0);
        }

        public int getCornerCount() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getCornerCount();
        }

        public double getCornerX(int arg0) {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getCornerX(arg0);
        }

        public double getCornerY(int arg0) {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getCornerY(arg0);
        }

        public double getCornerZ(int arg0) {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getCornerZ(arg0);
        }

        public String getDescription() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getDescription();
        }

        public String getLabel() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getLabel();
        }

        public int getLineColor() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getLineColor();
        }

        public double getLineOpacity() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getLineOpacity();
        }

        public int getLineWeight() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).getLineWeight();
        }

        public boolean isLabelMarkup() {
            return ((org.dynmap.markers.PolyLineMarker) genericMarker).isLabelMarkup();
        }

        public void setCornerLocation(int arg0, double arg1, double arg2, double arg3) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setCornerLocation(arg0, arg1, arg2, arg3);
        }

        public void setCornerLocations(double[] arg0, double[] arg1, double[] arg2) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setCornerLocations(arg0, arg1, arg2);

        }

        public void setDescription(String arg0) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setDescription(arg0);
        }

        public void setLabel(String arg0) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setLabel(arg0);
        }

        public void setLabel(String arg0, boolean arg1) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setLabel(arg0);
        }

        public void setLineStyle(int arg0, double arg1, int arg2) {
            ((org.dynmap.markers.PolyLineMarker) genericMarker).setLineStyle(arg0, arg1, arg2);
        }
    }

    public static MarkerAPI getMarkerAPI() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return new MarkerAPI(dynmapAPI.getMarkerAPI());
        }
        return null;
    }

    public static void triggerRenderOfVolume(Location minLoc, Location maxLoc) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.triggerRenderOfVolume(minLoc, maxLoc);
        }
    }

    public static void assertPlayerInvisibility(String arg0, boolean arg1, String arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.assertPlayerInvisibility(arg0, arg1, arg2);
        }
    }

    public static void assertPlayerVisibility(String arg0, boolean arg1, String arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.assertPlayerVisibility(arg0, arg1, arg2);
        }
    }

    public static String getDynmapCoreVersion() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getDynmapCoreVersion();
        }
        return null;
    }

    public static boolean getPauseFullRadiusRenders() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getPauseFullRadiusRenders();
        }
        return false;
    }

    public static boolean getPauseUpdateRenders() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getPauseUpdateRenders();
        }
        return false;
    }

    public static boolean getPlayerVisbility(String arg0) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getPlayerVisbility(arg0);
        }
        return false;
    }

    public static boolean markerAPIInitialized() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.markerAPIInitialized();
        }
        return false;
    }

    public static void postPlayerJoinQuitToWeb(String arg0, String arg1, boolean arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.postPlayerJoinQuitToWeb(arg0, arg1, arg2);
        }
    }

    public static void postPlayerMessageToWeb(String arg0, String arg1, String arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.postPlayerMessageToWeb(arg0, arg1, arg2);
        }
    }

    public static boolean sendBroadcastToWeb(String arg0, String arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.sendBroadcastToWeb(arg0, arg1);
        }
        return false;
    }

    public static boolean setDisableChatToWebProcessing(boolean arg0) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.setDisableChatToWebProcessing(arg0);
        }
        return false;
    }

    public static void setPauseFullRadiusRenders(boolean arg0) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.setPauseFullRadiusRenders(arg0);
        }
    }

    public static void setPauseUpdateRenders(boolean arg0) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.setPauseUpdateRenders(arg0);
        }
    }

    public static void setPlayerVisiblity(String arg0, boolean arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.setPlayerVisiblity(arg0, arg1);
        }
    }

    public static boolean testIfPlayerInfoProtected() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.testIfPlayerInfoProtected();
        }
        return false;
    }

    public static boolean testIfPlayerVisibleToPlayer(String arg0, String arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.testIfPlayerVisibleToPlayer(arg0, arg1);
        }
        return false;
    }

    public static int triggerRenderOfBlock(String arg0, int arg1, int arg2, int arg3) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.triggerRenderOfBlock(arg0, arg1, arg2, arg3);
        }
        return 0;
    }

    public static int triggerRenderOfVolume(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.triggerRenderOfVolume(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        return 0;
    }

    public static void assertPlayerInvisibility(Player arg0, boolean arg1, Plugin arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.assertPlayerInvisibility(arg0, arg1, arg2);
        }
    }

    public static void assertPlayerVisibility(Player arg0, boolean arg1, Plugin arg2) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.assertPlayerVisibility(arg0, arg1, arg2);
        }
    }

    public static String getDynmapVersion() {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getDynmapVersion();
        }
        return null;
    }

    public static boolean getPlayerVisbility(Player arg0) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            return dynmapAPI.getPlayerVisbility(arg0);
        }
        return false;
    }

    public static void postPlayerJoinQuitToWeb(Player arg0, boolean arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.postPlayerJoinQuitToWeb(arg0, arg1);
        }
    }

    public static void postPlayerMessageToWeb(Player arg0, String arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.postPlayerMessageToWeb(arg0, arg1);
        }
    }

    public static void setPlayerVisiblity(Player arg0, boolean arg1) {
        if (dynmapAPI == null) {
            loadPlugin();
        }

        if (dynmapAPI != null) {
            dynmapAPI.setPlayerVisiblity(arg0, arg1);
        }
    }
}
