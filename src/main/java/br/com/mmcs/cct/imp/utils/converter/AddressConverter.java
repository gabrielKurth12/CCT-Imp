package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.Address;
import br.com.mmcs.cct.imp.model.Country;
import br.com.mmcs.cct.imp.model.State;

/**
 *
 * @author Gabriel Rosa
 */
public final class AddressConverter {

    public static Address convert(final Object... value) {
        return Address.builder()
                .zipcode(ConverterUtils.convertToString(value[0]))
                .streetName(ConverterUtils.convertToString(value[1]))
                .cityName(ConverterUtils.convertToString(value[2]))
                .stateName(ConverterUtils.convertToString(value[3]))
                .state(State.builder()
                        .name(ConverterUtils.convertToString(value[4]))
                        .abbreviation(ConverterUtils.convertToString(value[5]))
                        .build())
                .country(Country.builder()
                        .name(ConverterUtils.convertToString(value[6]))
                        .code(ConverterUtils.convertToString(value[7]))
                        .build())
                .build();
    }

}
