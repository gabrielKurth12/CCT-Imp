package br.com.mmcs.cct.imp.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
// Tabela -> M0205_QUOTATION_VOLUME
public class QuotationVolume {

    private Integer quantity; // VOL_QUANTITY
    private BigDecimal length; // VOL_LENGTH
    private BigDecimal width; // VOL_WIDTH
    private BigDecimal height; // VOL_HEIGHT
    private BigDecimal volume; // VOL_VOLUME
    private BigDecimal unitWeight; // UNIT_WEIGHT
    private BigDecimal totalWeight; // TOTAL_WEIGHT
    private VolumeType volumeType; // VOLUME_TYPE_FK

}
