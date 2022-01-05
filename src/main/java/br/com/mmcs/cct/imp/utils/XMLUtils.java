package br.com.mmcs.cct.imp.utils;

import br.com.mmcs.cct.imp.model.Address;
import br.com.mmcs.cct.imp.model.ContactGeneral;
import br.com.mmcs.cct.imp.model.ShipmentContainer;
import br.com.mmcs.cct.imp.model.ShipmentHouse;
import br.com.mmcs.cct.imp.model.User;
import br.com.mmcs.cct.imp.service.ShipmentHouseService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gabriel Rosa
 */
public class XMLUtils {

    private static String BREAK_ROW = "\n";

    private static String XML_PATTERN = "[^"
            + "\u0009\r\n"
            + "\u0020-\uD7FF"
            + "\uE000-\uFFFD"
            + "\ud800\udc00-\udbff\udfff"
            + "]";

    @Autowired
    private ShipmentHouseService houseService;

    private Document fillCCTDocument(ShipmentHouse entity) {
        String description = null;
        DecimalFormat dFormat = new DecimalFormat("###0.000");
        String nameCG = "";
        if (entity.getCommodityDescription() != null) {
            description = entity.getCommodityDescription();
            description = description.replaceAll("\n", " ");
            description = description.replaceAll(XML_PATTERN, " ");
            description = description.replaceAll("&", "E");
            description = description.replaceAll("#", " ");
            description = description.replaceAll(">", " ");
            description = description.replaceAll("â€œ", " ");
            description = description.replaceAll("`", " ");
            description = description.replaceAll("\"", " ");
        }
        //Alguns campos precisam de tratamento pra retirar quebra de linha de string, ou concatenar dados, tudo é feito no começo do método
        //Importante: As linhas que possuem um comentario "//#?#" sao linhas que não tenho certeza dos dados preenchidos, e precisa confirmar com marcelo.
        Element houseWaybill = new Element("HouseWaybill", "rsm", "iata:housewaybill:1");

        //Setar Namespaces
        Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        houseWaybill.addNamespaceDeclaration(Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema"));
        houseWaybill.addNamespaceDeclaration(Namespace.getNamespace("ccts", "urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2"));
        houseWaybill.addNamespaceDeclaration(Namespace.getNamespace("udt", "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:8"));
        houseWaybill.addNamespaceDeclaration(Namespace.getNamespace("ram", "iata:datamodel:3"));
        houseWaybill.addNamespaceDeclaration(XSI);
        houseWaybill.setAttribute("schemaLocation", "iata:housewaybill:1 HouseWaybill_1.xsd", XSI);

        //Definir inclusaoPagamento como ROOT.
        Document documentCCT = new Document(houseWaybill);
        //Criar os elementos principais do XML
        Element messageHeaderDocument = new Element("MessageHeaderDocument", "rsm", "iata:housewaybill:1");
        Element businessHeaderDocument = new Element("BusinessHeaderDocument", "rsm", "iata:housewaybill:1");
        Element masterConsignment = new Element("MasterConsignment", "rsm", "iata:housewaybill:1");

        //Itens do MessageHeaderDocument
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(fillIDElementCCTXML("BRCUSTOMS"));
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(fillNameElementCCTXML("HouseWaybill"));
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "294"));
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(createNewElementToXml("IssueDateTime", "ram", "iata:datamodel:3", new DateTime().toString()));
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(createNewElementToXml("PurposeCode", "ram", "iata:datamodel:3", "Response"));
        messageHeaderDocument.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(createNewElementToXml("VersionID", "ram", "iata:datamodel:3", "3.0"));
        messageHeaderDocument.addContent(BREAK_ROW);
//        messageHeaderDocument.addContent(createNewElementToXml("ConversationID", "ram", "iata:datamodel:3", " "));//#?#
//        messageHeaderDocument.addContent(BREAK_ROW);

        //1 elemento
        Element senderParty = new Element("SenderParty", "ram", "iata:datamodel:3");
        senderParty.addContent(BREAK_ROW);
        senderParty.addContent(fillPrimaryIdElementCCTXML("C", "BRCUSTOMS"));
        senderParty.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(senderParty);
        messageHeaderDocument.addContent(BREAK_ROW);
        //Fim 1 elemento

        Element recipientParty = new Element("RecipientParty", "ram", "iata:datamodel:3");
        recipientParty.addContent(BREAK_ROW);
        recipientParty.addContent(fillPrimaryIdElementCCTXML("O", "NOT IDENTIFIED"));
        recipientParty.addContent(BREAK_ROW);
        messageHeaderDocument.addContent(recipientParty);
        messageHeaderDocument.addContent(BREAK_ROW);

        //Itens do BusinessHeaderDocument
        businessHeaderDocument.addContent(BREAK_ROW);
        businessHeaderDocument.addContent(fillIDElementCCTXML("NOT IDENTIFIED"));
        businessHeaderDocument.addContent(BREAK_ROW);

//        Element includedHeaderNote = new Element("IncludedHeaderNote", "ram", "iata:datamodel:3");
//        includedHeaderNote.addContent(BREAK_ROW);
//        includedHeaderNote.addContent(createNewElementToXml("ContentCode", "ram", "iata:datamodel:3", "XXXXXXXXX"));
//        includedHeaderNote.addContent(BREAK_ROW);
//        includedHeaderNote.addContent(createNewElementToXml("Content", "ram", "iata:datamodel:3", "XXXXXXXXX"));
//        includedHeaderNote.addContent(BREAK_ROW);
//        businessHeaderDocument.addContent(includedHeaderNote);
//        businessHeaderDocument.addContent(BREAK_ROW);\
        if (entity.getShipper() != null) {
            nameCG = entity.getShipper().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            Element signatoryConsignorAuthentication = new Element("SignatoryConsignorAuthentication", "ram", "iata:datamodel:3");
            signatoryConsignorAuthentication.addContent(BREAK_ROW);
            signatoryConsignorAuthentication.addContent(createNewElementToXml("Signatory", "ram", "iata:datamodel:3", nameCG));
            signatoryConsignorAuthentication.addContent(BREAK_ROW);
            businessHeaderDocument.addContent(signatoryConsignorAuthentication);
            businessHeaderDocument.addContent(BREAK_ROW);
        }

        Element signatoryCarrierAuthentication = new Element("SignatoryCarrierAuthentication", "ram", "iata:datamodel:3");
        signatoryCarrierAuthentication.addContent(BREAK_ROW);
        signatoryCarrierAuthentication.addContent(createNewElementToXml("ActualDateTime", "ram", "iata:datamodel:3", new DateTime().toString()));
        signatoryCarrierAuthentication.addContent(BREAK_ROW);
        signatoryCarrierAuthentication.addContent(createNewElementToXml("Signatory", "ram", "iata:datamodel:3", entity.getCarrier() == null ? "" : entity.getCarrier().getCodNumeric()));
        signatoryCarrierAuthentication.addContent(BREAK_ROW);

        Element issueAuthenticationLocation = new Element("IssueAuthenticationLocation", "ram", "iata:datamodel:3");
        issueAuthenticationLocation.addContent(BREAK_ROW);
        issueAuthenticationLocation.addContent(fillNameElementCCTXML(entity.getOrigin() == null ? "" : entity.getOrigin().getCode()));
        issueAuthenticationLocation.addContent(BREAK_ROW);
        signatoryCarrierAuthentication.addContent(issueAuthenticationLocation);
        signatoryCarrierAuthentication.addContent(BREAK_ROW);
        businessHeaderDocument.addContent(signatoryCarrierAuthentication);
        businessHeaderDocument.addContent(BREAK_ROW);

        //Itens do MasterConsignment
        masterConsignment.addContent(BREAK_ROW);
        Element includedTareGrossWeightMeasure = new Element("IncludedTareGrossWeightMeasure", "ram", "iata:datamodel:3");
        includedTareGrossWeightMeasure.setAttribute("unitCode", "KGM");
        includedTareGrossWeightMeasure.addContent(entity == null ? "0.000" : entity.getBruteWeight() == null ? "0.000" : dFormat.format(entity.getBruteWeight()).toString());//#?#
        masterConsignment.addContent(includedTareGrossWeightMeasure);
        masterConsignment.addContent(BREAK_ROW);

        masterConsignment.addContent(createNewElementToXml("TotalPieceQuantity", "ram", "iata:datamodel:3", entity == null ? "0.000" : entity.getVolumeQuantity() == null ? "0.000" : dFormat.format(entity.getVolumeQuantity()).toString()));
        masterConsignment.addContent(BREAK_ROW);

