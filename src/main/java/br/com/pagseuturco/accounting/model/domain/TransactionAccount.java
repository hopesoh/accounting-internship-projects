package br.com.pagseuturco.accounting.model.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionAccount {

    private static final String SEMICOLON = ";";
    private static final String BOOKLET_HEADER = "numero_documento; nome_emissor;identificacao_emissor;valor_documento;conta;data_recebimento";
    private static final String GENNERICCARD_HEADER = "hash_cartao;tipo_transacao;valor;conta;data_transacao";
    private static final String TRANSFER_HEADER = "tipo;valor;conta;data_transacao";

    public List<Turnover> processFile(List<String> fileIntoList) throws IOException, SQLException, ClassNotFoundException {

        String turnoverType = identifyTurnoverByType(fileIntoList.get(0));
        List<Turnover> turnoverList = transformIntoTurnoverList(turnoverType, fileIntoList);

        return turnoverList;
    }

    public ArrayList<String> transformFileIntoList(Reader reader) throws IOException {

        String fileLine = null;
        ArrayList<String> turnoverArrayList = new ArrayList<String>();

        while ((fileLine = ((BufferedReader)reader).readLine()) != null) {
            turnoverArrayList.add(fileLine);
        }

        return turnoverArrayList;
    }

    public String identifyTurnoverByType(String header) {
        switch (header) {
            case BOOKLET_HEADER:
                return "BOOKLET";
            case GENNERICCARD_HEADER:
                return "GENNERICCARD";
            case TRANSFER_HEADER:
                return "TRANSFER";
            default:
                return null;
        }
    }

    public ArrayList<Turnover> transformIntoTurnoverList(String turnoverType, List<String> fileLines) throws IOException, SQLException, ClassNotFoundException {


        ArrayList<Turnover> turnoverArrayList = new ArrayList<Turnover>();

        for (int index=1; index < fileLines.size(); index++ ) {

            String fileLine = fileLines.get(index);
            String[] splittedLine = fileLine.split(SEMICOLON, -1);

            if (splittedLine.length == 0) {
                return null;
            }

            FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
            Turnover financialTurnover = financialTurnoverFactory.build(turnoverType, splittedLine);
            turnoverArrayList.add(financialTurnover);
        }

        return turnoverArrayList;
    }
}
