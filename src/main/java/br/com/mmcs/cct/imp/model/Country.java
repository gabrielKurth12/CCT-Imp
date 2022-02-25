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
// Tabela -> M0001_COUNTRY
public class Country {

    private String code; // CODE
    private String name; // NAME_COUNTRY

}
