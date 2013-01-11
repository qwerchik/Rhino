package qwerchik.rhino;

import org.bukkit.entity.Player;
import java.util.Iterator;
import java.util.Vector;

public class RhinoVector {

    public Vector players = new Vector();
    public int totalPlayers = 0;

    public Player getRhino(Player player) {
        Iterator itr = players.iterator();
        Player rhinoPlayer = null;
        Player temp = null;
        while(itr.hasNext()) {
            temp = (Player) itr.next();
            if(temp.equals(player)) {
                rhinoPlayer = temp;
                break;
            }
        }
        return rhinoPlayer;
    }

    @SuppressWarnings("unchecked")
    public boolean addRhino(Player player) {
        if (getRhino(player) == null) {
            players.addElement(player);
            totalPlayers++;
            return true;
        } else return false;
    }

    public boolean removeRhino(Player player) {
        if (getRhino(player) != null) {
            players.removeElement(player);
            totalPlayers--;
            return true;
        } else return false;
    }

}
