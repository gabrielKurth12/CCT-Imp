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
// Tabela -> M0111_ACTIVITY
public class Activity {

    private String name; // NAME
    private String description; // DESCRIPTION

}
