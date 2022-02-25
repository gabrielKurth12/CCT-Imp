package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.ShipmentRateDao;
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
public class ShipmentRateDaoImpl implements ShipmentRateDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public Object[] findFirstValueAndCurrencyByShipmentHouseId(final Long houseId) {
        List<Object[]> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("currency.SYMBOL, ");
        query.append("shipmentRate.TOTAL_SALES, ");
        query.append("shipmentRate.RATE_SALES, ");
        query.append("service.CODE ");
        query.append("FROM M0020_SHIPMENT_RATE shipmentRate ");
        query.append("LEFT JOIN M0101_CURRENCY currency ON currency.ID = shipmentRate.CURRENCY_SALES_FK ");
        query.append("LEFT JOIN M0201_SERVICE service ON service.ID = shipmentRate.SERVICE_FK ");
        query.append("WHERE service.IS_FREIGHT IS TRUE AND shipmentRate.HOUSE_FK = ?1 ");

        try {
            connection = FabricaDeConexoes.getConexao();
            statement = connection.prepareStatement(query.toString());
            statement.setLong(1, houseId);
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

            return values;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
