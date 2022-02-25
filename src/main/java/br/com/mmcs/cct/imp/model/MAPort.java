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
// Tabela -> M0105_MAPORT
public class MAPort {

    private String name; // NAME_PORT
    private String code; // CODE
    private Country country; // COUNTRY_FK

}
