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
// Tabela -> M0110_VOLUME_TYPE
public class VolumeType {

    private String code; // CODE
    private String description; // DESCRIPTION
    private String comments; // COMMENTS

}
