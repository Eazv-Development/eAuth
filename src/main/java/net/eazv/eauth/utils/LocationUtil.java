package net.eazv.eauth.utils;

import lombok.experimental.UtilityClass;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@UtilityClass
public class LocationUtil {

    public String parseToString(Location location) {
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

    public Location parseToLocation(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String[] data = input.split(",");

        if (data.length != 6) {
            Bukkit.getLogger().warning("[eAuth] Invalid location format: " + input);
            return null;
        }

        try {
            double x = Double.parseDouble(data[0].trim());
            double y = Double.parseDouble(data[1].trim());
            double z = Double.parseDouble(data[2].trim());
            float yaw = Float.parseFloat(data[3].trim());
            float pitch = Float.parseFloat(data[4].trim());
            World world = Bukkit.getWorld(data[5].trim());

            if (world == null) {
                Bukkit.getLogger().warning("[eAuth] World not found: " + data[5].trim());
                return null;
            }

            return new Location(world, x, y, z, yaw, pitch);
        } catch (NumberFormatException ex) {
            Bukkit.getLogger().severe("[eAuth] Failed to parse location: " + input);
            return null;
        }
    }

}
