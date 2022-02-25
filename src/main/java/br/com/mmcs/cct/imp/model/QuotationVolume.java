package br.com.mmcs.cct.imp.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
@Builder
// Tabela -> M0205_QUOTATION_VOLUME
public class QuotationVolume {

    private Integer quantity; // VOL_QUANTITY
    private String length; // VOL_LENGTH
    private String width; // VOL_WIDTH
    private String height; // VOL_HEIGHT
    private String volume; // VOL_VOLUME
    private String unitWeight; // UNIT_WEIGHT
    private String totalWeight; // TOTAL_WEIGHT
    private VolumeType volumeType; // VOLUME_TYPE_FK

}
