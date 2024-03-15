package net.eazv.eauth.utils.scoreboard;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Assemble {

    private final JavaPlugin plugin;

    private AssembleAdapter adapter;
    private AssembleThread thread;
    private AssembleListener listeners;
    private AssembleStyle assembleStyle = AssembleStyle.MODERN;

    private Map<UUID, AssembleBoard> boards;

    private long ticks = 2;
    private boolean hook = false, debugMode = true, callEvents = true;

    private final ChatColor[] chatColorCache = ChatColor.values();

    /**
     * Assemble.
     *
     * @param plugin instance.
     * @param adapter that is being provided.
     */
    public Assemble(JavaPlugin plugin, AssembleAdapter adapter) {
        if (plugin == null) {
            throw new RuntimeException("Assemble can not be instantiated without a plugin instance!");
        }

        this.plugin = plugin;
        this.adapter = adapter;
        this.boards = new ConcurrentHashMap<>();

        this.setup();
    }

    /**
     * Setup Assemble.
     */
    public void setup() {
        // Register Events.
        this.listeners = new AssembleListener(this);
        this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

        // Ensure that the thread has stopped running.
        if (this.thread != null) {
            this.thread.interrupt();
            this.thread = null;
        }

        // Register new boards for existing online players.
        for (Player player : this.getPlugin().getServer().getOnlinePlayers()) {
            getBoards().putIfAbsent(player.getUniqueId(), new AssembleBoard(player, this));
        }

        // Start Thread.
        this.thread = new AssembleThread(this);
    }
}