        Element transportContractDocument = new Element("TransportContractDocument", "ram", "iata:datamodel:3");
        transportContractDocument.addContent(BREAK_ROW);
        transportContractDocument.addContent(fillIDElementCCTXML((entity.getCarrier() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric()) + "-" + entity.getMasterNumber()));//#?#
        transportContractDocument.addContent(BREAK_ROW);
        masterConsignment.addContent(transportContractDocument);
        masterConsignment.addContent(BREAK_ROW);
        if (entity.getOrigin() != null) {
            Element originLocation = new Element("OriginLocation", "ram", "iata:datamodel:3");
            originLocation.addContent(BREAK_ROW);
            originLocation.addContent(fillIDElementCCTXML(entity == null ? "" : entity.getOrigin() == null ? "" : entity.getOrigin().getCode()));
            originLocation.addContent(BREAK_ROW);
            originLocation.addContent(fillNameElementCCTXML(entity == null ? "" : entity.getOrigin() == null ? "" : entity.getOrigin().getName()));
            originLocation.addContent(BREAK_ROW);
            masterConsignment.addContent(originLocation);
            masterConsignment.addContent(BREAK_ROW);
        }
        if (entity.getDestination() != null) {
            Element finalDestinationLocation = new Element("FinalDestinationLocation", "ram", "iata:datamodel:3");
            finalDestinationLocation.addContent(BREAK_ROW);
            finalDestinationLocation.addContent(fillIDElementCCTXML(entity == null ? "" : entity.getDestination() == null ? "" : entity.getDestination().getCode()));
            finalDestinationLocation.addContent(BREAK_ROW);
            finalDestinationLocation.addContent(fillNameElementCCTXML(entity == null ? "" : entity.getDestination() == null ? "" : entity.getDestination().getName()));
            finalDestinationLocation.addContent(BREAK_ROW);
            masterConsignment.addContent(finalDestinationLocation);
            masterConsignment.addContent(BREAK_ROW);
        }

        Element includedHouseConsignment = new Element("IncludedHouseConsignment", "ram", "iata:datamodel:3");
        includedHouseConsignment.addContent(BREAK_ROW);

        includedHouseConsignment.addContent(fillIDElementCCTXML(entity.getShipmentNumber()));
        includedHouseConsignment.addContent(BREAK_ROW);

//        includedHouseConsignment.addContent(createNewElementToXml("AdditionalID", "ram", "iata:datamodel:3", "XXXXXXXXXXXXXX"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
        if (entity.getNotify() != null) {
            nameCG = entity.getNotify().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            includedHouseConsignment.addContent(createNewElementToXml("AssociatedReferenceID", "ram", "iata:datamodel:3", nameCG));
            includedHouseConsignment.addContent(BREAK_ROW);
        }

//        includedHouseConsignment.addContent(createNewElementToXml("NilCarriageValueIndicator", "ram", "iata:datamodel:3", "true"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
//        Element declaredValueForCarriageAmount = new Element("DeclaredValueForCarriageAmount", "ram", "iata:datamodel:3");
//        declaredValueForCarriageAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        declaredValueForCarriageAmount.addContent((entity.getDeclarationValue() == null ? "" : dFormat.format(entity.getDeclarationValue()).toString()));
//        includedHouseConsignment.addContent(declaredValueForCarriageAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        includedHouseConsignment.addContent(createNewElementToXml("NilCustomsValueIndicator", "ram", "iata:datamodel:3", "true"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element declaredValueForCustomsAmount = new Element("DeclaredValueForCustomsAmount", "ram", "iata:datamodel:3");
//        declaredValueForCustomsAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        declaredValueForCustomsAmount.addContent((entity.getDeclarationValue() == null ? "" : dFormat.format(entity.getDeclarationValue()).toString()));//#?#
//        includedHouseConsignment.addContent(declaredValueForCustomsAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        includedHouseConsignment.addContent(createNewElementToXml("NilInsuranceValueIndicator", "ram", "iata:datamodel:3", "true"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element insuranceValueAmount = new Element("InsuranceValueAmount", "ram", "iata:datamodel:3");
//        insuranceValueAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        insuranceValueAmount.addContent(entity.getTotalInsurance() == null ? "" : dFormat.format(entity.getTotalInsurance()).toString());
//        includedHouseConsignment.addContent(insuranceValueAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        includedHouseConsignment.addContent(createNewElementToXml("TotalChargePrepaidIndicator", "ram", "iata:datamodel:3", "true"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element weightTotalChargeAmount = new Element("WeightTotalChargeAmount", "ram", "iata:datamodel:3");
//        weightTotalChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        weightTotalChargeAmount.addContent(entity.getWeightToCharge() == null ? "" : dFormat.format(entity.getWeightToCharge()).toString());
//        includedHouseConsignment.addContent(weightTotalChargeAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element valuationTotalChargeAmount = new Element("ValuationTotalChargeAmount", "ram", "iata:datamodel:3");
//        valuationTotalChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        valuationTotalChargeAmount.addContent((entity.getDeclarationValue() == null ? "" : dFormat.format(entity.getDeclarationValue()).toString()));//#?#
//        includedHouseConsignment.addContent(valuationTotalChargeAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element taxTotalChargeAmount = new Element("TaxTotalChargeAmount", "ram", "iata:datamodel:3");
//        taxTotalChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        taxTotalChargeAmount.addContent((entity.getExternalFreight() == null ? "" : dFormat.format(entity.getExternalFreight()).toString()));//#?#
//        includedHouseConsignment.addContent(taxTotalChargeAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        includedHouseConsignment.addContent(createNewElementToXml("TotalDisbursementPrepaidIndicator", "ram", "iata:datamodel:3", "true"));//#?#
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element agentTotalDisbursementAmount = new Element("AgentTotalDisbursementAmount", "ram", "iata:datamodel:3");
//        agentTotalDisbursementAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        agentTotalDisbursementAmount.addContent((entity.getDeclarationValue() == null ? "" : dFormat.format(entity.getDeclarationValue()).toString()));//#?#
//        includedHouseConsignment.addContent(agentTotalDisbursementAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element carrierTotalDisbursementAmount = new Element("CarrierTotalDisbursementAmount", "ram", "iata:datamodel:3");
//        carrierTotalDisbursementAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        carrierTotalDisbursementAmount.addContent((entity.getDeclarationValue() == null ? "" : dFormat.format(entity.getDeclarationValue()).toString()));//#?#
//        includedHouseConsignment.addContent(carrierTotalDisbursementAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element totalPrepaidChargeAmount = new Element("TotalPrepaidChargeAmount", "ram", "iata:datamodel:3");
//        totalPrepaidChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        totalPrepaidChargeAmount.addContent(entity.getReport() == null ? "" : entity.getReport().getTotalPP() == null ? "0" : dFormat.format(entity.getReport().getTotalPP()).toString());//#?#
//        includedHouseConsignment.addContent(totalPrepaidChargeAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element totalCollectChargeAmount = new Element("TotalCollectChargeAmount", "ram", "iata:datamodel:3");
//        totalCollectChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
//        totalCollectChargeAmount.addContent(entity.getReport() == null ? "" : entity.getReport().getTotalCC() == null ? "0" : dFormat.format(entity.getReport().getTotalCC()).toString());//#?#
//        includedHouseConsignment.addContent(totalCollectChargeAmount);
//        includedHouseConsignment.addContent(BREAK_ROW);
        includedTareGrossWeightMeasure = new Element("IncludedTareGrossWeightMeasure", "ram", "iata:datamodel:3");
        includedTareGrossWeightMeasure.setAttribute("unitCode", "KGM");
        includedTareGrossWeightMeasure.addContent(entity.getBruteWeight() == null ? "0.000" : dFormat.format(entity.getBruteWeight()).toString());
        includedHouseConsignment.addContent(includedTareGrossWeightMeasure);
        includedHouseConsignment.addContent(BREAK_ROW);

        Element grossVolumeMeasure = new Element("GrossVolumeMeasure", "ram", "iata:datamodel:3");
        grossVolumeMeasure.setAttribute("unitCode", "MTQ");
        grossVolumeMeasure.addContent(entity.getVolumeQuantity() == null ? "0.000" : String.valueOf(dFormat.format(entity.getVolumeQuantity())));
        includedHouseConsignment.addContent(grossVolumeMeasure);
        includedHouseConsignment.addContent(BREAK_ROW);

