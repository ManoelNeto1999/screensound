package br.com.manoeldeveloper.screensound.repository;

import br.com.manoeldeveloper.screensound.model.Artista;
import br.com.manoeldeveloper.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("SELECT m FROM Musica m")
    List<Musica> listarMusicas();

    @Query("SELECT m FROM Musica m WHERE m.artista = :artista")
    List<Musica> listarMusicasPorArtista(Artista artista);
}
