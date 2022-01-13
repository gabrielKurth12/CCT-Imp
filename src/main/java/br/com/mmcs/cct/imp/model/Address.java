package br.com.mmcs.cct.imp.model;

import br.com.mmcs.cct.imp.model.enumeration.AddressTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
// Tabela -> M0001_ADDRESS
public class Address {

    private String zipcode; // ZIPCODE
    private AddressTypeEnum addressType; // ADDRESS_TYPE
    private String streetName; // STREET_NAME
    private String cityName; // CITY_NAME
    private String stateName; // STATE_NAME
    private City city; // CITY_FK
    private Country country; // COUNTRY_FK
    private State state; // Transient?? WTF

}
