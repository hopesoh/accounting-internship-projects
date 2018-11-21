package br.com.pagseuturco.accounting.model.domain;

import br.com.pagseuturco.accounting.model.dao.mapper.SQLPagSeuTurcoMapper;

/**
 * Realiza a inserção nas tabelas de registros de lucro.
 *
 */
public interface ProfitInserter {

    SQLPagSeuTurcoMapper getSQLPagSeuTurcoMapper();

}
