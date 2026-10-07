// =====================================================================================================
// PARTE 1 DO TDE DE RESOLUÇÃO DE PROBLEMAS -> CODIFICADOR/DECODIFICADOR DE CÓDIGO MORSE
// ALUNOS: JOÃO KAUDY, GUSTAVO GAWLAK, LUCAS RETZLAFF
// PROFESSOR: ARAMIS
// =====================================================================================================

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

// =====================================================================================================

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Arvore arvore = new Arvore();

        while(true){

            System.out.println("========================================================");
            System.out.println("CODIFICADOR E DECODIGICADOR DE CÓDIGO MORSE USANDO ÁRVORE BINÁRIA");
            System.out.println("========================================================");
            System.out.println("Digite uma opção:");
            System.out.println("1. Codificar uma frase");
            System.out.println("2. Decodificar uma frase");
            System.out.println("3. Codificar um arquivo de texto");
            System.out.println("4. Decodifciar um arquivo de texto");
            System.out.println("5. Exibir a árvore");
            System.out.println("========================================================");

            int A = scanner.nextInt();
            scanner.nextLine();

            switch (A) {
                case 1: //CODIFICAR UMA FRASE OU PALABRA
                    System.out.println("Digite a sua frase ou palavra a ser codificada");
                    String B = scanner.nextLine();
                    System.out.println("Frase Codificada: " + arvore.codificar(B));
                    break;

                case 2: //DECODIFICAR UMA FRASE OU PALAVRA
                    System.out.println("Digite a sua frase ou palavra a ser decodificada");
                    System.out.println("==================================");
                    System.out.println("• Digite APENAS os caracteres: '.', '-', ' ' e '/'");
                    System.out.println("• Use ESPAÇO entre cada LETRA");
                    System.out.println("• Use BARRA ('/') entre cada PALAVRA");
                    System.out.println("Exemplo: ... --- ... / --- .-.. .-  (SOS OLA)");
                    System.out.println("==================================");
                    String C = scanner.nextLine();
                    System.out.println("Frase Decodificada: " + arvore.decodificar(C));
                    break;

                case 3: //CODIFICAR ARQUIVO INTEIRO DE TEXTO
                    System.out.println("Digite o caminho completo do arquivo (Ex: /home/joao/texto.txt):");
                    String caminhoEntrada = scanner.nextLine();

                    try{
                        File arquivoEntrada = new File(caminhoEntrada);
                        Scanner leitorDeArquivo = new Scanner(arquivoEntrada);

                        String caminhoSaida = caminhoEntrada.replace(".txt", "_codificado.txt");
                        FileWriter escritor = new FileWriter(caminhoSaida);

                        // A ESTEIRA: Enquanto houver uma próxima linha no arquivo original...
                        while (leitorDeArquivo.hasNextLine()) {
                            String linhaOriginal = leitorDeArquivo.nextLine(); // Pega a linha

                            // Só traduz se a linha não for vazia
                            if (!linhaOriginal.isEmpty()) {
                                String linhaMorse = arvore.codificar(linhaOriginal); // Traduz
                                escritor.write(linhaMorse + "\n"); // Escreve no novo arquivo e pula uma linha (\n)
                            } else {
                                escritor.write("\n"); // Se a linha original for vazia, mantém o espaço vazio no novo arquivo
                            }
                        }

                        leitorDeArquivo.close(); // Desliga a esteira de leitura
                        escritor.close(); // Fecha o arquivo salvo

                        System.out.println("Sucesso absoluto! Arquivo salvo em: " + caminhoSaida);

                    } catch(Exception e){
                        System.out.println("Erro: Não achei o arquivo. Tem certeza que o caminho '" + caminhoEntrada + "' está certo?");
                    }
                    break;

                case 4: //DECODIFICAR ARQUIVO INTEIRO DE TEXTO
                    System.out.println("Digite o caminho completo do arquivo (Ex: /home/joao/texto.txt):");
                    String caminhoEntrada2 = scanner.nextLine();

                    try {
                        File arquivoEntrada = new File(caminhoEntrada2);
                        Scanner leitorDeArquivo = new Scanner(arquivoEntrada);

                        String caminhoSaida = caminhoEntrada2.replace(".txt", "_decodificado.txt");
                        FileWriter escritor = new FileWriter(caminhoSaida);

                        // A ESTEIRA: Enquanto houver uma próxima linha no arquivo em Morse...
                        while (leitorDeArquivo.hasNextLine()) {
                            String linhaOriginal = leitorDeArquivo.nextLine();

                            if (!linhaOriginal.isEmpty()) {
                                String linhaTraduzida = arvore.decodificar(linhaOriginal); // TRADUZ DE VOLTA
                                escritor.write(linhaTraduzida + "\n");
                            } else {
                                escritor.write("\n");
                            }
                        }

                        leitorDeArquivo.close();
                        escritor.close();

                        System.out.println("Sucesso absoluto! Arquivo salvo em: " + caminhoSaida);

                    } catch(Exception e){
                        System.out.println("Erro: Não achei o arquivo. Tem certeza que o caminho '" + caminhoEntrada2 + "' está certo?");
                    }
                    break;

                case 5: 
                    arvore.exibir();
                    break;

                default://RESPOSTA RUIM
                    System.out.println("Opção Inválida! Tente novamente");
            }

        }

    }
}

