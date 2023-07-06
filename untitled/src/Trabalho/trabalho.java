import java.util.Scanner;

public class trabalho {

    public static void mostrarcarros(String[][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modelo, Ano, Valor, Quilometragem, Manutenção");

        for (int indice = 0; indice < linhas; indice++) {
            System.out.println(indice + 1 + " - ");
            for (int vetor = 0; vetor < colunas; vetor++) {
                System.out.print(matriz[indice][vetor] + " | ");
            }
            System.out.println();
        }
    }

    public static void inserircarro(String[][] matriz, int linhas, int colunas) {
        int linha;
        Scanner scanner = new Scanner(System.in);
        mostrarcarros(matriz, linhas, colunas);

        do {
            System.out.println("Informe onde você deseja inserir o produto com valores de 1 à " + linhas);
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Informe um número válido.");
                scanner.next();
            }
            linha = scanner.nextInt();
        } while (linha < 1 || linha > linhas);

        System.out.println("Insira o modelo do carro: ");
        matriz[linha - 1][0] = validarmodelo(scanner.next());

        System.out.println("Insira o ano do carro: ");
        String anoDoCarro = scanner.next();
        while (!validarano(Integer.parseInt(anoDoCarro))) {
            System.out.println("Ano inválido, por favor, coloque um ano válido.");
            anoDoCarro = scanner.next();
        }

        matriz[linha - 1][1] = anoDoCarro;

        System.out.println("Insira a quilometragem do carro: ");
        String quilometragemDoCarro = scanner.next();
        while (!validarquilometragem(Integer.parseInt(quilometragemDoCarro))) {
            System.out.println("Quilometragem inválida, por favor, coloque uma quilometragem válida.");
            quilometragemDoCarro = scanner.next();
        }
        matriz[linha - 1][2] = quilometragemDoCarro;

        System.out.println("Insira o valor do carro: ");
        String valorDoCarro = scanner.next();
        while (!validarpreço(Integer.parseInt(valorDoCarro))) {
            System.out.println("Valor inválido, por favor, coloque um valor válido.");
            valorDoCarro = scanner.next();
        }
        matriz[linha - 1][3] = valorDoCarro;
        System.out.println("Carro adicionado");

    }

    public static void removerCarro(String[][] matriz, int linhas, String modeloCarro) {
        boolean encontrado = false;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(modeloCarro)) {
                encontrado = true;
                matriz[i][0] = null;
                matriz[i][1] = null;
                matriz[i][2] = null;
                matriz[i][3] = null;
            }
        }
        if (encontrado) {
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void realizarManutencao(String[][] matriz, int linhas) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o modelo do carro que quer realizar a manutenção");

        String modeloCarro = scanner.next();
        modeloCarro = validarmodelo(modeloCarro);

        boolean manutencao = false;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(modeloCarro)) {
                manutencao = true;
                matriz[i][4] = "recebeu manutenção";
            }
        }

        if (manutencao) {
            System.out.println("Manutenção realizada no carro " + modeloCarro + ", o custo de manutenção é arcado pela garagem.");
        } else {
            System.out.println("Manutenção não realizada. Não foi possível encontrar esse carro na garagem.");
        }
    }

    public static void calcularValorGaragem(String[][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);
        float somaTotal = 0.0f;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null) {
                somaTotal = somaTotal + Float.parseFloat(matriz[i][3]);
            }
        }
        System.out.println("Valor final de carros guardados na garagem é de: " + somaTotal + " R$");
    }

    public static String validarmodelo(String modelo) {
        String modeloValidado = modelo.replaceAll("[^a-zA-Z]", "");
        return modeloValidado;
    }

    public static boolean validarpreço(int preço) {
        return preço > 0;
    }

    public static boolean validarquilometragem(int quilometragem) {
        return quilometragem > 0;
    }

    public static boolean validarano(int ano) {
        return ano > 1886 && ano < 2024;
    }

    public static void main(String[] args) {
        String[][] listaDeCarros;
        int colunas = 5;
        int carros, opção;
        Scanner scanner = new Scanner(System.in);
        String nomeCarro;

        System.out.println("Insira a quantidade de carros que serão inseridos na garagem:");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Informe um número válido.");
            scanner.next();
        }
        carros = scanner.nextInt();
        listaDeCarros = new String[carros][colunas];

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Mostrar lista de carros.");
            System.out.println("2 - Inserir carro.");
            System.out.println("3 - Calcular valor total da garagem.");
            System.out.println("4 - Remover carro da lista.");
            System.out.println("5 - Realizar manutenção em um carro.");
            System.out.println("0 - Sair.");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Informe um número válido.");
                scanner.next();
            }
            opção = scanner.nextInt();
            switch (opção) {
                case 0:
                    break;
                case 1:
                    mostrarcarros(listaDeCarros, carros, colunas);
                    break;
                case 2:
                    inserircarro(listaDeCarros, carros, colunas);
                    break;
                case 3:
                    calcularValorGaragem(listaDeCarros, carros);
                    break;
                case 4:
                    System.out.println("Insira o produto a ser removido.");
                    nomeCarro = scanner.next();
                    removerCarro(listaDeCarros, carros, nomeCarro);
                    break;
                case 5:
                    System.out.println();
                    realizarManutencao(listaDeCarros, carros);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opção != 0);
    }
}