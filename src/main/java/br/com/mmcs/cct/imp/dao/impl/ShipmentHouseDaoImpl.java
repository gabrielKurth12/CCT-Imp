package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.ShipmentHouseDao;
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
    public Object[] findfrtValueAndCurrency(final Long houseId) {
        List<Object[]> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();

//        query.append("SELECT")
//                .append(" SH.SHIPMENT_NUMBER,")
//                .append(" CG.COMMERCIAL_NAME")
//                .append(" FROM M0020_SHIPMENT_HOUSE SH")
//                .append(" INNER JOIN M0130_CONTACT_GENERAL CG ON CG.ID = SH.CLIENT_CONTACT_GENERAL_FK")
//                .append(" WHERE SH.SHIPMENT_MODAL = 'CAI'");
//
//        try {
//            connection = FabricaDeConexoes.getConexao();
//            statement = connection.prepareStatement(query.toString());
////            statement.setString(1, shipmentModal);
//            resultSet = statement.executeQuery();
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//
//            while (resultSet.next()) {
//                Object[] values = new Object[columnCount];
//                for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                    values[i - 1] = resultSet.getObject(i);
//                }
//                results.add(values);
//            }
//
//            FabricaDeConexoes.fecharConexao(connection, statement, resultSet);
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        return null;
    }

    @Override
    public List<Object[]> findShipmentsByShipmentModal(final String shipmentModal) {
        List<Object[]> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT")
                .append(" FALSE,")
                .append(" SH.SHIPMENT_NUMBER,")
                .append(" CG.COMMERCIAL_NAME")
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

}
