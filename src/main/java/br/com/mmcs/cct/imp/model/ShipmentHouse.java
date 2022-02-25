package br.com.mmcs.cct.imp.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
@Setter
@Builder
// Tabela -> M0020_SHIPMENT_HOUSE
// TODO verificar se os campos do objeto Shipment se encontram na tabela M0020 ou em outra tabela especifica
public class ShipmentHouse { 

    private Long id;
    private String shipmentNumber; //SHIPMENT_NUMBER
    private ContactGeneral shipper; // SHIPPER_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Address.ZipCode, Address.StreetName, Address.CityName, Address.StateName, Address.Country.Code, Address.Country.Name, Address.State.Name, Address.State.Abbreviation, FaxPhone, User.name, User.Position, User.Phone, User.FaxPhone
    private ContactGeneral carrier; // CARRIER_CONTACT_GENERAL_FK | Campos utilizados -> CodNumeric
    private MAPort origin; // ORIGIN_MAPORT_FK | Campos utilizados -> Code, Name, Country.Code
    private String bruteWeight; // BRUTE_WEIGHT
    private Integer volumeQuantity; //VOLUME_QUANTITY
    private String masterNumber; // MASTER_NUMBER
    private MAPort destination; // DESTINY_MAPORT_FK | Campos utilizados -> Code, Name 
    private ContactGeneral notify; // NOTIFY_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Activity.Name, Activity.Description, Address.ZipCode, Address.StreetName, Address.CityName, Address.StateName, Address.Country.Code, Address.Country.Name, Address.State.Name, Address.State.Abbreviation, User.name, User.Position, User.Phone, User.FaxPhone
    private String weightToCharge; // WEIGHT_TO_CHARGE
    private Currency shipmentCurrency; // SHIPMENT_CURRENCY_FK | Campos utilizados -> Symbol
    private ShipmentContainer shipmentContainer; // HOUSE_FK | Campos utilizados -> ContainerNumber, VolumeType.Code, VolumeType.Description, SealOne
    private List<Transhipment> transhipments; // HOUSE_FK | Campos utilizados -> TravelNumber, ETD, Port.Code, Port.Name
    private DateTime forecastOutput; // FORECASTS_OUTPUT
    private DateTime forecastArrival; // FORECASTS_ARRIVAL
    private String travelNumber; // TRAVEL_NUMBER
    private ContactGeneral agent; // AGENT_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Address.StateName, Address.State, Address.State.Abbreviation, User.name, User.Position, User.Phone, User.FaxPhone
    private ContactGeneral consignee; // CNEE_CONTACT_GENERAL_FK | Campos utilizados -> CommercialName, ID, Address.StateName, Address.State, Address.State.Abbreviation, User.name, User.Position, User.Phone, User.FaxPhone
    private ContactGeneral customBroker; // CUSTOM_BROKER_CG_FK | Campos utilizados -> CommercialName
    private String commodityDescription; // COMMODITY_DESCRIPTION
    private DateTime dateIssueHouse; // DATE_ISSUE_HOUSE
    private List<QuotationVolume> volumes; // HOUSE_FK | Campos utilizados -> VolumeType.Code, VolumeType.Comments, VolumeType.Description, TotalWeight, Volume, Quantity, TotalWeight, UnitWeight, Widht, Lenght, Height
    private List<QuotationVolume> volumesExit; // WTF
    private Quotation quotationHouse; // QUOTATION_FK | Campos utilizados -> ChargeableWeight

}
