package br.com.mmcs.cct.imp.dao;

import java.util.List;
import br.com.mmcs.cct.imp.model.ShipmentHouse;

/**
 *
 * @author Gabriel Rosa
 */
public interface ShipmentHouseDao {

    List<Object[]> findShipmentsByShipmentModal(final String shipmentModal);

    ShipmentHouse findShipmentById(final Long id);

}
