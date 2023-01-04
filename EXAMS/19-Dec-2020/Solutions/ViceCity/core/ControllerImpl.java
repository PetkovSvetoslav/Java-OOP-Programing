package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private Player mainPlayer;
    private Map<String, Player> playersByName;
    private Deque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.playersByName = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        this.playersByName.put(name, new CivilPlayer(name));

        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }
        this.guns.offer(gun);

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.guns.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }

        Gun gun;
        if (name.equals("Vercetti")) {
            gun = guns.poll();
            this.mainPlayer.getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        } else if (this.playersByName.containsKey(name)) {
            gun = this.guns.poll();
            playersByName.get(name).getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
        }

        return CIVIL_PLAYER_DOES_NOT_EXIST;
    }

    @Override
    public String fight() {
        neighbourhood.action(this.mainPlayer, this.playersByName.values());

        String out;
        if (allPlayersAreFine()) {
            out = FIGHT_HOT_HAPPENED;
        } else {
            out = FIGHT_HAPPENED +
                    System.lineSeparator() +
                    String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()) +
                    System.lineSeparator() +
                    String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, getDeadPlayersCount()) +
                    System.lineSeparator() +
                    String.format(CIVIL_PLAYERS_LEFT_MESSAGE, getAlivePlayersCount());
        }

        return out;
    }

    private int getDeadPlayersCount() {
        return this.playersByName.values()
                .stream()
                .filter(p -> !p.isAlive())
                .mapToInt(p -> 1)
                .sum();
    }

    public int getAlivePlayersCount() {
        return this.playersByName.size() - this.getDeadPlayersCount();
    }

    private boolean allPlayersAreFine() {
        return this.mainPlayer.getLifePoints() == 100
                &&
                this.playersByName.values()
                        .stream()
                        .allMatch(p -> p.getLifePoints() == 50);
    }
}