// =====================================================================================================

class Arvore {

    //ATRIBUTOS
    public No raiz;

    //CONSTRUTOR
    public Arvore(){
        raiz = new No(' ');
        popularArvore();
    }

    //FUNCOES
    public void inserir(String codigo, char letra){
        No no_atual = raiz;

        for(int i = 0; i < codigo.length(); i++){ //for q serve para percorrer o código morse digitado

            char caractere = codigo.charAt(i);

            if(caractere == '.'){

                //verifica se é nulo/vazio, se for, cria um novo nó e coloca na esquerda, mesmo se nao for, o nó atual vira o no da esquerda
                if(no_atual.proximoEsquerda == null){
                    No no_novo = new No(' ');
                    no_atual.proximoEsquerda = no_novo;
                }
                no_atual = no_atual.proximoEsquerda;

            } else if (caractere == '-') {

                //msm coisa so que na direita
                if(no_atual.proximoDireita == null){
                    No no_novo = new No(' ');
                    no_atual.proximoDireita = no_novo;
                }
                no_atual = no_atual.proximoDireita;

            }

        }

        no_atual.valor = letra;
    }

    private void popularArvore() {
        // Letras
        inserir(".-", 'A');
        inserir("-...", 'B');
        inserir("-.-.", 'C');
        inserir("-..", 'D');
        inserir(".", 'E');
        inserir("..-.", 'F');
        inserir("--.", 'G');
        inserir("....", 'H');
        inserir("..", 'I');
        inserir(".---", 'J');
        inserir("-.-", 'K');
        inserir(".-..", 'L');
        inserir("--", 'M');
        inserir("-.", 'N');
        inserir("---", 'O');
        inserir(".--.", 'P');
        inserir("--.-", 'Q');
        inserir(".-.", 'R');
        inserir("...", 'S');
        inserir("-", 'T');
        inserir("..-", 'U');
        inserir("...-", 'V');
        inserir(".--", 'W');
        inserir("-..-", 'X');
        inserir("-.--", 'Y');
        inserir("--..", 'Z');

        // Números
        inserir(".----", '1');
        inserir("..---", '2');
        inserir("...--", '3');
        inserir("....-", '4');
        inserir(".....", '5');
        inserir("-....", '6');
        inserir("--...", '7');
        inserir("---..", '8');
        inserir("----.", '9');
        inserir("-----", '0');

        //OUTRAS COISAS
        inserir("--..--", ',');
        inserir(".-.-.-", '.');
        inserir("..--..", '?');
        inserir(".----.", '\'');
    }

