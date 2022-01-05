package br.com.mmcs.cct.imp.model;

import br.com.mmcs.cct.imp.model.enumeration.AddressTypeEnum;
import lombok.Getter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
public class Address {

    private String zipcode;
    private AddressTypeEnum addressType;
    private String streetName;
    private String cityName;
    private String stateName;
    private City city;
    private Country country;
    private State state;

}
