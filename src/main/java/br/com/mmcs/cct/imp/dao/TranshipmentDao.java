package br.com.mmcs.cct.imp.dao;

import br.com.mmcs.cct.imp.model.Transhipment;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public interface TranshipmentDao {

    List<Transhipment> findByShipmentHouseId(final Long shipmentHouseId);

}
