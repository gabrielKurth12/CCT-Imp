package br.com.mmcs.cct.imp.dao;

/**
 *
 * @author Gabriel Rosa
 */
public interface ShipmentRateDao {

    Object[] findFirstValueAndCurrencyByShipmentHouseId(final Long houseId);

}
