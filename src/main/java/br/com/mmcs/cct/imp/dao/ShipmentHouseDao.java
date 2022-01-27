package br.com.mmcs.cct.imp.dao;

import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public interface ShipmentHouseDao {

    Object[] findfrtValueAndCurrency(final Long houseId);

    List<Object[]> findShipmentsByShipmentModal(final String shipmentModal);

}
