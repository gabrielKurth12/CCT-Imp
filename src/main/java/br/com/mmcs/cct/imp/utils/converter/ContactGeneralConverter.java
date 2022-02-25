package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.Activity;
import br.com.mmcs.cct.imp.model.ContactGeneral;

/**
 *
 * @author Gabriel Rosa
 */
public class ContactGeneralConverter {

    public static ContactGeneral convert(final Object... value) {
        return ContactGeneral.builder()
                .id(value[0] != null ? Long.valueOf(ConverterUtils.convertToString(value[0])) : null)
                .commercialName(ConverterUtils.convertToString(value[1]))
                .faxPhone(ConverterUtils.convertToString(value[2]))
                .address(AddressConverter.convert(value[3], value[4], value[5], value[6], value[7],
                        value[8], value[9], value[10]))
                .user(UserConverter.convertUser(value[11], value[12], value[13]))
                .activity(value.length > 14 ? Activity.builder().name(ConverterUtils.convertToString(value[14])).description(ConverterUtils.convertToString(value[15])).build() : null)
                .build();
    }

}
