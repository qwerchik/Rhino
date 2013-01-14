package qwerchik.rhino;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RListener implements Listener {

    private Rhino plugin;

    public Rhino getPlugin() {
        return plugin;
    }

    public RListener(Rhino instance) {
        plugin = instance;
    }

    @EventHandler
    public void blockSniping(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.LEFT_CLICK_BLOCK ||
            event.getAction() == Action.LEFT_CLICK_AIR) &&
            getPlugin().vector.getRhino(player) != null)
        {
            Block block = player.getTargetBlock(null, 100);
            Location origin = block.getLocation();
            origin.setY(origin.getY() - 1);
            origin.setX(origin.getX() - 1);
            origin.setZ(origin.getZ() - 1);
            int origin_y = origin.getBlockY();
            int origin_x = origin.getBlockX();
            int origin_z = origin.getBlockZ();
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        if (getPlugin().notOre(
                                player.getWorld().getBlockAt(
                                origin_x + x,
                                origin_y + y,
                                origin_z + z).getTypeId())) {
                            player.getWorld().getBlockAt(
                                    origin_x + x,
                                    origin_y + y,
                                    origin_z + z).breakNaturally();
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void rhinoQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (getPlugin().vector.getRhino(player) != null) {
            getPlugin().vector.removeRhino(player);
        }
    }

    @EventHandler
    public void rhinoDeath(PlayerDeathEvent event) {
            Player player = (Player) event.getEntity();
            if (getPlugin().vector.getRhino(player) != null) {
                player.getWorld().strikeLightning(player.getLocation());
                player.getWorld().strikeLightning(player.getLocation());
                player.getWorld().strikeLightning(player.getLocation());
                getPlugin().vector.removeRhino(player);
            }
    }
}
