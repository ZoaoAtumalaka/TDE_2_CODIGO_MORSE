// =====================================================================================================
// TDE DE RESOLUÇÃO DE PROBLEMAS -> CODIFICADOR/DECODIFICADOR DE CÓDIGO MORSE
// ALUNOS: JOÃO KAUDY, GUSTAVO GAWLAK, LUCAS RETZLAFF
// PROFESSOR: ARAMIS
// =====================================================================================================

import java.util.Scanner;

// =====================================================================================================

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Arvore arvore = new Arvore();

        System.out.println("========================================================");
        System.out.println("CODIFICADOR E DECODIGICADOR DE CÓDIGO MORSE USANDO ÁRVORE BINÁRIA");
        System.out.println("========================================================");
        System.out.println("Digite uma opção:");
        System.out.println("1. Codificar uma frase");
        System.out.println("2. Decodificar uma frase");
        System.out.println("========================================================");

        int A = scanner.nextInt();
        scanner.nextLine();

        switch (A) {
            case 1:
                System.out.println("Digite a sua frase ou palavra a ser codificada");
                String B = scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite a sua frase ou palavra a ser decodificada");
                String C = scanner.nextLine();
                arvore.decodificar(C);
                break;
        }

        scanner.close();

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
    }

    public void decodificar(String frase_decodificar){

        String frase_traduzida = "";
        No no_atual = raiz;

        // Bloco que percorre o código para encontrar o melhor valor
        for(int i = 0; i < frase_decodificar.length(); i++) {

            char caractere = frase_decodificar.charAt(i);

            if (caractere == '.') {

                // Verifica a validade do codigo morse (esquerda)
                if(no_atual.proximoEsquerda == null){
                    System.out.println("O codigo morse é inválido! Tente novamente");
                    return;
                } else {
                    no_atual = no_atual.proximoEsquerda;
                }

            } else if (caractere == '-') {

                // Verifica a validade do codigo morse (direita)
                if(no_atual.proximoDireita == null){
                    System.out.println("O codigo morse é inválido! Tente novamente");
                    return;
                } else {
                    no_atual = no_atual.proximoDireita;
                }

            } else if(caractere == ' '){

                // Verifica se o nó atual é a raiz, se for, continua, se não, adiciona o valor do nó atual e volta pra raiz.
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
                return;

            }

        }

        if (no_atual != raiz) {
            frase_traduzida += no_atual.valor;
        }

        System.out.println("Frase traduziada: " + frase_traduzida);

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