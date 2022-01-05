package br.com.mmcs.cct.imp.model;

import java.math.BigDecimal;
import lombok.Getter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
public class QuotationVolume {

    private Integer quantity;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal volume;
    private BigDecimal unitWeight;
    private BigDecimal totalWeight;
    private VolumeType volumeType;

}
