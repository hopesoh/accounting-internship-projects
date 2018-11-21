package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Mapeador tabela de banco de dados x Classes;
 *
 */
public interface SQLMapper {

    PreparedStatement createInsertPreparedStatement(Connection connect, Turnover turnover);

    List<Turnover> createFindAllStatement(Connection connection);

}
