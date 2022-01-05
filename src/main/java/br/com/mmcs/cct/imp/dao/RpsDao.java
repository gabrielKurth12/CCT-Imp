package br.com.mmcs.cct.imp.dao;

import java.util.List;

/**
 *
 * @author Gabriel Rosa
 */
public interface RpsDao {

    public List<Object[]> buscarDadosParaConhecimentoDeCarga();

//    public Object[] buscaDadosParaGerarXmlRPS(String codigo);
//
//    public List<Object[]> buscaDadosParaCancelamentoRPS(String codigo);
//
//    public List<Object[]> listaDadosParaEnvioRpsSP();
//
//    public List<Object[]> listaDadosParaCancelamentoRpsSP();
//
//    public List<Object[]> buscaDadosParaDiscriminacaoRPS(String codigoFatura);
//
//    public void atualizarComDadosDoRpsEnviado(String idFatura, String numeroNfe, String codigoVerificacao, Integer numeroLote, String numeroRPS,String protocolo);
//
//    public List<Object[]> buscarDadosParaListagemDeConsultaRps();
//
//    public Object[] buscarDadosParaConsultaNFe(String codigoFatura);
//
//    public List<Object[]> buscarListaDeItensFinanceiroAPartirDoCodigo(String codigoFinanceiro);
//
//    public void atualizarContadorDoRPS(int numeroDeRpsEnviados);
//    
//    public boolean eEmpresa(String nomeDaEmpresa);
//    
//    public List<Object[]> buscarNotasNaoProcessadas();
}
