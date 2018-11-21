package br.com.pagseuturco.accounting.model.domain;

public interface BookletType extends ProfitInserter {
    String getDocumentNumber();

    String getName();

    String getIdentification();

}
