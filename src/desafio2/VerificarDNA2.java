package desafio2;

import desafio2.arquivosDNA.CriarArquivo;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VerificarDNA2 {
    public static void CriarArquivo(String caminho, String nome, String conteudo) {
        String nomeArquivo = caminho + nome;
        System.out.println(nomeArquivo);
        try {
            // Cria um objeto File
            File arquivo = new File(nomeArquivo);

            // Cria o arquivo (se não existir)
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado com sucesso: " + arquivo.getName());
            } else {
                System.out.println("O arquivo já existe.");
            }

            // Escreve no arquivo
            FileWriter fileWriter = new FileWriter(arquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(conteudo);

            printWriter.close();
            fileWriter.close();

            System.out.println("Dados escritos no arquivo com sucesso.");

        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String[] arquivos = new String[10];
        for (int i = 0; i < arquivos.length; i++) {
            arquivos[i] = "src/desafio2/arquivosDNA/dna-" + (i) + ".txt";
        }

        for (String nomeArquivo : arquivos) {
            System.out.println("\n📂 Lendo: " + nomeArquivo);

            try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
                String linha;
                int nLinha = 1;
                String conteudo = "";
                while ((linha = br.readLine()) != null) {
                    String seq = linha.trim().toUpperCase();
                    StringBuilder motivo = new StringBuilder();

                    nLinha++;

                    for (int i = 0; i < linha.length(); i++) {
                        char b = linha.charAt(i);
                        System.out.println(linha.length());
                        System.out.println(i);
                        if (b != 'A' && b != 'T' && b != 'C' && b != 'G') {
                            conteudo += "Fita inválida | Original: " + linha + "\n";
                            System.out.println("invalido");
                        } else {
                            System.out.println("InvalidoElse");
                            for (int j = 0; j < linha.length(); j++) {
                                switch (b) {
                                    case 'A':
                                        motivo.append('T');
                                        break;
                                    case 'T':
                                        motivo.append('A');
                                        break;
                                    case 'C':
                                        motivo.append('G');
                                        break;
                                    case 'G':
                                        motivo.append('C');
                                        break;
                                }
                            }

                            conteudo += linha + "\n";

                        }
                    }
                    conteudo += linha + "\n";
                }
                System.out.println("Conteudo: "+ conteudo);
                File nome = new File(nomeArquivo);
                CriarArquivo("src/desafio2/arquivosCriados/", nome.getName(), conteudo);
            } catch (IOException e) {
                System.out.println("Erro ao ler " + nomeArquivo + ": " + e.getMessage());
            }

        }
    }

    // 1) Checagem completa
    static boolean sequenciaValida(String seq, StringBuilder motivo) {
        if (seq.isEmpty()) {
            motivo.append("linha vazia");
            return false;
        }
        // Só A/T/C/G
        for (int i = 0; i < seq.length(); i++) {
            char b = seq.charAt(i);
            if (b != 'A' && b != 'T' && b != 'C' && b != 'G') {
                motivo.append("caractere inválido '").append(b).append("' na posição ").append(i + 1);
                return false;
            }
        }
        // Balanço A↔T e C↔G
        int a = 0, t = 0, c = 0, g = 0;
        for (int i = 0; i < seq.length(); i++) {
            switch (seq.charAt(i)) {
                case 'A':
                    a++;
                    break;
                case 'T':
                    t++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'G':
                    g++;
                    break;
            }
        }
        if (a != t || c != g) {
            motivo.append("desbalanceada: A=").append(a).append(" != T=").append(t)
                    .append(" ou C=").append(c).append(" != G=").append(g);
            return false;
        }
        return true;
    }

    // 2) Complementar (A↔T, C↔G). Se quiser o REVERSE-COMPLEMENT, faça reverse() no final.
    static String complementar(String seq) {
        StringBuilder sb = new StringBuilder(seq.length());
        for (int i = 0; i < seq.length(); i++) {
            char b = seq.charAt(i);
            switch (b) {
                case 'A':
                    sb.append('T');
                    break;
                case 'T':
                    sb.append('A');
                    break;
                case 'C':
                    sb.append('G');
                    break;
                case 'G':
                    sb.append('C');
                    break;
            }
        }
        return sb.toString();
    }
}