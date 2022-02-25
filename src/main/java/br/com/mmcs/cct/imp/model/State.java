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
// Tabela -> M0001_STATE
public class State {

    private String abbreviation; // ABBREVIATION
    private String name; // NAME_STATE

}