        includedHouseConsignment.addContent(createNewElementToXml("ConsignmentItemQuantity", "ram", "iata:datamodel:3", dFormat.format(entity.getVolumeQuantity() == null ? new BigDecimal(0) : entity.getVolumeQuantity())));
        includedHouseConsignment.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(createNewElementToXml("PackageQuantity", "ram", "iata:datamodel:3", dFormat.format(entity.getVolumeQuantity() == null ? new BigDecimal(0) : entity.getVolumeQuantity())));
        includedHouseConsignment.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(createNewElementToXml("TotalPieceQuantity", "ram", "iata:datamodel:3", String.valueOf(entity.getVolumeQuantity())));
        includedHouseConsignment.addContent(BREAK_ROW);
        if (description != null) {
            includedHouseConsignment.addContent(createNewElementToXml("SummaryDescription", "ram", "iata:datamodel:3", description != null ? description : ""));
            includedHouseConsignment.addContent(BREAK_ROW);
        }
//        includedHouseConsignment.addContent(createNewElementToXml("FreightRateTypeCode", "ram", "iata:datamodel:3", " "));
//        includedHouseConsignment.addContent(BREAK_ROW);
        if (entity.getShipper() != null) {
            nameCG = entity.getShipper().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            Element consignorParty = new Element("ConsignorParty", "ram", "iata:datamodel:3");
            consignorParty.addContent(BREAK_ROW);
            consignorParty.addContent(fillPrimaryIdElementCCTXML(entity.getShipper() == null ? "" : entity.getShipper().getId().toString()));
            consignorParty.addContent(BREAK_ROW);

//        consignorParty.addContent(createNewElementToXml("AdditionalID", "ram", "iata:datamodel:3", "XXXXXXXXXXX"));//#?#
//        consignorParty.addContent(BREAK_ROW);
            consignorParty.addContent(fillNameElementCCTXML(nameCG));
            consignorParty.addContent(BREAK_ROW);

//        consignorParty.addContent(createNewElementToXml("AccountID", "ram", "iata:datamodel:3", " "));
//        consignorParty.addContent(BREAK_ROW);   
            if (entity.getShipper().getAddress() != null) {
                Element postalStructuredAddress = fillPostalStructuredAddressCCTXML(entity.getShipper().getAddress());
                if (entity.getShipper().getAddress().getState() != null || entity.getShipper().getAddress().getStateName() != null) {
                    postalStructuredAddress.addContent(createNewElementToXml("CountrySubDivisionID", "ram", "iata:datamodel:3", entity.getShipper().getAddress().getStateName() != null ? entity.getShipper().getAddress().getStateName() : entity.getShipper().getAddress().getState() == null ? "" : entity.getShipper().getAddress().getState().getAbbreviation()));
                    postalStructuredAddress.addContent(BREAK_ROW);
                }
                consignorParty.addContent(postalStructuredAddress);
                consignorParty.addContent(BREAK_ROW);
            }

            Element definedTradeContact = fillDefinedTradeContactCCTXML(entity.getShipper());
            consignorParty.addContent(definedTradeContact);
            consignorParty.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(consignorParty);
            includedHouseConsignment.addContent(BREAK_ROW);
        }
        if (entity.getConsignee() != null) {
            nameCG = entity.getConsignee().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            Element consigneeParty = new Element("ConsigneeParty", "ram", "iata:datamodel:3");
            consigneeParty.addContent(BREAK_ROW);
            consigneeParty.addContent(fillPrimaryIdElementCCTXML(entity.getConsignee().getId().toString()));//#?#
            consigneeParty.addContent(BREAK_ROW);
//        consigneeParty.addContent(createNewElementToXml("AdditionalID", "ram", "iata:datamodel:3", "XXXXXXXXXXXXXX"));//#?#
//        consigneeParty.addContent(BREAK_ROW);
            consigneeParty.addContent(fillNameElementCCTXML(nameCG));
            consigneeParty.addContent(BREAK_ROW);
//            consigneeParty.addContent(createNewElementToXml("AccountID", "ram", "iata:datamodel:3", " "));//#?#
//            consigneeParty.addContent(BREAK_ROW);
            if (entity.getConsignee().getAddress() != null) {
                Element postalStructuredAddressConsignee = fillPostalStructuredAddressCCTXML(entity.getConsignee() == null ? null : entity.getConsignee().getAddress());
                if (entity.getConsignee().getAddress().getState() != null || entity.getConsignee().getAddress().getStateName() != null) {
                    postalStructuredAddressConsignee.addContent(createNewElementToXml("CountrySubDivisionID", "ram", "iata:datamodel:3", entity.getConsignee() == null ? null : entity.getConsignee().getAddress().getStateName() != null ? entity.getConsignee().getAddress().getStateName() : entity.getConsignee().getAddress().getState() == null ? "" : entity.getConsignee().getAddress().getState().getAbbreviation()));//#?#
                    postalStructuredAddressConsignee.addContent(BREAK_ROW);
                }
                consigneeParty.addContent(postalStructuredAddressConsignee);
                consigneeParty.addContent(BREAK_ROW);
            }

            Element definedTradeContactConsignee = fillDefinedTradeContactCCTXML(entity.getConsignee());
            consigneeParty.addContent(definedTradeContactConsignee);
            consigneeParty.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(consigneeParty);
            includedHouseConsignment.addContent(BREAK_ROW);
        }
        if (entity.getAgent() != null) {
            nameCG = entity.getAgent().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            Element freightForwarderParty = new Element("FreightForwarderParty", "ram", "iata:datamodel:3");
            freightForwarderParty.addContent(BREAK_ROW);
            freightForwarderParty.addContent(fillPrimaryIdElementCCTXML(entity.getAgent().getId().toString()));//#?#
            freightForwarderParty.addContent(BREAK_ROW);
//        freightForwarderParty.addContent(createNewElementToXml("AdditionalID", "ram", "iata:datamodel:3", "XXXXXXXXXXXXXX"));//#?#
//        freightForwarderParty.addContent(BREAK_ROW);
            freightForwarderParty.addContent(fillNameElementCCTXML(nameCG));
            freightForwarderParty.addContent(BREAK_ROW);
//        freightForwarderParty.addContent(createNewElementToXml("AccountID", "ram", "iata:datamodel:3", " "));//#?#
//        freightForwarderParty.addContent(BREAK_ROW);
            if (entity.getAgent().getAddress() != null) {
                Element postalStructuredAddressFreightForwarder = fillPostalStructuredAddressCCTXML(entity.getAgent() == null ? null : entity.getAgent().getAddress());
                if (entity.getAgent().getAddress().getState() != null || entity.getAgent().getAddress().getStateName() != null) {
                    postalStructuredAddressFreightForwarder.addContent(createNewElementToXml("CountrySubDivisionID", "ram", "iata:datamodel:3", entity.getAgent() == null ? null : entity.getAgent().getAddress().getStateName() != null ? entity.getAgent().getAddress().getStateName() : entity.getAgent().getAddress().getState() == null ? "" : entity.getAgent().getAddress().getState().getAbbreviation()));//#?#
                    postalStructuredAddressFreightForwarder.addContent(BREAK_ROW);
                }
                freightForwarderParty.addContent(postalStructuredAddressFreightForwarder);
                freightForwarderParty.addContent(BREAK_ROW);
            }

            Element definedTradeContactFreightForwarder = fillDefinedTradeContactCCTXML(entity.getAgent());
            freightForwarderParty.addContent(definedTradeContactFreightForwarder);
            freightForwarderParty.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(freightForwarderParty);
            includedHouseConsignment.addContent(BREAK_ROW);
        }
        if (entity.getNotify() != null) {
            nameCG = entity.getNotify().getCommercialName();
            nameCG = nameCG.replaceAll("\n", " ");
            nameCG = nameCG.replaceAll(XML_PATTERN, " ");
            nameCG = nameCG.replaceAll("&", "E");
            nameCG = nameCG.replaceAll("#", " ");
            nameCG = nameCG.replaceAll(">", " ");
            nameCG = nameCG.replaceAll("â€œ", " ");
            nameCG = nameCG.replaceAll("`", " ");
            nameCG = nameCG.replaceAll("\"", " ");
            Element associatedParty = new Element("AssociatedParty", "ram", "iata:datamodel:3");
            associatedParty.addContent(BREAK_ROW);
            associatedParty.addContent(fillPrimaryIdElementCCTXML(entity.getNotify().getId().toString()));//#?#
            associatedParty.addContent(BREAK_ROW);
//        associatedParty.addContent(createNewElementToXml("AdditionalID", "ram", "iata:datamodel:3", "XXXXXXXXXX"));//#?#
//        associatedParty.addContent(BREAK_ROW);
            associatedParty.addContent(fillNameElementCCTXML(nameCG));
            if (entity.getNotify().getActivity() != null) {
                if (entity.getNotify().getActivity().getName() != null) {
                    associatedParty.addContent(createNewElementToXml("RoleCode", "ram", "iata:datamodel:3", entity.getNotify() == null ? "" : entity.getNotify().getActivity() == null ? "" : entity.getNotify().getActivity().getName()));//#?#
                    associatedParty.addContent(BREAK_ROW);
                }
                if (entity.getNotify().getActivity().getDescription() != null) {
                    associatedParty.addContent(createNewElementToXml("Role", "ram", "iata:datamodel:3", entity.getNotify() == null ? "" : entity.getNotify().getActivity() == null ? "" : entity.getNotify().getActivity().getDescription()));//#?#
                    associatedParty.addContent(BREAK_ROW);
                }
            }
            if (entity.getNotify().getAddress() != null) {
                Element postalStructuredAddressAssociatedParty = fillPostalStructuredAddressCCTXML(entity.getNotify() == null ? null : entity.getNotify().getAddress());
                postalStructuredAddressAssociatedParty.addContent(createNewElementToXml("CountrySubDivisionID", "ram", "iata:datamodel:3", entity.getNotify() == null ? null : entity.getNotify().getAddress().getStateName() != null ? entity.getNotify().getAddress().getStateName() : entity.getNotify().getAddress().getState() == null ? "" : entity.getNotify().getAddress().getState().getAbbreviation()));
                postalStructuredAddressAssociatedParty.addContent(BREAK_ROW);
                associatedParty.addContent(postalStructuredAddressAssociatedParty);
                associatedParty.addContent(BREAK_ROW);
            }

//        Element specifiedAddressLocation = new Element("SpecifiedAddressLocation", "ram", "iata:datamodel:3");
//        specifiedAddressLocation.addContent(BREAK_ROW);
//        specifiedAddressLocation.addContent(fillIDElementCCTXML("XXXXXXXXX"));//#?#
//        specifiedAddressLocation.addContent(BREAK_ROW);
//        specifiedAddressLocation.addContent(fillNameElementCCTXML("XXXXXXXXXXX"));//#?#
//        specifiedAddressLocation.addContent(BREAK_ROW);
//        specifiedAddressLocation.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXXX"));//#?#
//        specifiedAddressLocation.addContent(BREAK_ROW);
//        postalStructuredAddressAssociatedParty.addContent(specifiedAddressLocation);
//        postalStructuredAddressAssociatedParty.addContent(BREAK_ROW);
            Element definedTradeContactAssociatedParty = fillDefinedTradeContactCCTXML(entity.getNotify());
            associatedParty.addContent(definedTradeContactAssociatedParty);
            associatedParty.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(associatedParty);
            includedHouseConsignment.addContent(BREAK_ROW);
        }

//        Element applicableTransportCargoInsurance = new Element("ApplicableTransportCargoInsurance", "ram", "iata:datamodel:3");
//        applicableTransportCargoInsurance.addContent(BREAK_ROW);
//
//        Element coverageInsuranceParty = new Element("CoverageInsuranceParty", "ram", "iata:datamodel:3");
//        coverageInsuranceParty.addContent(BREAK_ROW);
//
//        coverageInsuranceParty.addContent(createNewElementToXml("Role", "ram", "iata:datamodel:3", entity.getCarrier() == null ? "" : entity.getCarrier().getActivity() == null ? "" : entity.getCarrier().getActivity().getName()));//#?#
//        coverageInsuranceParty.addContent(BREAK_ROW);
//
//        applicableTransportCargoInsurance.addContent(coverageInsuranceParty);
//        applicableTransportCargoInsurance.addContent(BREAK_ROW);
//
//        includedHouseConsignment.addContent(applicableTransportCargoInsurance);
//        includedHouseConsignment.addContent(BREAK_ROW);
        if (entity.getOrigin() != null) {
            Element originLocation = new Element("OriginLocation", "ram", "iata:datamodel:3");
            originLocation.addContent(BREAK_ROW);
            originLocation.addContent(fillIDElementCCTXML(entity.getOrigin() == null ? "" : entity.getOrigin().getCode()));
            originLocation.addContent(BREAK_ROW);
            originLocation.addContent(fillNameElementCCTXML(entity.getOrigin() == null ? "" : entity.getOrigin().getName()));
            originLocation.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(originLocation);
            includedHouseConsignment.addContent(BREAK_ROW);
        }
        if (entity.getDestination() != null) {
            Element finalDestinationLocation = new Element("FinalDestinationLocation", "ram", "iata:datamodel:3");
            finalDestinationLocation.addContent(BREAK_ROW);
            finalDestinationLocation.addContent(fillIDElementCCTXML(entity.getDestination() == null ? "" : entity.getDestination().getCode()));
            finalDestinationLocation.addContent(BREAK_ROW);
            finalDestinationLocation.addContent(fillNameElementCCTXML(entity.getDestination() == null ? "" : entity.getDestination().getName()));
            finalDestinationLocation.addContent(BREAK_ROW);
            includedHouseConsignment.addContent(finalDestinationLocation);
            includedHouseConsignment.addContent(BREAK_ROW);
        }
        //PRIMEIRO TRANSHIPMENT FAZER NA MAE MESMO SE TIVER TRANSHIPMENT OU NAO 
        Element specifiedLogisticsTransportMovement = new Element("SpecifiedLogisticsTransportMovement", "ram", "iata:datamodel:3");
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
        specifiedLogisticsTransportMovement.addContent(createNewElementToXml("StageCode", "ram", "iata:datamodel:3", "Main-Carriage"));
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
//                specifiedLogisticsTransportMovement.addContent(createNewElementToXml("ModeCode", "ram", "iata:datamodel:3", "XXXXXXXX"));//#?#
//                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
        specifiedLogisticsTransportMovement.addContent(createNewElementToXml("Mode", "ram", "iata:datamodel:3", "AIR TRANSPORT"));
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
        specifiedLogisticsTransportMovement.addContent(fillIDElementCCTXML(entity.getTravelNumber()));
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
        specifiedLogisticsTransportMovement.addContent(createNewElementToXml("SequenceNumeric", "ram", "iata:datamodel:3", String.valueOf(1)));
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

