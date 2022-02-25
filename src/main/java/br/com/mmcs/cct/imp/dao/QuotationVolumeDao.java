package br.com.mmcs.cct.imp.dao;

import br.com.mmcs.cct.imp.model.QuotationVolume;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public interface QuotationVolumeDao {

    List<QuotationVolume> findQuotationVolumeByShipmentHouseId(final Long shipmentHouseId);

}
