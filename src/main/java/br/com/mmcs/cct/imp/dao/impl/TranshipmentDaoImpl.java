package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.TranshipmentDao;
import br.com.mmcs.cct.imp.model.Transhipment;
import br.com.mmcs.cct.imp.utils.converter.TranshipmentConverter;
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
public class TranshipmentDaoImpl implements TranshipmentDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<Transhipment> findByShipmentHouseId(final Long shipmentHouseId) {
        List<Object[]> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT  ")
                .append("	transhipment.TRAVEL_NUMBER, ")
                .append("	transhipment.DATE_OUT, ")
                .append("	port.CODE, ")
                .append("	port.NAME_PORT ")
                .append("FROM M0020_TRANSHIPMENT transhipment ")
                .append("LEFT JOIN M0105_MAPORT port ON port.ID = transhipment.PORT_FK ")
                .append("WHERE transhipment.HOUSE_FK = ?1 ");

        try {
            connection = FabricaDeConexoes.getConexao();
            statement = connection.prepareStatement(query.toString());
            statement.setLong(1, shipmentHouseId);
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

        return TranshipmentConverter.convert(results);
    }

}
