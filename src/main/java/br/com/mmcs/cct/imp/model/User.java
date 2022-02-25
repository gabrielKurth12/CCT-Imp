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
// Tabela -> M0003_USER
public class User {

    private String name; // NAME_USER
    private String phone; // PHONE
    private String position; // POSITION

}
