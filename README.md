# TDE 2 – Parte 1: Codificador/Decodificador de Código Morse

Aplicação de console em Java que codifica e decodifica texto em código Morse usando uma árvore binária: cada ponto (`.`) leva ao nó da esquerda e cada traço (`-`) leva ao nó da direita. 

A decodificação percorre a árvore da raiz até o nó correspondente à letra.

---

## Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | Codifica uma frase digitada (texto → Morse) |
| 2 | Decodifica uma frase digitada (Morse → texto) |
| 3 | Codifica um arquivo `.txt` inteiro e gera um novo arquivo |
| 4 | Decodifica um arquivo `.txt` inteiro e gera um novo arquivo |
| 5 | Exibe a árvore binária no terminal |
| 0 | Encerra o programa |

## Requisitos

- JDK 17 ou superior (desenvolvido e testado com JDK 21).
- Nenhuma biblioteca externa é necessária.

## Como compilar

Abra um terminal na pasta raiz do projeto (a que contém a pasta `src`) e execute:

Linux / macOS / Windows (PowerShell ou CMD):

```bash
javac -encoding UTF-8 -d out src/Main.java
```

Isso cria a pasta `out/` com os arquivos `.class` (`Main.class`, `Arvore.class` e `No.class`).

> O `-encoding UTF-8` garante que os acentos do código-fonte sejam compilados corretamente.

---

## Como executar

Ainda na pasta raiz do projeto:

```bash
java -cp out Main
```

### Atalho: compilar e executar em um só comando

Com o Java 11+ também é possível executar direto, sem gerar a pasta `out`:

```bash
java src/Main.java
```
## Como usar

Ao iniciar, o menu é exibido:

```
========================================================
CODIFICADOR E DECODIGICADOR DE CÓDIGO MORSE USANDO ÁRVORE BINÁRIA
========================================================
Digite uma opção:
1. Codificar uma frase
2. Decodificar uma frase
3. Codificar um arquivo de texto
4. Decodifciar um arquivo de texto
5. Exibir a árvore
0. Encerrar o programa
========================================================
```

Digite o número da opção e pressione Enter.

### Opção 1 – Codificar uma frase

```
Digite a sua frase ou palavra a ser codificada
SOS OLA
Frase Codificada: ... --- ... / --- .-.. .-
```

### Opção 2 – Decodificar uma frase

Regras para o código Morse de entrada:

- Use apenas os caracteres `.`, `-`, espaço e `/`.
- Use espaço para separar cada letra.
- Use barra (`/`) para separar cada palavra.

```
Digite a sua frase ou palavra a ser decodificada
... --- ... / --- .-.. .-
Frase Decodificada: SOS OLA
```

### Opções 3 e 4 – Arquivos de texto

Informe o caminho completo de um arquivo `.txt`. O programa processa o arquivo linha a linha (linhas vazias são preservadas) e grava o resultado na mesma pasta do arquivo original, acrescentando um sufixo ao nome:

| Opção | Arquivo de entrada | Arquivo de saída |
|-------|--------------------|------------------|
| 3 – Codificar | `/home/joao/texto.txt` | `/home/joao/texto_codificado.txt` |
| 4 – Decodificar | `/home/joao/texto_codificado.txt` | `/home/joao/texto_codificado_decodificado.txt` |

Exemplo completo

`texto.txt`:
```
Ola mundo

SOS 123
```

Após a opção 3, `texto_codificado.txt`:
```
--- .-.. .- / -- ..- -. -.. ---

... --- ... / .---- ..--- ...--
```

Após a opção 4 aplicada a esse arquivo, `texto_codificado_decodificado.txt`:
```
OLA MUNDO

SOS 123
```

> A decodificação devolve o texto em maiúsculas e sem acentos, pois o Morse implementado não diferencia maiúsculas/minúsculas nem possui letras acentuadas.

### Opção 5 – Exibir a árvore

Imprime a árvore binária no terminal. `.` indica o ramo esquerdo, `-` o ramo direito e `` um nó intermediário sem letra:

```
(raiz)
├── . E
│   ├── . I
│   │   ├── . S
│   │   │   ├── . H
│   │   │   │   ├── . 5
│   │   │   │   └── - 4
│   │   │   └── - V
│   │   │       └── - 3
│   │   └── - U
...
```

Lendo da raiz: `.` → `E`, `. .` → `I`, `. . .` → `S`, `. . . .` → `H`.

---

## Observações e limitações

- O arquivo de entrada deve terminar em `.txt`. O nome do arquivo de saída é obtido substituindo `.txt` por `_codificado.txt`/`_decodificado.txt`; se o arquivo não tiver essa extensão, o nome de saída seria igual ao de entrada e o arquivo original seria sobrescrito.
- Caracteres não suportados (por exemplo `@`, `!` ou letras acentuadas) geram a mensagem de erro correspondente e a frase/linha afetada resulta vazia.
- Código Morse inválido na decodificação (sequência inexistente na árvore ou caractere diferente de `.`, `-`, espaço e `/`) exibe uma mensagem de erro e retorna vazio.
- No menu, digite apenas números inteiros. Digitar uma letra faz o programa encerrar com erro, e qualquer opção numérica inválida exibe "Opção Inválida" e encerra o programa.
