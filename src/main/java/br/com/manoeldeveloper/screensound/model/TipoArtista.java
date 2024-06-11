package br.com.manoeldeveloper.screensound.model;

public enum TipoArtista {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipoArtistaScreenSound;

    TipoArtista(String tipoArtistaScreenSound) {
        this.tipoArtistaScreenSound = tipoArtistaScreenSound;
    }

    public static TipoArtista fromString(String text) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.tipoArtistaScreenSound.equalsIgnoreCase(text)) {
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de artista encontrado para a string fornecida: " + text);
    }
}
