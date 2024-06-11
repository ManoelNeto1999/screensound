package br.com.manoeldeveloper.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome_da_musica")
    private String nomeDaMusica;
    @Column(name = "album")
    private String album;
    @ManyToOne
    private Artista artista;

    public Musica() {}

    public Musica(String nomeDaMusica, String album, Artista artista) {
        this.nomeDaMusica = nomeDaMusica;
        this.album = album;
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDaMusica() {
        return nomeDaMusica;
    }

    public void setNomeDaMusica(String nomeDaMusica) {
        this.nomeDaMusica = nomeDaMusica;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return  " Nome da musica= '" + nomeDaMusica + '\'' +
                ", Album= '" + album + '\'' +
                ", Artista= " + artista.getNome();
    }
}
