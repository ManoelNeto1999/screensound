package br.com.manoeldeveloper.screensound.principal;

import br.com.manoeldeveloper.screensound.model.Artista;
import br.com.manoeldeveloper.screensound.model.Musica;
import br.com.manoeldeveloper.screensound.repository.ArtistaRepository;
import br.com.manoeldeveloper.screensound.service.ConsultaChatGPT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ArtistaRepository repositorio;
    private List<Musica> musicas = new ArrayList<>();

    public Principal(ArtistaRepository repositorio) { this.repositorio = repositorio;}

    public void exibeMenu() {
        var opcao = 0;
        while(opcao != 9) {
            var menu = """
                    "*** Screen Sound Músicas ***"
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    
                    9 -  Sair
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    pesquisarDadosSobreUmArtista();
                    break;
                case 9:
                    System.out.println("Programa finalizado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = sc.nextLine();
        System.out.println("Informe o tipo desse artista: (solo, dupla, banda)");
        var tipoArtista = sc.nextLine();
        Artista artista = new Artista(nomeArtista, tipoArtista);
        repositorio.save(artista);
        System.out.println(artista);
    }

    private void cadastrarMusica() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = sc.nextLine();

        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);

        if (artista.isPresent()) {
            var artistaEncontrado = artista.get();

            System.out.println("Digite o nome da musica: ");
            var nomeMusica = sc.nextLine();
            System.out.println("Digite o nome do album a qual a musica pertence: ");
            var nomeAlbum = sc.nextLine();

            musicas.add(new Musica(nomeMusica, nomeAlbum, artistaEncontrado));

            artistaEncontrado.setMusica(musicas);
            repositorio.save(artistaEncontrado);

        } else {
            System.out.println("Artista ainda não cadastro no banco," +
                    " cadastre o primeiro para depois cadastrar uma musica.");
        }
    }

    private void listarMusicas() {
        List<Musica> musicasJaCadastradasNoBanco = repositorio.listarMusicas();
        musicasJaCadastradasNoBanco.forEach(System.out::println);
    }

    private void buscarMusicaPorArtista() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = sc.nextLine();

        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);

        if (artista.isPresent()) {
            Artista artistaEncontrado = artista.get();
            List<Musica> musicasDeUmArtistaEspecifico = repositorio.listarMusicasPorArtista(artistaEncontrado);
            musicasDeUmArtistaEspecifico.forEach(System.out::println);

        } else {
            System.out.println("Artista ainda não cadastro no banco.");
        }
    }

    private void pesquisarDadosSobreUmArtista() {
        System.out.println("Digite o nome do artista que deseja pesquisar: ");
        var nomeArtista = sc.nextLine();
        var reposta = ConsultaChatGPT.obterDadosArtista(nomeArtista);
        System.out.println(reposta);
    }
}