        Element usedLogisticsTransportMeans = new Element("UsedLogisticsTransportMeans", "ram", "iata:datamodel:3");
        usedLogisticsTransportMeans.addContent(BREAK_ROW);
        usedLogisticsTransportMeans.addContent(fillNameElementCCTXML(entity.getCarrier() == null ? "" : entity.getCarrier().getCodNumeric()));//#?#
        usedLogisticsTransportMeans.addContent(BREAK_ROW);
        specifiedLogisticsTransportMovement.addContent(usedLogisticsTransportMeans);
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

        Element arrivalEvent = new Element("ArrivalEvent", "ram", "iata:datamodel:3");
        arrivalEvent.addContent(BREAK_ROW);
        arrivalEvent.addContent(createNewElementToXml("ScheduledOccurrenceDateTime", "ram", "iata:datamodel:3", new DateTime(entity.getForecastArrival()).toString()));
        arrivalEvent.addContent(BREAK_ROW);

        Element occurrenceArrivalLocation = new Element("OccurrenceArrivalLocation", "ram", "iata:datamodel:3");//#?#
        occurrenceArrivalLocation.addContent(BREAK_ROW);
        occurrenceArrivalLocation.addContent(fillIDElementCCTXML(entity.getDestination().getCode()));
        occurrenceArrivalLocation.addContent(BREAK_ROW);
        occurrenceArrivalLocation.addContent(fillNameElementCCTXML(entity.getDestination().getName()));
        occurrenceArrivalLocation.addContent(BREAK_ROW);
        occurrenceArrivalLocation.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "AIRPORT"));
        occurrenceArrivalLocation.addContent(BREAK_ROW);
        arrivalEvent.addContent(occurrenceArrivalLocation);
        arrivalEvent.addContent(BREAK_ROW);

        specifiedLogisticsTransportMovement.addContent(arrivalEvent);
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

        Element departureEvent = new Element("DepartureEvent", "ram", "iata:datamodel:3");
        departureEvent.addContent(BREAK_ROW);
        departureEvent.addContent(createNewElementToXml("ScheduledOccurrenceDateTime", "ram", "iata:datamodel:3", new DateTime(entity.getForecastOutput()).toString()));
        departureEvent.addContent(BREAK_ROW);

        Element occurrenceDepartureLocation = new Element("OccurrenceDepartureLocation", "ram", "iata:datamodel:3");
        occurrenceDepartureLocation.addContent(BREAK_ROW);
        occurrenceDepartureLocation.addContent(fillIDElementCCTXML(entity.getOrigin().getCode()));
        occurrenceDepartureLocation.addContent(BREAK_ROW);
        occurrenceDepartureLocation.addContent(fillNameElementCCTXML(entity.getOrigin().getName()));
        occurrenceDepartureLocation.addContent(BREAK_ROW);
        occurrenceDepartureLocation.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "AIRPORT"));
        occurrenceDepartureLocation.addContent(BREAK_ROW);

        departureEvent.addContent(occurrenceDepartureLocation);
        departureEvent.addContent(BREAK_ROW);

        specifiedLogisticsTransportMovement.addContent(departureEvent);
        specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

        includedHouseConsignment.addContent(specifiedLogisticsTransportMovement);
        includedHouseConsignment.addContent(BREAK_ROW);
        if (entity.getTranshipments() != null) {
            for (int i = 0; i < entity.getTranshipments().size(); i++) {
                specifiedLogisticsTransportMovement = new Element("SpecifiedLogisticsTransportMovement", "ram", "iata:datamodel:3");
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
                specifiedLogisticsTransportMovement.addContent(createNewElementToXml("StageCode", "ram", "iata:datamodel:3", "Main-Carriage"));
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
//                specifiedLogisticsTransportMovement.addContent(createNewElementToXml("ModeCode", "ram", "iata:datamodel:3", "XXXXXXXX"));//#?#
//                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
                specifiedLogisticsTransportMovement.addContent(createNewElementToXml("Mode", "ram", "iata:datamodel:3", "AIR TRANSPORT"));
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
                specifiedLogisticsTransportMovement.addContent(fillIDElementCCTXML(entity.getTranshipments().get(i).getTravelNumber()));
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);
                specifiedLogisticsTransportMovement.addContent(createNewElementToXml("SequenceNumeric", "ram", "iata:datamodel:3", String.valueOf(i + 2)));
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

                usedLogisticsTransportMeans = new Element("UsedLogisticsTransportMeans", "ram", "iata:datamodel:3");
                usedLogisticsTransportMeans.addContent(BREAK_ROW);
                usedLogisticsTransportMeans.addContent(fillNameElementCCTXML(entity.getCarrier() == null ? "" : entity.getCarrier().getCodNumeric()));//#?#
                usedLogisticsTransportMeans.addContent(BREAK_ROW);
                specifiedLogisticsTransportMovement.addContent(usedLogisticsTransportMeans);
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

                arrivalEvent = new Element("ArrivalEvent", "ram", "iata:datamodel:3");
                arrivalEvent.addContent(BREAK_ROW);
                arrivalEvent.addContent(createNewElementToXml("ScheduledOccurrenceDateTime", "ram", "iata:datamodel:3", entity.getTranshipments().get(i).getEtd() == null ? "" : new DateTime(entity.getTranshipments().get(i).getEtd()).toString()));
                arrivalEvent.addContent(BREAK_ROW);

                occurrenceArrivalLocation = new Element("OccurrenceArrivalLocation", "ram", "iata:datamodel:3");//#?#
                occurrenceArrivalLocation.addContent(BREAK_ROW);
                occurrenceArrivalLocation.addContent(fillIDElementCCTXML(entity.getTranshipments().get(i).getPort() == null ? "" : entity.getTranshipments().get(i).getPort().getCode()));
                occurrenceArrivalLocation.addContent(BREAK_ROW);
                occurrenceArrivalLocation.addContent(fillNameElementCCTXML(entity.getTranshipments().get(i).getPort() == null ? "" : entity.getTranshipments().get(i).getPort().getName()));
                occurrenceArrivalLocation.addContent(BREAK_ROW);
                occurrenceArrivalLocation.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "AIRPORT"));
                occurrenceArrivalLocation.addContent(BREAK_ROW);
                arrivalEvent.addContent(occurrenceArrivalLocation);
                arrivalEvent.addContent(BREAK_ROW);

                specifiedLogisticsTransportMovement.addContent(arrivalEvent);
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

                departureEvent = new Element("DepartureEvent", "ram", "iata:datamodel:3");
                departureEvent.addContent(BREAK_ROW);
                departureEvent.addContent(createNewElementToXml("ScheduledOccurrenceDateTime", "ram", "iata:datamodel:3", entity.getTranshipments().get(i).getEtd() == null ? "" : new DateTime(entity.getTranshipments().get(i).getEtd()).toString()));
                departureEvent.addContent(BREAK_ROW);

                occurrenceDepartureLocation = new Element("OccurrenceDepartureLocation", "ram", "iata:datamodel:3");
                occurrenceDepartureLocation.addContent(BREAK_ROW);
                occurrenceDepartureLocation.addContent(fillIDElementCCTXML(entity.getTranshipments().get(i).getPort() == null ? "" : entity.getTranshipments().get(i).getPort().getCode()));
                occurrenceDepartureLocation.addContent(BREAK_ROW);
                occurrenceDepartureLocation.addContent(fillNameElementCCTXML(entity.getTranshipments().get(i).getPort() == null ? "" : entity.getTranshipments().get(i).getPort().getName()));
                occurrenceDepartureLocation.addContent(BREAK_ROW);
                occurrenceDepartureLocation.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "AIRPORT"));
                occurrenceDepartureLocation.addContent(BREAK_ROW);

                departureEvent.addContent(occurrenceDepartureLocation);
                departureEvent.addContent(BREAK_ROW);

                specifiedLogisticsTransportMovement.addContent(departureEvent);
                specifiedLogisticsTransportMovement.addContent(BREAK_ROW);

                includedHouseConsignment.addContent(specifiedLogisticsTransportMovement);
                includedHouseConsignment.addContent(BREAK_ROW);
            }

        }

        ShipmentContainer sc = null;
        if (entity.getShipmentContainers() != null && !entity.getShipmentContainers().isEmpty()) {
            sc = entity.getShipmentContainers().get(0);
            Element utilizedLogisticsTransportEquipment = new Element("UtilizedLogisticsTransportEquipment", "ram", "iata:datamodel:3");
            utilizedLogisticsTransportEquipment.addContent(BREAK_ROW);
            if (sc.getContainerNumber() != null) {
                utilizedLogisticsTransportEquipment.addContent(fillIDElementCCTXML(sc == null ? "" : sc.getContainerNumber()));
                utilizedLogisticsTransportEquipment.addContent(BREAK_ROW);
            }
            if (sc.getVolumeType() != null) {
                if (sc.getVolumeType().getCode() != null) {
                    utilizedLogisticsTransportEquipment.addContent(createNewElementToXml("CharacteristicCode", "ram", "iata:datamodel:3", sc == null ? "" : sc.getVolumeType() == null ? "" : sc.getVolumeType().getCode()));//#?#
                    utilizedLogisticsTransportEquipment.addContent(BREAK_ROW);
                }
                if (sc.getVolumeType().getComments() != null) {
                    utilizedLogisticsTransportEquipment.addContent(createNewElementToXml("Characteristic", "ram", "iata:datamodel:3", sc == null ? "" : sc.getVolumeType() == null ? "" : sc.getVolumeType().getDescription()));//#?#
                    utilizedLogisticsTransportEquipment.addContent(BREAK_ROW);
                }
            }
            if (sc.getSealOne() != null) {
                Element affixedLogisticsSeal = new Element("AffixedLogisticsSeal", "ram", "iata:datamodel:3");
                affixedLogisticsSeal.addContent(BREAK_ROW);
                affixedLogisticsSeal.addContent(fillIDElementCCTXML(sc.getSealOne()));//#?#
                affixedLogisticsSeal.addContent(BREAK_ROW);

                utilizedLogisticsTransportEquipment.addContent(affixedLogisticsSeal);
                utilizedLogisticsTransportEquipment.addContent(BREAK_ROW);
            }

            includedHouseConsignment.addContent(utilizedLogisticsTransportEquipment);
            includedHouseConsignment.addContent(BREAK_ROW);
        }

