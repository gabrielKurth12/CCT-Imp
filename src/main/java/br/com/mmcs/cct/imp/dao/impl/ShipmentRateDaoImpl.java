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
        query.append("CURR.SYMBOL, ");
        query.append("SR.TOTAL_SALES, ");
        query.append("SR.RATE_SALES, ");
        query.append("SER.CODE ");
        query.append("FROM M0020_SHIPMENT_HOUSE SH ");
        query.append("LEFT JOIN M0020_SHIPMENT_RATE AS SR ON SH.ID = SR.HOUSE_FK ");
        query.append("LEFT JOIN M0101_CURRENCY AS CURR ON CURR.ID = SR.CURRENCY_SALES_FK ");
        query.append("LEFT JOIN M0201_SERVICE AS SER ON SER.ID = SR.SERVICE_FK ");
        query.append("WHERE SER.IS_FREIGHT IS TRUE AND SH.ID = ?1 ");

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
