package net.nonexplosivepvp.handlers;

import net.nonexplosivepvp.NonExplosivePvP;
import org.bukkit.*;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EndCrystalHandler implements Listener {
    private final NonExplosivePvP plugin;

    public EndCrystalHandler(NonExplosivePvP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnEntityDamage(EntityDamageByEntityEvent event) {
        if (!plugin.getConfig().getBoolean("disable_end_crystal_explosion")) {
            return;
        }

        Entity damager = event.getDamager();

        if (damager instanceof EnderCrystal) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnEntityExplode(EntityExplodeEvent event) {
        if (!plugin.getConfig().getBoolean("disable_end_crystal_explosion")) {
            return;
        }

        Entity entity = event.getEntity();

        if (entity instanceof EnderCrystal) {
            event.blockList().clear();
        }
    }
}
