package net.nonexplosivepvp.handlers;

import net.nonexplosivepvp.NonExplosivePvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class BedHandler implements Listener {
    private final NonExplosivePvP plugin;

    public BedHandler(NonExplosivePvP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnBlockInteract(PlayerInteractEvent event) {
        if (!plugin.getConfig().getBoolean("disable_bed_explosion")) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null) {
            return;
        }

        World world = block.getWorld();

        List<String> bed_usage_whitelist_worlds = this.plugin.getConfig().getStringList("bed_usage_whitelist_worlds");

        for (String world_name : bed_usage_whitelist_worlds) {
            if (world.getName().equals(world_name)) {
                return;
            }
        }

        if (block.getBlockData() instanceof Bed && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();

            if (!player.isSneaking()) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.DARK_RED + "> " + ChatColor.RED + "You can't use " + ChatColor.DARK_RED + "Beds " + ChatColor.RED + "here"); // conf
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_TELEPORT, 1f, 0.5f);
            }
        }
    }
}
