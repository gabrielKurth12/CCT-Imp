package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.User;

/**
 *
 * @author Gabriel Rosa
 */
public final class UserConverter {

    public static User convertUser(final Object... value) {
        return User.builder()
                .name(ConverterUtils.convertToString(value[0]))
                .phone(ConverterUtils.convertToString(value[1]))
                .position(ConverterUtils.convertToString(value[2]))
                .build();
    }

}