//        Element handlingSPHInstructions = new Element("HandlingSPHInstructions", "ram", "iata:datamodel:3");
//        handlingSPHInstructions.addContent(BREAK_ROW);
//        handlingSPHInstructions.addContent(createNewElementToXml("Description", "ram", "iata:datamodel:3", entity.getHandlingInformation()));//#?#
//        handlingSPHInstructions.addContent(BREAK_ROW);
//        handlingSPHInstructions.addContent(createNewElementToXml("DescriptionCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXXX"));//#?#
//        handlingSPHInstructions.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(handlingSPHInstructions);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element handlingSSRInstructions = new Element("HandlingSSRInstructions", "ram", "iata:datamodel:3");
//        handlingSSRInstructions.addContent(BREAK_ROW);
//        handlingSSRInstructions.addContent(createNewElementToXml("Description", "ram", "iata:datamodel:3", entity.getHandlingInformation()));//#?#
//        handlingSSRInstructions.addContent(BREAK_ROW);
//        handlingSSRInstructions.addContent(createNewElementToXml("DescriptionCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXXXXXXXXX"));//#?#
//        handlingSSRInstructions.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(handlingSSRInstructions);
//        includedHouseConsignment.addContent(BREAK_ROW);
//
//        Element handlingOSIInstructions = new Element("HandlingOSIInstructions", "ram", "iata:datamodel:3");
//        handlingOSIInstructions.addContent(BREAK_ROW);
//        handlingOSIInstructions.addContent(createNewElementToXml("Description", "ram", "iata:datamodel:3", entity.getHandlingInformation()));//#?#
//        handlingOSIInstructions.addContent(BREAK_ROW);
//        handlingOSIInstructions.addContent(createNewElementToXml("DescriptionCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXXXX"));//#?#;
//        handlingOSIInstructions.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(handlingOSIInstructions);
//        includedHouseConsignment.addContent(BREAK_ROW);
//        Element includedAccountingNote = new Element("IncludedAccountingNote", "ram", "iata:datamodel:3");
//        includedAccountingNote.addContent(BREAK_ROW);
//        includedAccountingNote.addContent(createNewElementToXml("ContentCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXX"));
//        includedAccountingNote.addContent(BREAK_ROW);
//        includedAccountingNote.addContent(createNewElementToXml("Content", "ram", "iata:datamodel:3", "XXXXXXXXXXXX"));
//        includedAccountingNote.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(includedAccountingNote);
//        includedHouseConsignment.addContent(BREAK_ROW);
//        Element IncludedCustomNote = new Element("IncludedCustomNote", "ram", "iata:datamodel:3");
//        IncludedCustomNote.addContent(BREAK_ROW);
//        IncludedCustomNote.addContent(createNewElementToXml("ContentCode", "ram", "iata:datamodel:3", "T"));
//        IncludedCustomNote.addContent(BREAK_ROW);
//        IncludedCustomNote.addContent(createNewElementToXml("Content", "ram", "iata:datamodel:3", "CNPJ" + entity.getConsignee() == null ? "" : entity.getConsignee().getFederalRegistration()));
//        IncludedCustomNote.addContent(BREAK_ROW);
//        IncludedCustomNote.addContent(createNewElementToXml("SubjectCode", "ram", "iata:datamodel:3", "CNE"));
//        IncludedCustomNote.addContent(BREAK_ROW);
//        IncludedCustomNote.addContent(createNewElementToXml("CountryID", "ram", "iata:datamodel:3", entity.getConsignee() == null ? "" : entity.getConsignee().getAddress() == null ? "" : entity.getConsignee().getAddress().getCountry().getCode()));
//        IncludedCustomNote.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(IncludedCustomNote);
//        includedHouseConsignment.addContent(BREAK_ROW);
        Element associatedReferenceDocument = new Element("AssociatedReferenceDocument", "ram", "iata:datamodel:3");
        associatedReferenceDocument.addContent(BREAK_ROW);
        associatedReferenceDocument.addContent(fillIDElementCCTXML((entity.getCarrier() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric()) + "-" + entity.getMasterNumber()));//#?#
        associatedReferenceDocument.addContent(BREAK_ROW);
        associatedReferenceDocument.addContent(createNewElementToXml("IssueDateTime", "ram", "iata:datamodel:3", entity.getDateIssueHouse() == null ? "" : new DateTime(entity.getDateIssueHouse()).toString()));
        associatedReferenceDocument.addContent(BREAK_ROW);
