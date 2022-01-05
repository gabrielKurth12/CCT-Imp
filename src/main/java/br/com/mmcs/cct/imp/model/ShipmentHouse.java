package br.com.mmcs.cct.imp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Gabriel Rosa
 */
@Getter
public class ShipmentHouse {

    private Long id;
    private String shipmentNumber;
    private ContactGeneral shipper;
    private ContactGeneral carrier;
    private MAPort origin;
    private BigDecimal bruteWeight;
    private Integer volumeQuantity;
    private String masterNumber;
    private MAPort destination;
    private ContactGeneral notify;
    private BigDecimal weightToCharge;
    private Currency shipmentCurrency;
    private List<ShipmentContainer> shipmentContainers;
    private List<Transhipment> transhipments;
    private Date forecastOutput;
    private Date forecastArrival;
    private String travelNumber;
    private ContactGeneral agent;
    private ContactGeneral consignee;
    private ContactGeneral customBroker;
    private String commodityDescription;
    private Date dateIssueHouse;
    private List<QuotationVolume> volumes;
    private List<QuotationVolume> volumesExit;
    private Quotation quotationHouse;

}
