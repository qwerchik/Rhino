package qwerchik.rhino;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
            if (block.getTypeId() != 0x07) block.breakNaturally();
        }
    }

    @EventHandler
    public void rhinoSprinting(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            player.setWalkSpeed(0.5f);
        }
    }
}
