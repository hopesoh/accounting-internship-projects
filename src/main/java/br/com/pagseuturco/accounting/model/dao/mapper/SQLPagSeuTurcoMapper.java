package br.com.pagseuturco.accounting.model.dao.mapper;

import br.com.pagseuturco.accounting.model.domain.Turnover;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface SQLPagSeuTurcoMapper {

    PreparedStatement createInsertPagSeuTurcoPreparedStatement(Connection connect, Turnover turnover);

}
