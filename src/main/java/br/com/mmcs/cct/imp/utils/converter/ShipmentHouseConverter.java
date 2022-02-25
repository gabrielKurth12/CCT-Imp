package br.com.mmcs.cct.imp.utils.converter;

import br.com.mmcs.cct.imp.model.ContactGeneral;
import br.com.mmcs.cct.imp.model.Currency;
import br.com.mmcs.cct.imp.model.Quotation;
import br.com.mmcs.cct.imp.model.ShipmentContainer;
import br.com.mmcs.cct.imp.model.ShipmentHouse;
import br.com.mmcs.cct.imp.model.VolumeType;
import org.joda.time.DateTime;

/**
 *
 * @author Gabriel Rosa
 */
public final class ShipmentHouseConverter {

    public static ShipmentHouse convert(final Object[] value) {
        ShipmentHouse shipmentHouse = ShipmentHouse.builder()
                .id(Long.valueOf(value[0].toString()))
                .shipmentNumber(value[1].toString())
                .shipper(ContactGeneralConverter.convert(value[2], value[3], value[4], value[5], value[6], value[7], value[8],
                        value[9], value[10], value[11], value[12], value[13], value[14], value[15]))
                .carrier(ContactGeneral.builder().codNumeric(ConverterUtils.convertToString(value[16])).build())
                .origin(MaportConverter.convert(value[17], value[18], value[19]))
                .bruteWeight(ConverterUtils.convertToString(value[20]))
                .volumeQuantity(new Integer(ConverterUtils.convertToString(value[21])))
                .masterNumber(ConverterUtils.convertToString(value[22]))
                .destination(MaportConverter.convert(value[23], value[24]))
                .notify(ContactGeneralConverter.convert(value[25], value[26], value[27], value[28], value[29], value[30], value[31], value[32], value[33],
                        value[34], value[35], value[36], value[37], value[38], value[39], value[40]))
                .weightToCharge(ConverterUtils.convertToString(value[41]))
                .shipmentCurrency(Currency.builder().symbol(ConverterUtils.convertToString(value[42])).build())
                .shipmentContainer(ShipmentContainer.builder()
                        .containerNumber(ConverterUtils.convertToString(value[43]))
                        .volumeType(VolumeType.builder()
                                .code(ConverterUtils.convertToString(value[44]))
                                .description(ConverterUtils.convertToString(value[45]))
                                .build())
                        .sealOne(ConverterUtils.convertToString(value[46]))
                        .build())
                .forecastOutput(new DateTime(value[47]))
                .forecastArrival(new DateTime(value[48]))
                .travelNumber(ConverterUtils.convertToString(value[49]))
                .agent(ContactGeneralConverter.convert(value[50], value[51], value[52], value[53], value[54], value[55], value[56], value[57],
                        value[58], value[59], value[60], value[61], value[62], value[63]))
                .consignee(ContactGeneralConverter.convert(value[64], value[65], value[66], value[67], value[68], value[69], value[70], value[71],
                        value[72], value[73], value[74], value[75], value[76], value[77]))
                .customBroker(ContactGeneral.builder().commercialName(ConverterUtils.convertToString(value[78])).build())
                .commodityDescription(ConverterUtils.convertToString(value[79]))
                .dateIssueHouse(new DateTime(value[80]))
                .quotationHouse(Quotation.builder().chargeableWeight(ConverterUtils.convertToString(value[81])).build())
                .build();

        return shipmentHouse;
    }

}
