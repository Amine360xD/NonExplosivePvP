package net.nonexplosivepvp.handlers;

import net.nonexplosivepvp.NonExplosivePvP;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RespawnAnchorHandler implements Listener {
    private final NonExplosivePvP plugin;

    public RespawnAnchorHandler(NonExplosivePvP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnBlockInteract(PlayerInteractEvent event) {
        if (!plugin.getConfig().getBoolean("disable_respawn_anchor_explosion")) {
            return;
        }

        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (block == null || item == null) {
            return;
        }

        World world = block.getWorld();

        List<String> respawn_anchor_usage_whitelist_worlds = this.plugin.getConfig().getStringList("respawn_anchor_usage_whitelist_worlds");

        for (String world_name : respawn_anchor_usage_whitelist_worlds) {
            if (world.getName().equals(world_name)) {
                return;
            }
        }

        if (block.getType().equals(Material.RESPAWN_ANCHOR) && item.getType().equals(Material.GLOWSTONE) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();

            if (!player.isSneaking()) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.DARK_RED + "> " + ChatColor.RED + "You can't use " + ChatColor.DARK_RED + "Respawn Anchors " + ChatColor.RED + "here"); // conf
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_TELEPORT, 1f, 0.5f);
            }
        }
    }
}
