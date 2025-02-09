package src.main.java.com.domen.model;

import src.main.java.com.domen.mechanics.EjectionMechanism;
import src.main.java.com.domen.enums.*;

public class Engine {
    public EngineStatus status;
    public EjectionMechanism ejectionMechanism;

    public Engine() {
        this.status = EngineStatus.OFF;
        this.ejectionMechanism = new EjectionMechanism();
    }

    public void activate(Space space, Person... persons) {
        this.status = EngineStatus.ACTIVATING;
        triggerEvent(SpaceEvent.ENGINE_START);
        triggerEvent(SpaceEvent.AIR_RUSH);
        this.status = EngineStatus.ON;
        triggerEvent(SpaceEvent.CONFETTI_LIKE_EJECTION);

        ejectionMechanism.ejectPersons(space, persons);
    }

    private void triggerEvent(SpaceEvent event) {
        System.out.println(event.getDescription());
    }

    public EngineStatus getStatus() {
        return status;
    }
}
