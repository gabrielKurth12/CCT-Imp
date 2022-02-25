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
// Tabela -> M0020_SHIPMENT_CONTAINER
public class ShipmentContainer {

    private String containerNumber; // CONTAINER_NUMBER
    private String sealOne; // SEAL_ONE
    private VolumeType volumeType; // VOLUME_TYPE_FK

}
