package net.nonexplosivepvp;

import net.nonexplosivepvp.handlers.BedHandler;
import net.nonexplosivepvp.handlers.EndCrystalHandler;
import net.nonexplosivepvp.handlers.RespawnAnchorHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class NonExplosivePvP extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new RespawnAnchorHandler(this);
        new BedHandler(this);
        new EndCrystalHandler(this);
    }
}
