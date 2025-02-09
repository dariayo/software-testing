package com.domen.model;

import com.domen.enums.EngineStatus;
import com.domen.enums.SpaceEvent;
import com.domen.mechanics.EjectionMechanism;

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