//        associatedReferenceDocument.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", "XXXXXXXX"));//#?#
//        associatedReferenceDocument.addContent(BREAK_ROW);
        associatedReferenceDocument.addContent(fillNameElementCCTXML("Air Waybill"));
        associatedReferenceDocument.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(associatedReferenceDocument);
        includedHouseConsignment.addContent(BREAK_ROW);

//        Element associatedConsignmentCustomsProcedure = new Element("AssociatedConsignmentCustomsProcedure", "ram", "iata:datamodel:3");
//        associatedConsignmentCustomsProcedure.addContent(BREAK_ROW);
//        associatedConsignmentCustomsProcedure.addContent(createNewElementToXml("GoodsStatusCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXX"));//#?#
//        associatedConsignmentCustomsProcedure.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(associatedConsignmentCustomsProcedure);
//        includedHouseConsignment.addContent(BREAK_ROW);
        Element applicableOriginCurrencyExchange = new Element("ApplicableOriginCurrencyExchange", "ram", "iata:datamodel:3");
        applicableOriginCurrencyExchange.addContent(BREAK_ROW);
        applicableOriginCurrencyExchange.addContent(createNewElementToXml("SourceCurrencyCode", "ram", "iata:datamodel:3", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol()));//#?#
        applicableOriginCurrencyExchange.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(applicableOriginCurrencyExchange);
        includedHouseConsignment.addContent(BREAK_ROW);

        Element applicableDestinationCurrencyExchange = new Element("ApplicableDestinationCurrencyExchange", "ram", "iata:datamodel:3");
        applicableDestinationCurrencyExchange.addContent(BREAK_ROW);
        applicableDestinationCurrencyExchange.addContent(createNewElementToXml("TargetCurrencyCode", "ram", "iata:datamodel:3", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol()));//#?#
        applicableDestinationCurrencyExchange.addContent(BREAK_ROW);
//        applicableDestinationCurrencyExchange.addContent(createNewElementToXml("MarketID", "ram", "iata:datamodel:3", "XXXXXXXXX"));//#?#
//        applicableDestinationCurrencyExchange.addContent(BREAK_ROW);
//        applicableDestinationCurrencyExchange.addContent(createNewElementToXml("ConversionRate", "ram", "iata:datamodel:3", "XXXXXXXXXX"));//#?#
//        applicableDestinationCurrencyExchange.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(applicableDestinationCurrencyExchange);
        includedHouseConsignment.addContent(BREAK_ROW);

//        Element applicableLogisticsServiceCharge = new Element("ApplicableLogisticsServiceCharge", "ram", "iata:datamodel:3");
//        applicableLogisticsServiceCharge.addContent(BREAK_ROW);
//        applicableLogisticsServiceCharge.addContent(createNewElementToXml("ServiceTypeCode", "ram", "iata:datamodel:3", "XXXXXXXXXX"));//#?#
//        applicableLogisticsServiceCharge.addContent(BREAK_ROW);
//        includedHouseConsignment.addContent(applicableLogisticsServiceCharge);
//        includedHouseConsignment.addContent(BREAK_ROW);
        Element applicableLogisticsAllowanceCharge = new Element("ApplicableLogisticsAllowanceCharge", "ram", "iata:datamodel:3");
//        applicableLogisticsAllowanceCharge.addContent(BREAK_ROW);
//        applicableLogisticsAllowanceCharge.addContent(fillIDElementCCTXML("XXXXXXXXXXX"));//#?#
//        applicableLogisticsAllowanceCharge.addContent(BREAK_ROW);
//        applicableLogisticsAllowanceCharge.addContent(createNewElementToXml("Reason", "ram", "iata:datamodel:3", "XXXXXXXXXXXX"));//#?#
//        applicableLogisticsAllowanceCharge.addContent(BREAK_ROW);

        Element actualAmount = new Element("ActualAmount", "ram", "iata:datamodel:3");
        actualAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
        actualAmount.addContent(entity.getWeightToCharge() == null ? dFormat.format(new BigDecimal(0)) : dFormat.format(entity.getWeightToCharge())).toString();//#?#
        applicableLogisticsAllowanceCharge.addContent(actualAmount);
        applicableLogisticsAllowanceCharge.addContent(BREAK_ROW);
