package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.Country;
import br.com.mmcs.cct.imp.model.MAPort;

/**
 *
 * @author Gabriel Rosa
 */
public final class MaportConverter {

    public static MAPort convert(final Object... value) {
        return MAPort.builder()
                .code(ConverterUtils.convertToString(value[0]))
                .name(ConverterUtils.convertToString(value[1]))
                .country(value.length > 2 ? Country.builder().code(ConverterUtils.convertToString(value[2])).build() : null)
                .build();
    }

}
