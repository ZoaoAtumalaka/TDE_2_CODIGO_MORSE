import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================================");
        System.out.println("CODIFICADOR E DECODIGICADOR DE CÓDIGO MORSE USANDO ÁRVORE BINÁRIA");
        System.out.println("por João Kaudy, Gustavo Gawlak, Não Sei mais que Da Silva");
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
                break;
        }

        scanner.close();

    }
}

class Arvore {

    //ATRIBUTOS
    public No raiz;

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

                if(no_atual.proximoEsquerda == null){
                    No no_novo = new No(' ');
                    no_atual.proximoEsquerda = no_novo;
                    no_atual = no_novo;
                }

            } else if (caractere == '-') {

                if (no_atual.proximoDireita == null) {
                    No no_novo = new No(' ');
                    no_atual.proximoDireita = no_novo;
                    no_atual = no_novo;
                }

            }

        }

        no_atual.valor = letra;
    }

    private void popularArvore() {
        inserir(".-", 'A');
        inserir("-...", 'B');
    }

}

public void decodificar(){
    //decodificar as parada aqui
}
}

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