package desafio2;

public class main {
    public static void main(String[] args) {

        final int QNT_ARQUIVOS = 10;
        String[] nomeArquivos = new String[QNT_ARQUIVOS];

        for(int i = 0; i < QNT_ARQUIVOS; i++){
            nomeArquivos[i] = "dna-"+i+".txt";
        }

        for(int i = 0; i < QNT_ARQUIVOS; i++){
            System.out.println(nomeArquivos[i]);
        }

    }
}
