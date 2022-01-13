package br.com.mmcs.cct.imp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
// Tabela -> M0020_SHIPMENT_HOUSE
// TODO verificar se os campos do objeto Shipment se encontram na tabela M0020 ou em outra tabela especifica
public class ShipmentHouse { 

    private Long id;
    private String shipmentNumber; //SHIPMENT_NUMBER
    private ContactGeneral shipper; // SHIPPER_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Address, User, FaxPhone
    private ContactGeneral carrier; // CARRIER_CONTACT_GENERAL_FK | Campos utilizados -> CodNumeric
    private MAPort origin; // ORIGIN_MAPORT_FK | Campos utilizados -> Code, Name, Country
    private BigDecimal bruteWeight; // BRUTE_WEIGHT
    private Integer volumeQuantity; //VOLUME_QUANTITY
    private String masterNumber; // MASTER_NUMBER
    private MAPort destination; // DESTINY_MAPORT_FK | Campos utilizados -> Code, Name, 
    private ContactGeneral notify; // NOTIFY_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Activity, Address, User, FaxPhone
    private BigDecimal weightToCharge; // WEIGHT_TO_CHARGE
    private Currency shipmentCurrency; // SHIPMENT_CURRENCY_FK | Campos utilizados -> Symbol
    private List<ShipmentContainer> shipmentContainers; // MASTER_FK | Campos utilizados -> ContainerNumber, VolumeType, SealOne
    private List<Transhipment> transhipments; // HOUSE_FK | Campos utilizados -> TravelNumber, ETD, Port
    private Date forecastOutput; // FORECASTS_OUTPUT
    private Date forecastArrival; // FORECASTS_ARRIVAL
    private String travelNumber; // TRAVEL_NUMBER
    private ContactGeneral agent; // AGENT_CONTACT_GENERAL_FK | Campos utilizados -> Address, ID
    private ContactGeneral consignee; // CNEE_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Address
    private ContactGeneral customBroker; // CUSTOM_BROKER_CG_FK | Campos utilizados -> CommercialName
    private String commodityDescription; // COMMODITY_DESCRIPTION
    private Date dateIssueHouse; // DATE_ISSUE_HOUSE
    private List<QuotationVolume> volumes; // HOUSE_FK | Campos utilizados -> VolumeType, UnitWeight, Quantity, TotalWeight, Widht, Lenght, Height
    private List<QuotationVolume> volumesExit; // WTF
    private Quotation quotationHouse; // QUOTATION_FK | Campos utilizados -> ChargeableWeight

}
