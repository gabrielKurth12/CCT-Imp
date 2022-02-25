package br.com.mmcs.cct.imp.dao.impl;

import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
import br.com.mmcs.cct.imp.dao.QuotationVolumeDao;
import br.com.mmcs.cct.imp.model.QuotationVolume;
import br.com.mmcs.cct.imp.utils.converter.QuotationVolumeConverter;
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
public class QuotationVolumeDaoImpl implements QuotationVolumeDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<QuotationVolume> findQuotationVolumeByShipmentHouseId(final Long shipmentHouseId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("	volumeType.CODE,")
                .append("	volumeType.COMMENTS,")
                .append("	volumeType.DESCRIPTION,")
                .append("	volume.TOTAL_WEIGHT,")
                .append("	volume.VOL_VOLUME,")
                .append("	volume.VOL_QUANTITY,")
                .append("	volume.UNIT_WEIGHT,")
                .append("	volume.VOL_WIDTH,")
                .append("	volume.VOL_HEIGHT,")
                .append("	volume.VOL_LENGTH ")
                .append("FROM M0205_QUOTATION_VOLUME volume ")
                .append("LEFT JOIN M0110_VOLUME_TYPE volumeType ON volumeType.ID = volume.VOLUME_TYPE_FK ")
                .append("WHERE volume.HOUSE_FK = ? ");

        try {
            List<Object[]> results = new ArrayList<>();
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

            return QuotationVolumeConverter.convert(results);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
