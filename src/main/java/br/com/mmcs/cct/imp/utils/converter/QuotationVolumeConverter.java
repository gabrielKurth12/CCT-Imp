package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.QuotationVolume;
import br.com.mmcs.cct.imp.model.VolumeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public final class QuotationVolumeConverter {

    public static List<QuotationVolume> convert(final List<Object[]> values) {
        List<QuotationVolume> quotations = new ArrayList<>();

        for (Object[] quotation : values) {
            quotations.add(QuotationVolume.builder()
                    .volumeType(VolumeType.builder()
                            .code(ConverterUtils.convertToString(quotation[0]))
                            .comments(ConverterUtils.convertToString(quotation[1]))
                            .description(ConverterUtils.convertToString(quotation[2]))
                            .build())
                    .totalWeight(ConverterUtils.convertToString(quotation[3]))
                    .volume(ConverterUtils.convertToString(quotation[4]))
                    .quantity(new Integer(ConverterUtils.convertToString(quotation[5])))
                    .unitWeight(ConverterUtils.convertToString(quotation[6]))
                    .width(ConverterUtils.convertToString(quotation[7]))
                    .height(ConverterUtils.convertToString(quotation[8]))
                    .length(ConverterUtils.convertToString(quotation[9]))
                    .build());
        }
        return quotations;
    }

}
