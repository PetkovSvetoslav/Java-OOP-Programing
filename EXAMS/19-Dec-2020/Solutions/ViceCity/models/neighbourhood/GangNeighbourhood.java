package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        Collection<Gun> mainPlayerGuns = mainPlayer.getGunRepository().getModels();

        for (Gun gun : mainPlayerGuns) {
            for (Player citizen : civilPlayers) {
                while (citizen.isAlive() && gun.canFire()) {
                    int fire = gun.fire();
                    citizen.takeLifePoints(fire);
                }
                if (!gun.canFire()) {
                    break;
                }
            }
        }

        for (Player citizen : civilPlayers) {
            if (citizen.isAlive()) {
                Collection<Gun> citizenGuns = citizen.getGunRepository().getModels();
                for (Gun gun : citizenGuns) {
                    while (gun.canFire() && mainPlayer.isAlive()) {
                        int fire = gun.fire();
                        mainPlayer.takeLifePoints(fire);
                    }
                    if (!mainPlayer.isAlive()) {
                        return;
                    }
                }
            }
        }
    }
}
