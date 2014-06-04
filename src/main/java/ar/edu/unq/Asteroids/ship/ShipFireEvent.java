package ar.edu.unq.Asteroids.ship;

import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.physic.Physics;

public class ShipFireEvent extends FiredEvent {

	private final Physics shipPhysic;

	public ShipFireEvent(final Physics shipPhysic) {
		this.shipPhysic = shipPhysic;
	}

	public Physics getShipPhysic() {
		return this.shipPhysic;
	}

}
