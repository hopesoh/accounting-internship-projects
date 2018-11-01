package br.com.pagseuturco.accounting.view.init;

import br.com.pagseuturco.accounting.view.file.FileReader;

import java.io.Reader;
import java.io.StringReader;

public class MockFileReader extends FileReader {

    public Reader readFile() {
        return new StringReader( "tipo;valor;conta;data_transacao\n" +
                "DEBITO;130.55;101;15/08/2018\n" +
                "CREDITO;130.55;102;15/08/2018\n" +
                "DEBITO;10.95;102;15/08/2018\n" +
                "CREDITO;10.95;103;15/08/2018\n" +
                "DEBITO;150.50;103;15/08/2018\n" +
                "CREDITO;150.50;102;15/08/2018\n" +
                "DEBITO;300.00;103;15/08/2018\n" +
                "CREDITO;20.55;101;15/08/2018\n" +
                "DEBITO;20.55;102;15/08/2018\n" +
                "CREDITO;1.10;102;15/08/2018\n" +
                "DEBITO;1.10;101;15/08/2018\n" +
                "CREDITO;22.45;103;15/08/2018\n" +
                "DEBITO;22.45;102;15/08/2018\n" +
                "CREDITO;72.35;101;15/08/2018\n" +
                "DEBITO;72.35;103;15/08/2018\n" +
                "CREDITO;111.22;103;15/08/2018\n" +
                "DEBITO;111.22;102;15/08/2018\n" +
                "CREDITO;145.35;103;15/08/2018\n" +
                "DEBITO;145.35;102;15/08/2018\n" +
                "CREDITO;13.50;101;15/08/2018\n" +
                "DEBITO;13.50;102;15/08/2018\n"
        );
    }

}
