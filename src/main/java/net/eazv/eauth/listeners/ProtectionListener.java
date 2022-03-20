package net.eazv.eauth.listeners;

import net.eazv.eauth.AuthPlugin;
import net.eazv.eauth.utils.LocationUtil;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.configuration.file.FileConfiguration;

public class ProtectionListener implements Listener {

    private final FileConfiguration config = AuthPlugin.getInstance().getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        player.setFoodLevel(20);
        player.setHealth(player.getHealth());
        player.getActivePotionEffects().clear();
        player.getInventory().clear();
        player.setFireTicks(0);

        event.setJoinMessage(null);

        teleportToSpawn(player);

        if (!config.getBoolean("OPTIONS.HIDE_PLAYERS")) {
            return;
        }
        
        Bukkit.getOnlinePlayers().stream().map(online -> {
            player.hidePlayer(online);
            return online;
        }).forEachOrdered(online -> {
            online.hidePlayer(player);
        });

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickItem(PlayerPickupItemEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            event.getItem().remove();
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() != null) {
            teleportToSpawn(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.hasBlock()) {
            return;
        }

        if (event.getClickedBlock() != null && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if (!config.getBoolean("OPTIONS.CANCEL_CHAT")) {
            return;
        }

        if (!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if ((event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ())) {
            event.getPlayer().teleport(event.getFrom());
        }
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {

        if (!(LocationUtil.parseToLocation(config.getString("LOCATION.SPAWN")) == null || config.getString("LOCATION.SPAWN") == null)) {
            String[] data = config.getString("LOCATION.SPAWN").split(", ");

            World world = Bukkit.getServer().getWorld(data[5]);

            world.setGameRuleValue("doDaylightCycle", "false");
            world.setTime(6000);
            world.setStorm(false);
            world.setWeatherDuration(0);
            world.setAnimalSpawnLimit(0);
            world.setAmbientSpawnLimit(0);
            world.setMonsterSpawnLimit(0);
            world.setWaterAnimalSpawnLimit(0);
        }

    }

    private void teleportToSpawn(Player player) {
        if (LocationUtil.parseToLocation(config.getString("LOCATION.SPAWN")) == null || config.getString("LOCATION.SPAWN") == null) {
            return;
        }

        player.teleport(LocationUtil.parseToLocation(config.getString("LOCATION.SPAWN")));
    }

}