    public String decodificar(String frase_decodificar){

        String frase_traduzida = "";
        No no_atual = raiz;

        //percorredor profissional de codigo morse capaz de identificar o melhor valor
        for(int i = 0; i < frase_decodificar.length(); i++) {

            char caractere = frase_decodificar.charAt(i);

            if (caractere == '.') {

                //verificando se o codigo é valido mesmo (tortao pra esquerda)
                if(no_atual.proximoEsquerda == null){
                    System.out.println("O codigo morse é inválido! Tente novamente");
                    return "";
                } else {
                    no_atual = no_atual.proximoEsquerda;
                }

            } else if (caractere == '-') {

                //verificando se o codigo é valido mesmo (tortao pra direita)
                if(no_atual.proximoDireita == null){
                    System.out.println("O codigo morse é inválido! Tente novamente");
                    return "";
                } else {
                    no_atual = no_atual.proximoDireita;
                }

            } else if(caractere == ' '){

                //confere se o nó anterior é raiz, pq se for ele vai acabar colocando outro espaço desnecessário
                if(no_atual == raiz){
                    continue;
                } else {
                    frase_traduzida += no_atual.valor;
                    no_atual = raiz;
                }

            } else if(caractere =='/'){
                frase_traduzida += " ";
                no_atual = raiz;
            } else {
                System.out.println("Insira um código morse válido...");
                return "";
            }

        }

        if (no_atual != raiz) {
            frase_traduzida += no_atual.valor;
        }

        return frase_traduzida;

    }

    public String codificar(String frase_codificar){

        String[][] matrizM = {
                // Letras
                {".-", "A"}, {"-...", "B"}, {"-.-.", "C"}, {"-..", "D"}, {".", "E"},
                {"..-.", "F"}, {"--.", "G"}, {"....", "H"}, {"..", "I"}, {".---", "J"},
                {"-.-", "K"}, {".-..", "L"}, {"--", "M"}, {"-.", "N"}, {"---", "O"},
                {".--.", "P"}, {"--.-", "Q"}, {".-.", "R"}, {"...", "S"}, {"-", "T"},
                {"..-", "U"}, {"...-", "V"}, {".--", "W"}, {"-..-", "X"}, {"-.--", "Y"},
                {"--..", "Z"},

                // Números
                {".----", "1"}, {"..---", "2"}, {"...--", "3"}, {"....-", "4"}, {".....", "5"},
                {"-....", "6"}, {"--...", "7"}, {"---..", "8"}, {"----.", "9"}, {"-----", "0"},

                //OUTRAS COISAS
                {"--..--", ","}, {".-.-.-", "."}, {"..--..", "?"}, {".----.", "'"}
        };

        String frase_codificada = "";
        char[] F = frase_codificar.toUpperCase().toCharArray();

        for(int i = 0; i < F.length; i++) {//andar pela frase codificada

            if(F[i] == ' ') {
                frase_codificada += "/ ";
                continue;
            }

            boolean encontrou = false;

            for(int x = 0; x < matrizM.length; x++){ //andar pela matriz

                if( F[i] == matrizM[x][1].toCharArray()[0]) {
                    frase_codificada += matrizM[x][0];
                    frase_codificada += " ";
                    encontrou=true;
                    break;
                }

            }

            if(encontrou == false){
                System.out.println("ERRO! Caractere Inválido... Tente Novamente bananão");
                return "";
            }

        }

        return frase_codificada;

    }

    public void exibir() {
        System.out.println("(raiz)");
        exibirNo(raiz.proximoEsquerda, "", raiz.proximoDireita == null, '.');
        exibirNo(raiz.proximoDireita, "", true, '-');
    }

    private void exibirNo(No no, String prefixo, boolean ultimo, char aresta) {
        if (no == null) return;

        String letra = (no.valor == ' ') ? "*" : String.valueOf(no.valor);

        System.out.println(prefixo + (ultimo ? "└── " : "├── ") + aresta + " " + letra);

        String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");
        exibirNo(no.proximoEsquerda, novoPrefixo, no.proximoDireita == null, '.');
        exibirNo(no.proximoDireita, novoPrefixo, true, '-');
    }
}

// =====================================================================================================

class No {

    // ATRIBUTOS
    public char valor;
    public No proximoEsquerda; //vai ser o ponto (.)
    public No proximoDireita; //vai ser o traco (-)

    // CONSTRUTOR
    public No(char valor) {
        this.valor = valor;
        this.proximoEsquerda = null;
        this.proximoDireita = null;
    }

}

// =====================================================================================================