package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.ShipmentHouseDao;
import br.com.mmcs.cct.imp.model.ShipmentHouse;
import br.com.mmcs.cct.imp.utils.converter.ShipmentHouseConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public class ShipmentHouseDaoImpl implements ShipmentHouseDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<Object[]> findShipmentsByShipmentModal(final String shipmentModal) {
        List<Object[]> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT")
                .append(" FALSE,")
                .append(" SH.SHIPMENT_NUMBER,")
                .append(" CG.COMMERCIAL_NAME,")
                .append(" SH.ID")
                .append(" FROM M0020_SHIPMENT_HOUSE SH")
                .append(" INNER JOIN M0130_CONTACT_GENERAL CG ON CG.ID = SH.CLIENT_CONTACT_GENERAL_FK")
                .append(" WHERE SH.SHIPMENT_MODAL = ?");

        try {
            connection = FabricaDeConexoes.getConexao();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, shipmentModal);
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i == 1) {
                        values[i - 1] = false;
                    } else {
                        values[i - 1] = resultSet.getObject(i);
                    }
                }
                results.add(values);
            }

            FabricaDeConexoes.fecharConexao(connection, statement, resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return results;
    }

    @Override
    public ShipmentHouse findShipmentById(final Long id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("	shipmentHouse.ID, ")
                .append("	shipmentHouse.SHIPMENT_NUMBER, ")
                .append("		shipper.ID, ")
                .append("		shipper.COMMERCIAL_NAME, ")
                .append("		shipper.FAXPHONE, ")
                .append("		shipperAddress.ZIPCODE, ")
                .append("		shipperAddress.STREET_NAME, ")
                .append("		shipperAddress.CITY_NAME, ")
                .append("		shipperAddress.STATE_NAME, ")
                .append("		shipperAddressState.NAME_STATE, ")
                .append("		shipperAddressState.ABBREVIATION, ")
                .append("		shipperAddressCountry.CODE, ")
                .append("		shipperAddressCountry.NAME_COUNTRY, ")
                .append("		shipperUser.NAME_USER, ")
                .append("		shipperUser.`POSITION`, ")
                .append("		shipperUser.PHONE, ")
                .append("	carrier.COD_NUMERIC, ")
                .append("		origin.CODE, ")
                .append("		origin.NAME_PORT, ")
                .append("		originCountry.CODE, ")
                .append("	shipmentHouse.BRUTE_WEIGHT, ")
                .append("	shipmentHouse.VOLUME_QUANTITY, ")
                .append("	shipmentHouse.MASTER_NUMBER, ")
                .append("	destination.CODE, ")
                .append("	destination.NAME_PORT, ")
                .append("		notify.ID, ")
                .append("		notify.COMMERCIAL_NAME, ")
                .append("		notify.FAXPHONE, ")
                .append("		notifyAddress.ZIPCODE, ")
                .append("		notifyAddress.STREET_NAME, ")
                .append("		notifyAddress.CITY_NAME, ")
                .append("		notifyAddress.STATE_NAME, ")
                .append("		notifyAddressState.NAME_STATE, ")
                .append("		notifyAddressState.ABBREVIATION, ")
                .append("		notifyAddressCountry.CODE, ")
                .append("		notifyAddressCountry.NAME_COUNTRY, ")
                .append("		notifyUser.NAME_USER, ")
                .append("		notifyUser.`POSITION`, ")
                .append("		notifyUser.PHONE, ")
                .append("		notifyAcitivity.NAME, ")
                .append("		notifyAcitivity.DESCRIPTION, ")
                .append("	shipmentHouse.WEIGHT_TO_CHARGE, ")
                .append("	currency.SYMBOL, ")
                .append("		container.CONTAINER_NUMBER, ")
                .append("		containerVolume.CODE, ")
                .append("		containerVolume.DESCRIPTION, ")
                .append("		container.SEAL_ONE, ")
                .append("	shipmentHouse.FORECASTS_OUTPUT, ")
                .append("	shipmentHouse.FORECASTS_ARRIVAL, ")
                .append("	shipmentHouse.TRAVEL_NUMBER, ")
                .append("		agent.ID, ")
                .append("		agent.COMMERCIAL_NAME, ")
                .append("		agent.FAXPHONE, ")
                .append("		agentAddress.ZIPCODE, ")
                .append("		agentAddress.STREET_NAME, ")
                .append("		agentAddress.CITY_NAME, ")
                .append("		agentAddress.STATE_NAME, ")
                .append("		agentAddressState.NAME_STATE, ")
                .append("		agentAddressState.ABBREVIATION, ")
                .append("		agentAddressCountry.CODE, ")
                .append("		agentAddressCountry.NAME_COUNTRY, ")
                .append("		agentUser.NAME_USER, ")
                .append("		agentUser.`POSITION`, ")
                .append("		agentUser.PHONE, ")
                .append("	consignee.ID, ")
                .append("	consignee.COMMERCIAL_NAME, ")
                .append("	consigneeAddress.STREET_NAME, ")
                .append("	consigneeAddress.ZIPCODE, ")
                .append("	consigneeAddress.CITY_NAME, ")
                .append("	consigneeAddress.STATE_NAME, ")
                .append("	consigneeAddressState.NAME_STATE, ")
                .append("	consigneeAddressState.ABBREVIATION, ")
                .append("	consigneeAddressCountry.CODE, ")
                .append("	consigneeAddressCountry.NAME_COUNTRY, ")
                .append("	consigneeUser.NAME_USER, ")
                .append("	consigneeUser.`POSITION`, ")
                .append("	consigneeUser.PHONE, ")
                .append("	consignee.FAXPHONE, ")
                .append("	broker.COMMERCIAL_NAME, ")
                .append("	shipmentHouse.COMMODITY_DESCRIPTION, ")
                .append("	shipmentHouse.DATE_ISSUE_HOUSE, ")
                .append("	quotation.CHARGEABLE_WEIGHT ")
                .append("FROM M0020_SHIPMENT_HOUSE shipmentHouse ")
                .append("	LEFT JOIN M0130_CONTACT_GENERAL shipper ON shipper.ID = shipmentHouse.SHIPPER_CONTACT_GENERAL_FK ")
                .append("	LEFT JOIN M0001_ADDRESS shipperAddress ON shipperAddress.ID = shipper.ADDRESS_FK ")
                .append("	LEFT JOIN M0001_STATE shipperAddressState ON shipperAddressState.ID = shipperAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0001_COUNTRY shipperAddressCountry ON shipperAddressCountry.ID = shipperAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0003_USER shipperUser ON shipperUser.ID = shipper.USER_FK ")
                .append("LEFT JOIN M0130_CONTACT_GENERAL carrier ON carrier.ID = shipmentHouse.CARRIER_CONTACT_GENERAL_FK ")
                .append("	LEFT JOIN M0105_MAPORT origin ON origin.ID = shipmentHouse.ORIGIN_MAPORT_FK ")
                .append("	LEFT JOIN M0001_COUNTRY originCountry ON originCountry.ID = origin.COUNTRY_FK  ")
                .append("LEFT JOIN M0105_MAPORT destination ON destination.ID = shipmentHouse.DESTINY_MAPORT_FK ")
                .append("	LEFT JOIN M0130_CONTACT_GENERAL notify ON notify.ID = shipmentHouse.NOTIFY_CONTACT_GENERAL_FK ")
                .append("	LEFT JOIN M0111_ACTIVITY notifyAcitivity ON notifyAcitivity.ID = notify.ACTIVITY_FK ")
                .append("	LEFT JOIN M0001_ADDRESS notifyAddress ON notifyAddress.ID = notify.ADDRESS_FK ")
                .append("	LEFT JOIN M0001_STATE notifyAddressState ON notifyAddressState.ID = notifyAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0001_COUNTRY notifyAddressCountry ON notifyAddressCountry.ID = notifyAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0003_USER notifyUser ON notifyUser.ID = notify.USER_FK ")
                .append("LEFT JOIN M0101_CURRENCY currency ON currency.ID = shipmentHouse.SHIPMENT_CURRENCY_FK ")
                .append("	LEFT JOIN M0020_SHIPMENT_CONTAINER container ON container.HOUSE_FK = shipmentHouse.ID ")
                .append("	LEFT JOIN M0110_VOLUME_TYPE containerVolume ON containerVolume.ID = container.VOLUME_TYPE_FK ")
                .append("LEFT JOIN M0130_CONTACT_GENERAL agent ON agent.ID = shipmentHouse.AGENT_CONTACT_GENERAL_FK ")
                .append("LEFT JOIN M0001_ADDRESS agentAddress ON agentAddress.ID = agent.ADDRESS_FK ")
                .append("LEFT JOIN M0001_STATE agentAddressState ON agentAddressState.ID = agentAddress.COUNTRY_FK ")
                .append("LEFT JOIN M0001_COUNTRY agentAddressCountry ON agentAddressCountry.ID = agentAddress.COUNTRY_FK ")
                .append("LEFT JOIN M0003_USER agentUser ON agentUser.ID = agent.USER_FK ")
                .append("	LEFT JOIN M0130_CONTACT_GENERAL consignee ON consignee.ID = shipmentHouse.CNEE_CONTACT_GENERAL_FK ")
                .append("	LEFT JOIN M0001_ADDRESS consigneeAddress ON consigneeAddress.ID = consignee.ADDRESS_FK ")
                .append("	LEFT JOIN M0001_STATE consigneeAddressState ON consigneeAddressState.ID = consigneeAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0001_COUNTRY consigneeAddressCountry ON consigneeAddressCountry.ID = consigneeAddress.COUNTRY_FK ")
                .append("	LEFT JOIN M0003_USER consigneeUser ON consigneeUser.ID = consignee.USER_FK ")
                .append("LEFT JOIN M0130_CONTACT_GENERAL broker ON broker.ID = shipmentHouse.CUSTOM_BROKER_CG_FK ")
                .append("LEFT JOIN M0205_QUOTATION quotation ON quotation.ID = shipmentHouse.QUOTATION_FK ")
                .append("WHERE shipmentHouse.ID = ? ");

        try {
            connection = FabricaDeConexoes.getConexao();
            statement = connection.prepareStatement(query.toString());
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Object[] values = new Object[columnCount];
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
            }

            FabricaDeConexoes.fecharConexao(connection, statement, resultSet);
            resultSet.close();
            statement.close();
            connection.close();

            return ShipmentHouseConverter.convert(values);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
