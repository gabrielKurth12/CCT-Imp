package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.ShipmentHouseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Rosa
 */
public class ShipmentHouseDaoImpl implements ShipmentHouseDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override //TODO tratar query
    public Object[] findfrtValueAndCurrency(Long houseId) {
        StringBuilder query = new StringBuilder();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

//            while (resultSet.next()) {
//                Object[] values = new Object[columnCount];
//                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                    values[i - 1] = resultSet.getObject(i);
//                }
//                dadosDaListagem.add(values);
//            }

            FabricaDeConexoes.fecharConexao(connection, statement, resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

//        for (Object[] objects : dadosDaListagem) {
//            empresa = new Empresa();
//            empresa.setId((Long) objects[0]);
//            empresa.setCnpj((String) objects[1]);
//            empresas.add(empresa);
//        }
        return null;

//        Query query = getEntityManager().createNativeQuery(query.toString());
//        query.setParameter(1, houseId);
//
//        return query.getResultList().isEmpty() ? new Object[]{null, null, null} : (Object[]) query.getResultList().get(0);
    }

}
