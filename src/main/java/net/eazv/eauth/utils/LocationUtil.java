package net.eazv.eauth.utils;

import lombok.experimental.UtilityClass;

import org.bukkit.Bukkit;
import org.bukkit.Location;

@UtilityClass
public class LocationUtil {

    public String parseToString(org.bukkit.Location location) {
        if (location == null) {
            return null;
        }

        return location.getX() + ", "
                + location.getY() + ", "
                + location.getZ() + ", "
                + location.getYaw() + ", "
                + location.getPitch() + ", "
                + location.getWorld().getName();
    }

    public Location parseToLocation(String string) {
        if (string == null) {
            return null;
        }
        
        String[] data = string.split(", ");
        try {
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            double z = Double.parseDouble(data[2]);
            float pitch = Float.valueOf(data[4]);
            float yaw = Float.valueOf(data[3]);
            org.bukkit.World world = Bukkit.getWorld(data[5]);

            Location location = new Location(world, x, y, z, yaw, pitch);

            return location;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
