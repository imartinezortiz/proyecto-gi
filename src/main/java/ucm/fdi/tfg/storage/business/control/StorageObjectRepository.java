package ucm.fdi.tfg.storage.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import ucm.fdi.tfg.storage.business.entity.StorageObject;
import ucm.fdi.tfg.storage.business.entity.StorageObjectId;

public interface StorageObjectRepository extends JpaRepository<StorageObject, StorageObjectId> {

}
