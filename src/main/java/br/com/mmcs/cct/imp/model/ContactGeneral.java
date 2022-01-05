package br.com.mmcs.cct.imp.model;

import lombok.Getter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
public class ContactGeneral {

    private Long id;
    private User user = new User();
    private String commercialName;
    private Address address = new Address();
    private String faxPhone;
    private Activity activity;
    private String codNumeric;

}
