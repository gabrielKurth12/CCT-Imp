package br.com.mmcs.cct.imp.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
// Tabela -> M0020_TRANSHIPMENT
public class Transhipment {

    private MAPort port; // PORT_FK
    private String travelNumber; // TRAVEL_NUMBER
    private Date etd; // DATE_OUT

}
