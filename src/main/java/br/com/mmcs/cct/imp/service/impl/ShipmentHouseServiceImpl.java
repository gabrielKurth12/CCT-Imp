package br.com.mmcs.cct.imp.service.impl;

import br.com.mmcs.cct.imp.dao.ShipmentHouseDao;
import br.com.mmcs.cct.imp.service.ShipmentHouseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gabriel Rosa
 */
public class ShipmentHouseServiceImpl implements ShipmentHouseService {

    @Autowired
    private ShipmentHouseDao dao;

    @Override
    public Object[] findfrtValueAndCurrency(String shipmentModal) {
        return dao.findfrtValueAndCurrency(shipmentModal);
    }

}
