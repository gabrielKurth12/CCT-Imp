package br.com.mmcs.cct.imp.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Rosa
 */
public class FabricaDeConexoes {

    /**
     * MMCS
     */
    //Dados para a conexão com o banco
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "atlantis";
//    private static final String DATABASE = "cheetahwebnuno?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=America/Sao_Paulo";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.0.32:3306/";
    /**
     * NUNO
     *
     * @return
     */
//    private static final String USUARIO = "cheetah";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "cheetahweb";
//    private static final String STR_CONEXAO = "jdbc:mysql://177.54.150.120:3306/";
//
    /**
     * GP Externo
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://atlantis.gpcargo.com.br:53306/";
    /**
     * GP Interno
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.180.205:3306/";
    /**
     * SHIPMASTER
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=America/Sao_Paulo";
//    private static final String STR_CONEXAO = "jdbc:mysql://shipmasters.no-ip.info:3306/";
    /**
     * EASY WAY
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://128.201.72.207:3306/";
//
    /**
     * MM Global
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://cloudmmglobal.supplyhosting.com.br:3306/";
    /**
     * MM Global interno
     *
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "atlantis";
//    private static final String DATABASE = "cheetahwebgp";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.0.32:3306/";
    /**
     * PCM
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.0.215:3306/";
//    
    /**
     * FGL
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";;
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://177.139.163.80:43306/";
    /**
     * GOTRANS Externo
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://saopaulo.go-trans.com.br:3306/";
    /**
     * GOTRANS Interno
     *
     * @return
     */
    //private static final String USUARIO = "atlantis";
    //private static final String SENHA = "qpalzm88*";
    //private static final String DATABASE = "atlantis";
    //private static final String STR_CONEXAO = "jdbc:mysql://192.168.1.15:3306/";
    /**
     * Portco Interno
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.1.10:3306/";
    /**
     * OneWay interno
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "atlantis";
//    private static final String DATABASE = "cheetahwebone";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.0.32:3306/";
    /**
     * OneWay
     *
     * @return
     */
    private static final String USUARIO = "atlantis";
    private static final String SENHA = "qpalzm88*";
    private static final String DATABASE = "atlantis";
    private static final String STR_CONEXAO = "jdbc:mysql://192.168.1.218:3306/";

    /**
     * Interno teste
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "atlantis";
//    private static final String DATABASE = "cheetahwebone";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.0.32:3306/";
    /**
     * Connection
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://cloudconnectioncargo.supplyhosting.com.br:3306/";
    /**
     * GP Novo Server
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://atlantis.gpcargo.com.br:3306/";
    /**
     * ICS
     *
     * @return
     */
//    private static final String USUARIO = "ics";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantisics";
//    private static final String STR_CONEXAO = "jdbc:mysql://cloudics.supplyhosting.com.br:3306/";
    /**
     * FGL Novo Server teste
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "basefgl";
//    private static final String STR_CONEXAO = "jdbc:mysql://177.139.163.80:63306/";
//    /**
//     * Brachmann
//     *
//     * @return
//     */
//    private static final String USUARIO = "brachmann";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantisbrachmann";
//    private static final String STR_CONEXAO = "jdbc:mysql://cloudbrachmann.supplyhosting.com.br:3306/";
    /**
     * AramTrust
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "@ram20T";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://ec2-18-230-140-130.sa-east-1.compute.amazonaws.com:3306/";
    /**
     * Horizonte
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://horizontecargo.ddns.net:3306/";
    /**
     * Horizonte Interno
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://192.168.1.12:3306/";
    /**
     * Alltrans
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://200.98.80.114:3306/";
    /**
     * QS
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://vpn.quattror.com.br:33069/";
    /**
     * Master
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://128.201.72.55:3306/";
    /**
     * Wind
     *
     * @return
     */
//    private static final String USUARIO = "atlantis";
//    private static final String SENHA = "qpalzm88*";
//    private static final String DATABASE = "atlantis";
//    private static final String STR_CONEXAO = "jdbc:mysql://179.191.66.214:3306/";
    public static Connection getConexao() {
        try {
            Connection con = DriverManager.getConnection(
                    STR_CONEXAO + DATABASE,
                    USUARIO,
                    SENHA);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(FabricaDeConexoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void fecharConexao(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Fechada a conexão com o banco de dados");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar a conexão com o banco de dados " + e.getMessage());
        }
    }

    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        try {
            if (conn != null) {
                fecharConexao(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn != null || stmt != null) {
                fecharConexao(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }

}
