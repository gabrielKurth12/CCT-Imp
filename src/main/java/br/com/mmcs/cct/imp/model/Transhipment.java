package br.com.mmcs.cct.imp.model;

import java.util.Date;
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
// Tabela -> M0020_TRANSHIPMENT
public class Transhipment {

    private MAPort port; // PORT_FK
    private String travelNumber; // TRAVEL_NUMBER
    private String etd; // DATE_OUT

}