//        applicableLogisticsAllowanceCharge.addContent(createNewElementToXml("PartyTypeCode", "ram", "iata:datamodel:3", "XXXXXXXXXXXX"));//#?#
//        applicableLogisticsAllowanceCharge.addContent(BREAK_ROW);
        includedHouseConsignment.addContent(applicableLogisticsAllowanceCharge);
        includedHouseConsignment.addContent(BREAK_ROW);

        if (!entity.getVolumes().isEmpty()) {
            for (int i = 0; i < entity.getVolumes().size(); i++) {
                Element includedHouseConsignmentItem = new Element("IncludedHouseConsignmentItem", "ram", "iata:datamodel:3");
                includedHouseConsignmentItem.addContent(BREAK_ROW);
                includedHouseConsignmentItem.addContent(createNewElementToXml("SequenceNumeric", "ram", "iata:datamodel:3", String.valueOf(i + 1)));
                includedHouseConsignmentItem.addContent(BREAK_ROW);
                if (entity.getVolumes().get(i).getVolumeType() != null) {
                    Element typeCode = new Element("TypeCode", "ram", "iata:datamodel:3");
                    typeCode.setAttribute("listAgencyID", "1");//#?#
                    typeCode.addContent(entity.getVolumes().get(i) == null ? "" : entity.getVolumes().get(i).getVolumeType() == null ? "" : entity.getVolumes().get(i).getVolumeType().getCode());//#?#
                    includedHouseConsignmentItem.addContent(typeCode);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getTotalWeight() != null) {
                    Element grossWeightMeasure = new Element("GrossWeightMeasure", "ram", "iata:datamodel:3");
                    grossWeightMeasure.setAttribute("unitCode", "KGM");//#?#
                    grossWeightMeasure.addContent(entity.getVolumes().get(i).getTotalWeight() == null ? "" : dFormat.format(entity.getVolumes().get(i).getTotalWeight()).toString());
                    includedHouseConsignmentItem.addContent(grossWeightMeasure);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getVolume() != null) {
                    grossVolumeMeasure = new Element("GrossVolumeMeasure", "ram", "iata:datamodel:3");
                    grossVolumeMeasure.setAttribute("unitCode", "KGM");//#?#
                    grossVolumeMeasure.addContent(entity.getVolumes().get(i).getVolume() == null ? "" : dFormat.format(entity.getVolumes().get(i).getVolume()).toString());
                    includedHouseConsignmentItem.addContent(grossVolumeMeasure);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                if (entity.getQuotationHouse() != null && entity.getQuotationHouse().getChargeableWeight() != null) {
                    Element totalChargeAmount = new Element("TotalChargeAmount", "ram", "iata:datamodel:3");
                    totalChargeAmount.setAttribute("currencyID", entity.getShipmentCurrency() == null ? "" : entity.getShipmentCurrency().getSymbol());
                    totalChargeAmount.addContent(entity.getQuotationHouse() == null ? "" : entity.getQuotationHouse().getChargeableWeight() == null ? "" : dFormat.format(entity.getQuotationHouse().getChargeableWeight()).toString());
                    includedHouseConsignmentItem.addContent(totalChargeAmount);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }

                includedHouseConsignmentItem.addContent(createNewElementToXml("PackageQuantity", "ram", "iata:datamodel:3", String.valueOf(entity.getVolumes().size())));
                includedHouseConsignmentItem.addContent(BREAK_ROW);
                if (entity.getVolumes().get(i).getQuantity() != null) {
                    includedHouseConsignmentItem.addContent(createNewElementToXml("PieceQuantity", "ram", "iata:datamodel:3", String.valueOf(entity.getVolumes().get(i).getQuantity())));
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getVolumeType() != null) {
                    includedHouseConsignmentItem.addContent(createNewElementToXml("VolumetricFactor", "ram", "iata:datamodel:3", (entity.getVolumes().get(i).getVolumeType() == null ? "" : entity.getVolumes().get(i).getVolumeType().getCode())));//#?#
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                    if (entity.getVolumes().get(i).getVolumeType().getComments() != null) {
                        includedHouseConsignmentItem.addContent(createNewElementToXml("Information", "ram", "iata:datamodel:3", (entity.getVolumes().get(i).getVolumeType() == null ? "" : entity.getVolumes().get(i).getVolumeType().getComments())));//#?#
                        includedHouseConsignmentItem.addContent(BREAK_ROW);
                    }
                    if (entity.getVolumes().get(i).getVolumeType().getDescription() != null) {

                    }
                    String descriptionCargo = entity.getVolumes().get(i).getVolumeType().getDescription();
                    descriptionCargo = descriptionCargo.replaceAll("\n", " ");
                    descriptionCargo = descriptionCargo.replaceAll(XML_PATTERN, " ");
                    descriptionCargo = descriptionCargo.replaceAll("&", "E");
                    descriptionCargo = descriptionCargo.replaceAll("#", " ");
                    descriptionCargo = descriptionCargo.replaceAll(">", " ");
                    descriptionCargo = descriptionCargo.replaceAll("â€œ", " ");
                    descriptionCargo = descriptionCargo.replaceAll("`", " ");
                    descriptionCargo = descriptionCargo.replaceAll("\"", " ");
                    Element natureIdentificationTransportCargo = new Element("NatureIdentificationTransportCargo", "ram", "iata:datamodel:3");
                    natureIdentificationTransportCargo.addContent(BREAK_ROW);
                    natureIdentificationTransportCargo.addContent(createNewElementToXml("Identification", "ram", "iata:datamodel:3", descriptionCargo));//#?#
                    natureIdentificationTransportCargo.addContent(BREAK_ROW);
                    includedHouseConsignmentItem.addContent(natureIdentificationTransportCargo);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                if (entity.getOrigin().getCountry() != null) {
                    Element originCountry = new Element("OriginCountry", "ram", "iata:datamodel:3");
                    originCountry.addContent(BREAK_ROW);
                    originCountry.addContent(createNewElementToXml("ID", "ram", "iata:datamodel:3", entity.getOrigin() == null ? "" : entity.getOrigin().getCountry() == null ? "" : entity.getOrigin().getCountry().getCode()));//#?#
                    originCountry.addContent(BREAK_ROW);
                    includedHouseConsignmentItem.addContent(originCountry);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }

                Element associatedUnitLoadTransportEquipment = new Element("AssociatedUnitLoadTransportEquipment", "ram", "iata:datamodel:3");
//                associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);
//                associatedUnitLoadTransportEquipment.addContent(fillIDElementCCTXML("XXXXXXXXXXXX"));//#?#
//                associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);

                Element tareWeightMeasure = new Element("TareWeightMeasure", "ram", "iata:datamodel:3");
                tareWeightMeasure.setAttribute("unitCode", "KGM");
                tareWeightMeasure.addContent(entity.getVolumes().get(i).getUnitWeight() == null ? "" : entity.getVolumes().get(i).getUnitWeight().toString());//#?#
                associatedUnitLoadTransportEquipment.addContent(tareWeightMeasure);
                associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);

                associatedUnitLoadTransportEquipment.addContent(createNewElementToXml("LoadedPackageQuantity", "ram", "iata:datamodel:3", entity.getVolumesExit() == null ? "" : String.valueOf(entity.getVolumesExit().size())));//#?#
                associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);
                if (entity.getVolumes().get(i).getVolumeType() != null) {
                    associatedUnitLoadTransportEquipment.addContent(createNewElementToXml("CharacteristicCode", "ram", "iata:datamodel:3", entity.getVolumes().get(i).getVolumeType() == null ? "" : entity.getVolumes().get(i).getVolumeType().getCode()));//#?#
                    associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);
                }
                if (entity.getCustomBroker() != null) {
                    Element operatingParty = new Element("OperatingParty", "ram", "iata:datamodel:3");
                    operatingParty.setAttribute("schemeAgencyID", "1");
                    operatingParty.addContent(entity.getQuotationHouse() == null ? "" : entity.getCustomBroker() == null ? "" : entity.getCustomBroker().getCommercialName());//#?#
                    associatedUnitLoadTransportEquipment.addContent(operatingParty);
                    associatedUnitLoadTransportEquipment.addContent(BREAK_ROW);
                    includedHouseConsignmentItem.addContent(associatedUnitLoadTransportEquipment);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }
                Element transportLogisticsPackage = new Element("TransportLogisticsPackage", "ram", "iata:datamodel:3");
                if (entity.getVolumes().get(i).getQuantity() != null) {
                    transportLogisticsPackage.addContent(BREAK_ROW);
                    transportLogisticsPackage.addContent(createNewElementToXml("ItemQuantity", "ram", "iata:datamodel:3", String.valueOf(entity.getVolumes().get(i).getQuantity())));//#?#
                    transportLogisticsPackage.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getTotalWeight() != null) {
                    Element grossWeightMeasure = new Element("GrossWeightMeasure", "ram", "iata:datamodel:3");
                    grossWeightMeasure.setAttribute("unitCode", "KGM");//#?#
                    grossWeightMeasure.addContent(entity.getVolumes().get(i).getTotalWeight() == null ? "" : dFormat.format(entity.getVolumes().get(i).getTotalWeight()).toString());//#?#
                    transportLogisticsPackage.addContent(grossWeightMeasure);
                    transportLogisticsPackage.addContent(BREAK_ROW);
                }
                Element linearSpatialDimension = new Element("LinearSpatialDimension", "ram", "iata:datamodel:3");
                if (entity.getVolumes().get(i).getWidth() != null) {
                    Element widthMeasure = new Element("WidthMeasure", "ram", "iata:datamodel:3");
                    widthMeasure.setAttribute("unitCode", "CMT");
                    widthMeasure.addContent(entity.getVolumes().get(i).getWidth() == null ? "" : dFormat.format(entity.getVolumes().get(i).getWidth()).toString());
                    linearSpatialDimension.addContent(widthMeasure);
                    linearSpatialDimension.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getLength() != null) {
                    Element lengthMeasure = new Element("LengthMeasure", "ram", "iata:datamodel:3");
                    lengthMeasure.setAttribute("unitCode", "CMT");
                    lengthMeasure.addContent(entity.getVolumes().get(i).getLength() == null ? "" : dFormat.format(entity.getVolumes().get(i).getLength()).toString());
                    linearSpatialDimension.addContent(lengthMeasure);
                    linearSpatialDimension.addContent(BREAK_ROW);
                }
                if (entity.getVolumes().get(i).getHeight() != null) {
                    Element heightMeasure = new Element("HeightMeasure", "ram", "iata:datamodel:3");
                    heightMeasure.setAttribute("unitCode", "CMT");
                    heightMeasure.addContent(entity.getVolumes().get(i).getHeight() == null ? "" : dFormat.format(entity.getVolumes().get(i).getHeight()).toString());
                    linearSpatialDimension.addContent(heightMeasure);
                    linearSpatialDimension.addContent(BREAK_ROW);
                }
                transportLogisticsPackage.addContent(linearSpatialDimension);
                transportLogisticsPackage.addContent(BREAK_ROW);
                includedHouseConsignmentItem.addContent(transportLogisticsPackage);
                includedHouseConsignmentItem.addContent(BREAK_ROW);
                Object[] frt = new Object[3];
                frt = houseService.findfrtValueAndCurrency(entity.getId());
                if (frt[0] != null && frt[1] != null) {
                    Element applicableFreightRateServiceCharge = new Element("ApplicableFreightRateServiceCharge", "ram", "iata:datamodel:3");
//                applicableFreightRateServiceCharge.addContent(BREAK_ROW);
//                applicableFreightRateServiceCharge.addContent(createNewElementToXml("CategoryCode", "ram", "iata:datamodel:3", entity.getVolumes().get(i).getVolumeType() == null ? " " : entity.getVolumes().get(i).getVolumeType().getDescription()));//#?#
//                applicableFreightRateServiceCharge.addContent(BREAK_ROW);
//                applicableFreightRateServiceCharge.addContent(createNewElementToXml("CommodityItemID", "ram", "iata:datamodel:3", entity.getVolumes().get(i).getId().toString()));//#?#
//                applicableFreightRateServiceCharge.addContent(BREAK_ROW);
                    if (entity.getWeightToCharge() != null) {
                        Element chargeableWeightMeasure = new Element("ChargeableWeightMeasure", "ram", "iata:datamodel:3");
                        chargeableWeightMeasure.setAttribute("unitCode", "KGM");
                        chargeableWeightMeasure.addContent(dFormat.format(entity.getWeightToCharge()));//#?#
                        applicableFreightRateServiceCharge.addContent(chargeableWeightMeasure);
                        applicableFreightRateServiceCharge.addContent(BREAK_ROW);
                    }
                    applicableFreightRateServiceCharge.addContent(createNewElementToXml("AppliedRate", "ram", "iata:datamodel:3", dFormat.format((BigDecimal) frt[2]).toString()));
                    applicableFreightRateServiceCharge.addContent(BREAK_ROW);
                    Element appliedAmount = new Element("AppliedAmount", "ram", "iata:datamodel:3");
                    appliedAmount.setAttribute("currencyID", (String) frt[0]);
                    appliedAmount.addContent(dFormat.format((BigDecimal) frt[1]).toString());
                    applicableFreightRateServiceCharge.addContent(appliedAmount);
                    applicableFreightRateServiceCharge.addContent(BREAK_ROW);
                    includedHouseConsignmentItem.addContent(applicableFreightRateServiceCharge);
                    includedHouseConsignmentItem.addContent(BREAK_ROW);
                }

                associatedReferenceDocument = new Element("AssociatedReferenceDocument", "ram", "iata:datamodel:3");
                associatedReferenceDocument.addContent(BREAK_ROW);
                associatedReferenceDocument.addContent(fillIDElementCCTXML(entity.getCarrier() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric() == null ? entity.getMasterNumber() : entity.getCarrier().getCodNumeric() + entity.getMasterNumber()));
                associatedReferenceDocument.addContent(BREAK_ROW);
                if (entity.getDateIssueHouse() != null) {
                    associatedReferenceDocument.addContent(createNewElementToXml("IssueDateTime", "ram", "iata:datamodel:3", entity.getDateIssueHouse() == null ? "" : new DateTime(entity.getDateIssueHouse()).toString()));
                    associatedReferenceDocument.addContent(BREAK_ROW);
                }
//                associatedReferenceDocument.addContent(createNewElementToXml("TypeCode", "ram", "iata:datamodel:3", entity.getVolumes().get(i).getVolumeType() == null ? " " : entity.getVolumes().get(i).getVolumeType().getCode()));//#?#
//                associatedReferenceDocument.addContent(BREAK_ROW);
                associatedReferenceDocument.addContent(fillNameElementCCTXML("Air Waybill"));
                associatedReferenceDocument.addContent(BREAK_ROW);
                includedHouseConsignmentItem.addContent(associatedReferenceDocument);
                includedHouseConsignmentItem.addContent(BREAK_ROW);
//
//                Element specifiedRateCombinationPointLocation = new Element("SpecifiedRateCombinationPointLocation", "ram", "iata:datamodel:3");
//                specifiedRateCombinationPointLocation.addContent(BREAK_ROW);
//                specifiedRateCombinationPointLocation.addContent(fillIDElementCCTXML("XXXXXXXXXXXX"));//#?#
//                specifiedRateCombinationPointLocation.addContent(BREAK_ROW);
//                includedHouseConsignmentItem.addContent(specifiedRateCombinationPointLocation);
//                includedHouseConsignmentItem.addContent(BREAK_ROW);

                includedHouseConsignment.addContent(includedHouseConsignmentItem);
                includedHouseConsignment.addContent(BREAK_ROW);
            }
        }

        masterConsignment.addContent(includedHouseConsignment);
        masterConsignment.addContent(BREAK_ROW);
        //Adicionar todos os elementos dentro do pai 'houseWaybill'
        houseWaybill.addContent(BREAK_ROW);
        houseWaybill.addContent(messageHeaderDocument);
        houseWaybill.addContent(BREAK_ROW);
        houseWaybill.addContent(businessHeaderDocument);
        houseWaybill.addContent(BREAK_ROW);
        houseWaybill.addContent(masterConsignment);
        houseWaybill.addContent(BREAK_ROW);
        return documentCCT;
    }

    private Element fillIDElementCCTXML(String data) {
        return createNewElementToXml("ID", "ram", "iata:datamodel:3", data == null ? "XXXXXXX" : data);
    }

    private Element fillNameElementCCTXML(String value) {
        return createNewElementToXml("Name", "ram", "iata:datamodel:3", value);
    }

    private Element fillPrimaryIdElementCCTXML(String schemeID, String Content) {
        Element primaryID = new Element("PrimaryID", "ram", "iata:datamodel:3");
        primaryID.setAttribute("schemeID", schemeID);
        primaryID.addContent(Content);
        return primaryID;
    }

    private Element fillPrimaryIdElementCCTXML(String value) {
        Element primaryID = new Element("PrimaryID", "ram", "iata:datamodel:3");
        primaryID.setAttribute("schemeID", "1");
        primaryID.addContent(value);
        return primaryID;
    }

    private Element fillPostalStructuredAddressCCTXML(Address adress) {
        Element postalStructuredAddress = new Element("PostalStructuredAddress", "ram", "iata:datamodel:3");
        if (adress != null) {
            postalStructuredAddress.addContent(BREAK_ROW);
            if (adress.getZipcode() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("PostcodeCode", "ram", "iata:datamodel:3", adress.getZipcode()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getStreetName() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("StreetName", "ram", "iata:datamodel:3", adress.getStreetName()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getCityName() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("CityName", "ram", "iata:datamodel:3", adress.getCityName()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getCountry() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("CountryID", "ram", "iata:datamodel:3", adress.getCountry() == null ? "" : adress.getCountry().getCode()));
                postalStructuredAddress.addContent(BREAK_ROW);
                postalStructuredAddress.addContent(createNewElementToXml("CountryName", "ram", "iata:datamodel:3", adress.getCountry() == null ? "" : adress.getCountry().getName()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getStateName() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("CountrySubDivisionName", "ram", "iata:datamodel:3", adress.getStateName()));
                postalStructuredAddress.addContent(BREAK_ROW);

            } else if (adress.getState() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("CountrySubDivisionName", "ram", "iata:datamodel:3", adress.getState().getName()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getZipcode() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("PostOfficeBox", "ram", "iata:datamodel:3", adress.getZipcode()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
            if (adress.getCity() != null) {
                postalStructuredAddress.addContent(createNewElementToXml("CityID", "ram", "iata:datamodel:3", adress.getCity() == null ? "" : adress.getCity().getCode()));
                postalStructuredAddress.addContent(BREAK_ROW);
            }
        } else {
        }
        return postalStructuredAddress;
    }

    public Element createNewElementToXml(String elementName, String prefix, String uri, String elementValue) {
        Element newElement = new Element(elementName, prefix, uri);
        if (elementValue != null) {
            newElement.setText(elementValue);
        }
        return newElement;
    }

    private Element fillDefinedTradeContactCCTXML(ContactGeneral cg) {
        Element definedTradeContact = new Element("DefinedTradeContact", "ram", "iata:datamodel:3");
        if (cg != null) {
            User us = cg.getUser();
            if (us != null) {
                //Element definedTradeContact = new Element("DefinedTradeContact", "ram", "iata:datamodel:3");
                definedTradeContact.addContent(BREAK_ROW);
                if (us.getName() != null) {
                    definedTradeContact.addContent(createNewElementToXml("PersonName", "ram", "iata:datamodel:3", us.getName()));
                    definedTradeContact.addContent(BREAK_ROW);
                }
                if (us.getPosition() != null) {
                    definedTradeContact.addContent(createNewElementToXml("DepartmentName", "ram", "iata:datamodel:3", us.getPosition()));
                    definedTradeContact.addContent(BREAK_ROW);
                }
                if (us.getPhone() != null) {
                    Element directTelephoneCommunication = new Element("DirectTelephoneCommunication", "ram", "iata:datamodel:3");
                    directTelephoneCommunication.addContent(BREAK_ROW);
                    directTelephoneCommunication.addContent(createNewElementToXml("CompleteNumber", "ram", "iata:datamodel:3", us.getPhone()));
                    directTelephoneCommunication.addContent(BREAK_ROW);
                    definedTradeContact.addContent(directTelephoneCommunication);
                    definedTradeContact.addContent(BREAK_ROW);
                }
                if (cg.getFaxPhone() != null) {
                    Element faxCommunication = new Element("FaxCommunication", "ram", "iata:datamodel:3");
                    faxCommunication.addContent(BREAK_ROW);
                    faxCommunication.addContent(createNewElementToXml("CompleteNumber", "ram", "iata:datamodel:3", cg.getFaxPhone()));
                    faxCommunication.addContent(BREAK_ROW);
                    definedTradeContact.addContent(faxCommunication);
                    definedTradeContact.addContent(BREAK_ROW);
                }

//                Element uRIEmailCommunication = new Element("URIEmailCommunication", "ram", "iata:datamodel:3");
//                uRIEmailCommunication.addContent(BREAK_ROW);
//                uRIEmailCommunication.addContent(generalUtils.createNewElementToXml("URIID", "ram", "iata:datamodel:3", "XXXXXXXXXX"));
//                uRIEmailCommunication.addContent(BREAK_ROW);
//                definedTradeContact.addContent(uRIEmailCommunication);
//                definedTradeContact.addContent(BREAK_ROW);
//                Element telexCommunication = new Element("TelexCommunication", "ram", "iata:datamodel:3");
//                telexCommunication.addContent(BREAK_ROW);
//                telexCommunication.addContent(generalUtils.createNewElementToXml("CompleteNumber", "ram", "iata:datamodel:3", "XXXXXXXXXX"));
//                telexCommunication.addContent(BREAK_ROW);
//                definedTradeContact.addContent(telexCommunication);
//                definedTradeContact.addContent(BREAK_ROW);
            } else {
            }
        }

        return definedTradeContact;
    }

}
