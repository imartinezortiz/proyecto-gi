package ucm.fdi.tfg.storage.business.entity;

import ucm.fdi.tfg.events.DomainEvent;

public final class DomainEvents {

	public static final DomainEvent<StorageObject> OBJECT_DELETED = new DomainEvent<StorageObject>();
	
	private DomainEvents() {
		// Avoid instantiation
	}
}
