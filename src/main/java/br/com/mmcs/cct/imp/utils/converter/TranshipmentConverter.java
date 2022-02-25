package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.Transhipment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public final class TranshipmentConverter {

    public static List<Transhipment> convert(final List<Object[]> values) {
        List<Transhipment> transhipments = new ArrayList<>();

        for (Object[] transhipment : values) {
            transhipments.add(Transhipment.builder()
                    .travelNumber(ConverterUtils.convertToString(transhipment[0]))
                    .etd(ConverterUtils.convertToString(transhipment[1]))
                    .port(MaportConverter.convert(transhipment[2], transhipment[3]))
                    .build());
        }
        return transhipments;
    }

}
