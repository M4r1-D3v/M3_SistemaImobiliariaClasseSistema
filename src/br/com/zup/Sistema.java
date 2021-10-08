package br.com.zup;

import java.util.Scanner;

public class Sistema {
    //Método alternativo ao "sout" para receber a entrada de dados

    private static Scanner entradaDados(String dadosRecebidos) {
        System.out.println(dadosRecebidos);
        return new Scanner(System.in);

    }

    //Método para exibir as opções do menu:

    public static void exibirMenu() {
        System.out.println("--------------------------------");
        System.out.println("Seja bem vinde ao sistema da Imobiliária MinhaCasa!");
        System.out.println("--------------------------------");
        System.out.println("Para cadastrar um imóvel, digite: 1");
        System.out.println("Para exibir a lista dos imóveis cadastrados, digite: 2");
        System.out.println("Para sair do sistema, digite: 3");

    }

    //Método para cadastrar um imóvel (opção 1 do menu):

    public static Imovel cadastrarImovel() {
        String enderecoImovel = entradaDados("Digite o endereço do imóvel: ").nextLine();
        double valorAluguel = entradaDados("Digite o valor do aluguel: ").nextDouble();

        Imovel imovel = new Imovel(enderecoImovel, valorAluguel);
        return imovel;
    }
    /* Um imóvel tem como atributos, além do endereço e do valor do aluguel, o funcionário responsável e os moradores.
    // O Método para cadastrar um funcionário responsável vai pegar a parte do menu que cadastra os funcionários e
    //transformar num método próprio pra isso, para desaclopar o código. Assim, dentro desse método, que será com
    //retorno,(pq iremos precisar que retorne um funcionário para cadastrar no imovel), irá precisar colocar a parte do menu
    //de entrada de dados (tem um novo método só pra isso aqui dentro do sistema, o capturarDados(), + instanciar um funcionário
    //a partir dessa captura de dados e retornar esse funcionário.
    //Assim, essa parte do menu feita na Main:
    //System.out.println("Digite o nome do funcionário responsável: ");
    //                String nomeFuncionario = input.nextLine();
    //                System.out.println("Digite o cpf do funcionário: ");
    //                String cpfFuncionario = input.nextLine();
    //                System.out.println("Digite o numero da carteira de trabalho do funcionário: ");
    //                String ctpsFuncionario = input.nextLine();
    //
    //                Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, ctpsFuncionario);
     vira:*/

    public static Funcionario cadastrarFuncionario() {
        //parte do menu de entrada de dados:
        String nomeFuncionario = entradaDados("Digite o nome do funcionário responsável:").nextLine();
        String cpfFuncionario = entradaDados("Digite o cpf do funcionário:").nextLine();
        String ctpsFuncionario = entradaDados("Digite o numero da carteira de trabalho do funcionário:").nextLine();

        //instanciar funcionário com os dados recebidos:

        Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, ctpsFuncionario);

        //retornar um funcionario:

        return funcionario;

    }

    //Agora é seguir o mesmo raciocínio da criação do método cadastrarFuncionario(), para os moradores do imóvel:
    //System.out.println("Por favor, digite o nome do morador: ");
    //                        String nomeMorador = input.nextLine();
    //                        System.out.println("Por favor digite o cpf do morador: ");
    //                        String cpfMorador = input.nextLine();
    //                        System.out.println("Por favor, digite o telefone do morador: ");
    //                        String telefoneMorador = input.nextLine();
    //                        input.nextLine();
    //
    //                        Morador morador = new Morador(nomeMorador, cpfMorador, telefoneMorador);
    //Fica assim:
    //Método para cadastrar morador:

    public static Morador cadastrarMorador() {
        String nomeMorador = entradaDados("Por favor, digite o nome do morador:").nextLine();
        String cpfMorador = entradaDados("Por favor digite o cpf do morador:").nextLine();
        String telefoneMorador = entradaDados("Por favor, digite o telefone do morador:").nextLine();

        Morador morador = new Morador(nomeMorador, cpfMorador, telefoneMorador);
        return morador;
    }

    //Let's bora juntar tudo!
    // Agora, precisa criar um método que irá englobar todos os mini-métodos criados (cada um com a sua responsabilidade), para ser executado na Main
    //Aqui, devido ao princípio da responsabilidade única de cada método, instancia-se a imobiliária.
    // a ordem dentro do loop, será a ordem lógica em que o programa irá rodar:
    // 1) exibe o menu com as opções para que o usuário faça a escolha;
    //2) mostra o submenu dada a escolha anterior do usuário e todas as demais opções contidas nele;

    public static void executar() {
        boolean menuGeral = true;
        Imobiliaria imobiliariaExecutar = new Imobiliaria();


        while (menuGeral) {

            exibirMenu(); //exibe menu de opções para o usuário.
            System.out.println("--------------------------------");
            int escolhaUsuario = entradaDados("Por favor digite a opção desejada:").nextInt();//recebe os dados do usuário;

            switch (escolhaUsuario) { //submenu com o desdobramento da escolha do usuário feita no menu anterior

                case 1:
                    //cadastrar imovel + funcionário responsável
                    System.out.println("--------------------------------");
                    Imovel imovelCadastro = cadastrarImovel();// o método cadastrarImovel retorna um imovel do tipo Imovel, que será guardado na variável imovelCadastro
                    Funcionario funcionarioCadastro = cadastrarFuncionario();
                    imovelCadastro.setFuncionarioResponsavel(funcionarioCadastro); // inclui o funcionário cadastrado no imóvel cadastrado

                    //cadastrar quantidade de moradores do imovel
                    int qtdeMoradores = entradaDados("Por favor, digite a quantidade de moradores do imóvel: ").nextInt();
                    int contador = 0;

                    while (contador < qtdeMoradores) { //repetir a opção de cadastrar morador enquanto o contador for menor do que a quantidade de moradores recebida.
                        Morador moradorCadastro = cadastrarMorador();
                        imovelCadastro.adicionarMorador(moradorCadastro);
                        contador++;
                    }
                    imobiliariaExecutar.adicionarImovel(imovelCadastro);//depois do imovel cadastrado+funcionario cadastrado no imovel+moradores cadastrados, basta adicionar o imovel na imobiliaria.
                    break;
                case 2:
                    System.out.println(imobiliariaExecutar); //printar imobiliaria para exibir a lista de imoveis (opção 2 do menu);
                    break;
                case 3:
                    System.out.println("--------------------------------");
                    System.out.println("Obrigade por utlizar o nosso sistema, até a próxima, volte sempre!");//mensagem de saída do menu (opção 3)
                    menuGeral = false; //sai do menu

            }


        }

    }


    //Método para exibir a lista dos imóveis cadastrados: apenas um sout(imobiliaria) qd for juntar tudo.
}


