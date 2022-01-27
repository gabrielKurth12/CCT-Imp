package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.ShipmentHouseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public class ShipmentHouseDaoImpl implements ShipmentHouseDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private List<Object[]> results;

    @Override //TODO tratar query
    public Object[] findfrtValueAndCurrency(String shipmentModal) {
        StringBuilder query = new StringBuilder();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        query.append("SELECT")
                .append(" SH.SHIPMENT_NUMBER,")
                .append(" CG.COMMERCIAL_NAME")
                .append(" FROM M0020_SHIPMENT_HOUSE SH")
                .append(" INNER JOIN M0130_CONTACT_GENERAL CG ON CG.ID = SH.CLIENT_CONTACT_GENERAL_FK")
                .append(" WHERE SH.SHIPMENT_MODAL = ?1");

        try {
            connection = FabricaDeConexoes.getConexao();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, shipmentModal);
            resultSet = statement.executeQuery();

//            while (resultSet.next()) {
//                Object[] values = new Object[columnCount];
//                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                    values[i - 1] = resultSet.getObject(i);
//                }
//                results.add(values);
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
