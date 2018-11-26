package br.com.pagseuturco.accounting.model.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TransactionAccount {

    private static final String SEMICOLON = ";";
    private static final String BOOKLET_HEADER = "numero_documento; nome_emissor;identificacao_emissor;valor_documento;conta;data_recebimento";
    private static final String GENNERICCARD_HEADER = "hash_cartao;tipo_transacao;valor;conta;data_transacao";
    private static final String TRANSFER_HEADER = "tipo;valor;conta;data_transacao";

    public List<Turnover> processFile(List<String> fileIntoList) {
        String fileType = identifyFileByType(fileIntoList.get(0));
        return transformFileIntoTurnoverList(fileType, fileIntoList);
    }

    public ArrayList<String> transformFileIntoList(Reader reader) throws IOException {
        String fileLine;
        ArrayList<String> turnoverArrayList = new ArrayList<String>();

        while ((fileLine = ((BufferedReader)reader).readLine()) != null) {
            turnoverArrayList.add(fileLine);
        }

        return turnoverArrayList;
    }

    public String identifyFileByType(String header) {
        switch (header) {
            case BOOKLET_HEADER:
                return "BOOKLET";
            case GENNERICCARD_HEADER:
                return "GENNERICCARD";
            case TRANSFER_HEADER:
                return "TRANSFER";
            default:
                throw new IllegalArgumentException();
        }
    }

    public ArrayList<Turnover> transformFileIntoTurnoverList(String fileType, List<String> fileLines) {


        ArrayList<Turnover> turnoverArrayList = new ArrayList<>();

        for(int turnoverIndex=1; turnoverIndex < fileLines.size(); turnoverIndex++ ) {

            try {
                String fileLine = fileLines.get(turnoverIndex);
                String[] splittedLine = fileLine.split(SEMICOLON, -1);

                FinancialTurnoverFactory financialTurnoverFactory = new FinancialTurnoverFactory();
                Turnover financialTurnover = financialTurnoverFactory.build(fileType, splittedLine);
                turnoverArrayList.add(financialTurnover);
            } catch (Exception ex) {
                throw new NullPointerException();
            }
        }

        return turnoverArrayList;
    }
}
