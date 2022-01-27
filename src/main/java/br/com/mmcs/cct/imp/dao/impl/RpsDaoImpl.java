    //package br.com.mmcs.cct.imp.dao.impl;
//
//import br.com.mmcs.cct.imp.conexao.FabricaDeConexoes;
//import br.com.mmcs.cct.imp.dao.RpsDao;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author suporte
// */
//public class RpsDaoImpl implements RpsDao {
//
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;
//    private ResultSetMetaData resultSetMetaData;
//    private int columnCount;
//    private List<Object[]> dadosDaListagem;
//
//    @Override
//    public List<Object[]> buscarDadosParaConhecimentoDeCarga() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT ")
//                .append("SH.SHIPMENT_NUMBER, ")
//                .append("CG.NAME_CG, ")
//                .append("CG.FEDERAL_REGISTRATION ")
//                .append("FROM M0020_SHIPMENT_HOUSE SH ")
//                .append("LEFT JOIN M0130_CONTACT_GENERAL CG ON CG.ID = SH.CLIENT_CONTACT_GENERAL_FK ");
//
//        try {
//            connection = FabricaDeConexoes.getConexao();
//            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, codigo);
//            resultSet = statement.executeQuery();
//            resultSetMetaData = resultSet.getMetaData();
//            columnCount = resultSetMetaData.getColumnCount();
//            dadosDaListagem = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Object[] values = new Object[columnCount];
//                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                    values[i - 1] = resultSet.getObject(i);
//                }
//                dadosDaListagem.add(values);
//            }
//
//            FabricaDeConexoes.fecharConexao(connection, statement, resultSet);
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return dadosDaListagem;
//    }
//
////    @Override
////    public Object[] buscaDadosParaGerarXmlRPS(String codigo) {
////
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append("EMP.FEDERAL_REGISTRATION,"); //0
////        builder.append("EMP.CITY_REGISTRATION,");//1 - Inscrição Municipal
////        builder.append("FP.CODE,");//2
////        builder.append("FP.DATE_ISSUE,");//3
////        builder.append("FP.TOTAL_TAXABLE,");//4
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME = 'PIS' LIMIT 1),");//5        
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME = 'COFINS' LIMIT 1),");//6
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME = 'INSS' LIMIT 1),");//7
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME LIKE 'IR%' LIMIT 1),");//8
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME = 'CSLL' LIMIT 1),");//9
////        builder.append("CN.CNAE_CODE,");//10
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME = 'ISS' AND DATE_DELETED IS NULL LIMIT 1),");//11
////        builder.append("EMP.NAME_ENTERPRISE,");//12
////        builder.append("CG.FEDERAL_REGISTRATION,");//13
////        builder.append("CG.CITY_REGISTRATION,");//14
////        builder.append("CG.COMMERCIAL_NAME,");//15
////        builder.append("AD.ADDRESS_TYPE,");//16
////        builder.append("AD.STREET_NAME,");//17
////        builder.append("AD.NUMBER_ADDRESS,");//18
////        builder.append("AD.COMPLEMENT,");//19
////        builder.append("AD.NEIGHBORHOOD,");//20
////        builder.append("CT.CODE,");//21
////        builder.append("ST.ABBREVIATION,");//22
////        builder.append("AD.ZIPCODE,");//23
////        builder.append("EMP.PERCENT_NFE,");//24
////        builder.append("CT_EMP.CODE,");//25
////        builder.append("CT.SIAFI_CODE,");//26
////        builder.append("CT_EMP.SIAFI_CODE, ");//27
////        builder.append("CT.NAME_CITY,");//28
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME = 'ISS' LIMIT 1),");//29
////        builder.append("CT_EMP.NAME_CITY,");//30
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME = 'PIS' AND DATE_DELETED IS NULL LIMIT 1),");//31
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME = 'COFINS' AND DATE_DELETED IS NULL LIMIT 1),");//32
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME = 'INSS' AND DATE_DELETED IS NULL LIMIT 1),");//33
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME LIKE 'IR%' AND DATE_DELETED IS NULL LIMIT 1),");//34
////        builder.append("(SELECT TAXE_PERCENT FROM M0009_ENTERPRISE_TAXES WHERE TAXE_NAME = 'CSLL' AND DATE_DELETED IS NULL LIMIT 1),");//35
////        builder.append("FP.INVOICE_COMMENTS,");//36
////        builder.append("(SELECT COUNTER.LAST_NUMBER FROM M0006_COUNTERS COUNTER WHERE COUNTER.DESCRIPTION = 'LOTE' LIMIT 1),");//37
////        builder.append("EMP.IS_SIMPLE_NATIONAL,");//38
////        builder.append("SH.SHIPMENT_NUMBER,");//39
////        builder.append("SH.HOUSE_NUMBER,");//40
////        builder.append("EMP.PERCENT_NFE,");//41
////        builder.append("FP.TOTAL_INVOICE,");//42
////        builder.append("SH.MASTER_NUMBER,");//43
////        builder.append("SH.REF_CLIENT,");//44
////        builder.append("FP.INTERNAL_COMMENTS,");//45
////        builder.append("VOUCHER.VOUCHER_TYPE,");//46
////        builder.append("FP.ACCOUNTING_DATE,");//47
////        builder.append("(SELECT COUNTER.LAST_NUMBER FROM M0006_COUNTERS COUNTER WHERE COUNTER.DESCRIPTION = 'RPS' LIMIT 1),");//48
////        builder.append("CT.NAME_CITY,");//49
////        builder.append("CG.FEDERAL_REGISTRATION_TYPE,");//50
////        builder.append("FP.TOTAL_NO_TAXABLE,");//51
////        builder.append("CG.EMAIL_NFE,");//52
////        builder.append("CG.IS_DO_NOT_WITHHOLD_TAXES,");//53
////        builder.append("AD.CITY_NAME,");//54
////        builder.append("AD.STATE_NAME,");//55
////        builder.append("CY.NAME_COUNTRY,");//56
////        builder.append("FP.EXCHANGE_RATE, ");//57
////        builder.append("(SELECT TAX.UNIT_VALUE FROM M2000_FINANCIAL_TAXE TAX WHERE FINANCIAL_FK = FP.ID AND TAX.TAXE_NAME LIKE 'PIS/COFINS/CSLL' LIMIT 1), ");//58
////        builder.append("CU.SYMBOL, ");//59
////        builder.append("FR.ACCOUNTING_DATE ");//60
////
////        builder.append(" FROM M2000_FINANCIAL FP");
////        builder.append(" LEFT JOIN M0002_ENTERPRISE EMP ON EMP.ID = " + ConfiguracaoUtils.getEmpresaEmissora().getId());
////        builder.append(" LEFT JOIN M0002_ENTERPRISE_CNAE CN ON CN.ID = FP.ENTERPRISE_CNAE_FK");
////        builder.append(" LEFT JOIN M0130_CONTACT_GENERAL CG ON CG.ID = FP.CONTACT_GENERAL_FK");
////        builder.append(" LEFT JOIN M0001_ADDRESS AD ON AD.ID = CG.ADDRESS_FK");
////        builder.append(" LEFT JOIN M0001_COUNTRY CY ON CY.ID = AD.COUNTRY_FK");
////        builder.append(" LEFT JOIN M0001_CITY CT ON CT.ID = AD.CITY_FK");
////        builder.append(" LEFT JOIN M0001_STATE ST ON ST.ID = CT.STATE_FK");
////        builder.append(" LEFT JOIN M0001_ADDRESS AD_EMP ON AD_EMP.ID = EMP.ADDRESS_FK");
////        builder.append(" LEFT JOIN M0001_CITY CT_EMP ON CT_EMP.ID = AD_EMP.CITY_FK");
////        builder.append(" LEFT JOIN M2000_FINANCIAL_ITEM FI ON FI.FINANCIAL_FK = FP.ID");
////        builder.append(" LEFT JOIN M0020_SHIPMENT_HOUSE SH ON SH.ID = FI.SHIPMENT_ID");
////        builder.append(" LEFT JOIN M0007_VOUCHER VOUCHER ON VOUCHER.ID = FP.VOUCHER_FK");
////        builder.append(" LEFT JOIN M0101_CURRENCY CU ON CU.ID = FP.CURRENCY_FK");
////        builder.append(" LEFT JOIN M2000_FINANCIAL_RECEIPT FR ON FR.FINANCIAL_FK = FP.ID ");
////
////        builder.append(" WHERE FP.CODE = ?");
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, codigo);
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem.isEmpty() ? null : dadosDaListagem.get(0);
////    }
////
////    public List<Object[]> buscaItensDeUmLancamentoFinanceiro(String codigoFinanceiro) {
////        StringBuilder builder = new StringBuilder();
////
////        return geraDados(builder, null);
////    }
////
////    @Override
////    public List<Object[]> listaDadosParaEnvioRpsSP() {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT DISTINCT ");
////        builder.append("FALSE, ");
////        builder.append("FP.CODE, ");
////        builder.append("C.COMMERCIAL_NAME, ");
////        builder.append("FP.ACCOUNTING_DATE, ");
////        builder.append("FP.TOTAL_INVOICE, ");
////        builder.append("FP.TOTAL_TAXABLE, ");
////        builder.append("FP.TOTAL_PENDING, ");
////        builder.append("FP.SHIPMENT_NUMBER ");
////
////        builder.append("FROM M2000_FINANCIAL FP ");
////        builder.append("INNER JOIN M0007_VOUCHER V ON V.ID = FP.VOUCHER_FK ");
////        builder.append("INNER JOIN M0130_CONTACT_GENERAL C ON C.ID = FP.CONTACT_GENERAL_FK ");
////        builder.append("INNER JOIN M2000_FINANCIAL_ITEM FI ON FI.FINANCIAL_FK = FP.ID ");
////        if (eEmpresa("NUNO_")) {
////            builder.append("WHERE LAUNCH_TYPE = 'CHARGING' AND V.IS_CALCULATE_TAXES = TRUE AND FP.TOTAL_TAXABLE != 0 AND FI.SHIPMENT_CODE NOT LIKE '%CMI%'");
////        } else {
////            builder.append("WHERE LAUNCH_TYPE = 'CHARGING' AND FP.TOTAL_TAXABLE != 0 ");
////        }
////
////        builder.append("AND FP.EXTERNAL_NUMBER IS NULL AND FP.DATE_DELETED IS NULL ");
////        builder.append("ORDER BY FP.CODE ");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    if (i == 1) {
////                        values[i - 1] = false;
////                    } else {
////                        values[i - 1] = resultSet.getObject(i);
////                    }
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
////
////    @Override
////    public List<Object[]> buscaDadosParaDiscriminacaoRPS(String codigoFatura) {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append(" FI.DESCRIPTION, FI.TOTAL_CURRENCY_INVOICE, CU.SYMBOL ");
////        builder.append(" FROM M2000_FINANCIAL FP ");
////        builder.append(" INNER JOIN M2000_FINANCIAL_ITEM FI ON FP.ID = FI.FINANCIAL_FK ");
////        builder.append(" INNER JOIN M0101_CURRENCY CU ON CU.ID = FP.CURRENCY_FK ");
////        builder.append(" WHERE FP.CODE = ? AND FI.IS_TAXABLE = TRUE");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, codigoFatura);
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
////
////    @Override
////    public List<Object[]> buscaDadosParaCancelamentoRPS(String codigo) {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append(" FP.NFE_NUMBER, FP.VERIFICATION_CODE_NFE, EMP.CITY_REGISTRATION,EMP.FEDERAL_REGISTRATION,CT_EMP.CODE");
////        builder.append(" FROM M2000_FINANCIAL FP ");
////        builder.append(" INNER JOIN M2000_FINANCIAL_ITEM FI ON FP.ID = FI.FINANCIAL_FK ");
////        builder.append(" LEFT JOIN M0002_ENTERPRISE EMP ON EMP.ID = " + ConfiguracaoUtils.getEmpresaEmissora().getId());
////        builder.append(" LEFT JOIN M0001_ADDRESS AD_EMP ON AD_EMP.ID = EMP.ADDRESS_FK");
////        builder.append(" LEFT JOIN M0001_CITY CT_EMP ON CT_EMP.ID = AD_EMP.CITY_FK");
////
////        builder.append(" WHERE FP.CODE = ? ");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, codigo);
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
////
////    @Override
////    public void atualizarComDadosDoRpsEnviado(String idFatura, String numeroNfe, String codigoVerificacao, Integer numeroLote, String numeroRPS, String protocolo) {
////        try {
////            String query = "UPDATE M2000_FINANCIAL "
////                    + " SET EXTERNAL_NUMBER = ?,"
////                    + " VERIFICATION_CODE_NFE = ?,"
////                    + " LOTE_NUMBER_RPS = ?,"
////                    + " NFE_NUMBER = ?,"
////                    + " RPS_NUMBER = ?,"
////                    + " RPS_DATE = ?,"
////                    + " PROTOCOL = ?"
////                    + " WHERE CODE = ?";
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(query);
////            statement.setString(1, numeroNfe == null ? null : "NFE" + numeroNfe);
////            statement.setString(2, codigoVerificacao);
////            statement.setInt(3, numeroLote == null ? 0 : numeroLote);
////            statement.setString(4, numeroNfe);
////            statement.setString(5, numeroRPS);
////            statement.setDate(6, new java.sql.Date(new Date().getTime()));
////            statement.setString(7, protocolo);
////            statement.setString(8, idFatura);
////
////            statement.executeUpdate();
////
////            FabricaDeConexoes.fechaConexao(connection, statement);
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    @Override
////    public List<Object[]> listaDadosParaCancelamentoRpsSP() {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append("FP.CODE, ");
////        builder.append("C.COMMERCIAL_NAME, ");
////        builder.append("FP.ACCOUNTING_DATE, ");
////        builder.append("FP.TOTAL_INVOICE, ");
////        builder.append("FP.TOTAL_TAXABLE, ");
////        builder.append("FP.EXTERNAL_NUMBER, ");
////        builder.append("FP.SHIPMENT_NUMBER ");
////
////        builder.append(" FROM M2000_FINANCIAL FP ");
////        builder.append(" LEFT JOIN M0007_VOUCHER V ON V.ID = FP.VOUCHER_FK ");
////        builder.append(" LEFT JOIN M0130_CONTACT_GENERAL C ON C.ID = FP.CONTACT_GENERAL_FK ");
////        builder.append(" WHERE FP.NFE_NUMBER IS NOT NULL AND FP.LAUNCH_TYPE = 'CHARGING' AND (FP.IS_CANCELLED_RPS = FALSE OR FP.IS_CANCELLED_RPS IS NULL) ");
////        builder.append(" ORDER BY FP.ID DESC");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
////
////    @Override
////    public List<Object[]> buscarDadosParaListagemDeConsultaRps() {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append("FP.CODE, ");
////        builder.append("C.COMMERCIAL_NAME, ");
////        builder.append("FP.ACCOUNTING_DATE, ");
////        builder.append("FP.TOTAL_INVOICE, ");
////        builder.append("FP.TOTAL_TAXABLE, ");
////        builder.append("FP.EXTERNAL_NUMBER, ");
////        builder.append("FP.SHIPMENT_NUMBER, ");
////        builder.append("FP.RPS_NUMBER, ");
////        builder.append("FP.PROTOCOL ");
////
////        builder.append(" FROM M2000_FINANCIAL FP ");
////        builder.append(" LEFT JOIN M0007_VOUCHER V ON V.ID = FP.VOUCHER_FK ");
////        builder.append(" LEFT JOIN M0130_CONTACT_GENERAL C ON C.ID = FP.CONTACT_GENERAL_FK ");
////        builder.append(" WHERE FP.NFE_NUMBER IS NOT NULL ");
////        builder.append(" OR FP.PROTOCOL IS NOT NULL");
////        builder.append(" ORDER BY FP.ID DESC");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
////
////    @Override
////    public Object[] buscarDadosParaConsultaNFe(String codigoFatura) {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append("EMP.FEDERAL_REGISTRATION,");
////        builder.append("FP.VERIFICATION_CODE_NFE ,");
////        builder.append("FP.NFE_NUMBER,");
////        builder.append("EMP.CITY_REGISTRATION,");
////        builder.append("FP.CODE,");
////        builder.append("FP.PROTOCOL ");
////        builder.append(" FROM M2000_FINANCIAL FP");
////        builder.append(" LEFT JOIN M0002_ENTERPRISE EMP ON EMP.ID = " + ConfiguracaoUtils.getEmpresaEmissora().getId());
////        builder.append(" LEFT JOIN M0130_CONTACT_GENERAL CG ON CG.ID = FP.CONTACT_GENERAL_FK");
////        builder.append(" WHERE FP.CODE = ?");
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, codigoFatura);
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return dadosDaListagem.get(0);
////    }
////
////    public List<Object[]> geraDados(StringBuilder query, Map<Class, Object> parameters) {
////        int indexParameter = 1;
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(query.toString());
////            if (parameters != null) {
////                for (Map.Entry<Class, Object> entry : parameters.entrySet()) {
////
////                    if (entry.getKey().equals(String.class)) {
////                        statement.setString(indexParameter, (String) entry.getValue());
////                    } else if (entry.getKey().equals(Integer.class)) {
////                        statement.setInt(indexParameter, (int) entry.getValue());
////                    }
////
////                    indexParameter++;
////                }
////            }
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return dadosDaListagem;
////    }
////
////    @Override
////    public List<Object[]> buscarListaDeItensFinanceiroAPartirDoCodigo(String codigoFinanceiro) {
////        StringBuilder builder = new StringBuilder();
////        builder.append("SELECT ");
////        builder.append("FI.DESCRIPTION,");
////        builder.append("FI.QUANTITY,");
////        builder.append("FI.UNIT_VALUE,");
////        builder.append("FI.TOTAL");
////        builder.append(" FROM M2000_FINANCIAL_ITEM FI");
////        builder.append(" INNER JOIN M2000_FINANCIAL FP ON FP.ID = FI.FINANCIAL_FK ");
////        builder.append(" WHERE FP.CODE IS NOT NULL");
////
////        return geraDados(builder, null);
////    }
////
////    public int buscarContadorDoRPS() {
////        StringBuilder builder = new StringBuilder();
////        int result = 0;
////        builder.append(" SELECT ");
////        builder.append(" LAST_NUMBER");
////        builder.append(" FROM M0006_COUNTERS");
////        builder.append(" WHERE DESCRIPTION = 'RPS'");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            resultSet = statement.executeQuery();
////
////            if (resultSet.next()) {
////                result = resultSet.getInt(1);
////            }
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return result;
////    }
////
////    @Override
////    public void atualizarContadorDoRPS(int numeroDeRpsEnviados) {
////        int contador = buscarContadorDoRPS();
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("UPDATE ");
////        builder.append(" M0006_COUNTERS SET LAST_NUMBER = ?");
////        builder.append(" WHERE DESCRIPTION = 'RPS'");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setInt(1, contador + numeroDeRpsEnviados);
////            statement.executeUpdate();
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    @Override
////    public boolean eEmpresa(String nomeDaEmpresa) {
////        StringBuilder builder = new StringBuilder();
////        boolean result = false;
////        builder.append("SELECT ");
////        builder.append("ID FROM FWSEC_SUBSCRIPTION WHERE REPORT_NAME = ?");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            statement.setString(1, nomeDaEmpresa);
////            resultSet = statement.executeQuery();
////
////            if (resultSet.next()) {
////                result = true;
////            }
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return result;
////    }
////
////    @Override
////    public List<Object[]> buscarNotasNaoProcessadas() {
////        StringBuilder builder = new StringBuilder();
////
////        builder.append("SELECT ");
////        builder.append("FP.RPS_NUMBER, ");
////        builder.append("FP.PROTOCOL ");
////
////        builder.append(" FROM M2000_FINANCIAL FP ");
////        builder.append(" LEFT JOIN M0007_VOUCHER V ON V.ID = FP.VOUCHER_FK ");
////        builder.append(" LEFT JOIN M0130_CONTACT_GENERAL C ON C.ID = FP.CONTACT_GENERAL_FK ");
////        builder.append(" WHERE FP.NFE_NUMBER IS NULL ");
////        builder.append(" AND FP.PROTOCOL IS NOT NULL");
////        builder.append(" ORDER BY FP.ID DESC");
////
////        try {
////            connection = FabricaDeConexoes.getConexao();
////            statement = connection.prepareStatement(builder.toString());
////            resultSet = statement.executeQuery();
////            resultSetMetaData = resultSet.getMetaData();
////            columnCount = resultSetMetaData.getColumnCount();
////            dadosDaListagem = new ArrayList<>();
////
////            while (resultSet.next()) {
////                Object[] values = new Object[columnCount];
////                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
////                    values[i - 1] = resultSet.getObject(i);
////                }
////                dadosDaListagem.add(values);
////            }
////
////            FabricaDeConexoes.fechaConexao(connection, statement, resultSet);
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////        return dadosDaListagem;
////    }
//}
