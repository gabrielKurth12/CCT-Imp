package br.com.mmcs.cct.imp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
@Builder
// Tabela -> M0130_CONTACT_GENERAL
public class ContactGeneral {

    private Long id;
    private User user; // USER_FK
    private String commercialName; // COMMERCIAL_NAME
    private Address address; // ADDRESS_FK
    private String faxPhone; // FAXPHONE
    private Activity activity; // ACTIVITY_FK
    private String codNumeric; // COD_NUMERIC

}
