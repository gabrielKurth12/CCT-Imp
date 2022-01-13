package br.com.mmcs.cct.imp.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
// Tabela -> M0130_CONTACT_GENERAL
public class ContactGeneral {

    private Long id;
    private User user = new User(); // USER_FK
    private String commercialName; // COMMERCIAL_NAME
    private Address address = new Address(); // ADDRESS_FK
    private String faxPhone; // FAXPHONE
    private Activity activity; // ACTIVITY_FK
    private String codNumeric; // COD_NUMERIC

}
