package br.com.mmcs.cct.imp.model;

import java.util.Date;
import lombok.Getter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
public class Transhipment {

    private MAPort port;
    private String travelNumber;
    private Date etd;

}
