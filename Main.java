import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;


public class Main {

    public static String ficheiroSongs = "songs.txt";
    public static int ignoredSongs = 0;
    public static int okSongs = 0;

    public static String ficheiroDetails = "song_details.txt";
    public static int okTemaMusical = 0;
    public static int ignoredTemaMusical = 0;

    public static String ficheiroArtists = "song_artists.txt";
    public static int okArtistas = 0;
    static int ignoredArtistas = 0;

    static ArrayList<Song> arraySong = new ArrayList<>();
    static ArrayList<Artista> arrayArtistas = new ArrayList<>();
    static ArrayList<Song> arrayDetails = new ArrayList<>();

    static ParseInfo infoSongs;
    static ParseInfo infoTemaMusical;
    static ParseInfo infoArtistas;

    static ArrayList<Song> musicas = new ArrayList<>();




    public static void main(String[] args) throws IOException {
        loadFiles();
        System.out.println(getParseInfo("songs.txt"));

        System.out.println("Welcome to DEISI Rockstar!");


    }





    public static String getCreativeQuery() {
        return "";
    }


    public static int getTypeOfSecondParameter() {
        return 0;
    }


    public static String getVideoUrl() {
        return null;
    }

    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }


    public static HashMap<Integer, ArrayList<Song>> createHashMapSongs(Song[] songs) {

        HashMap<Integer, ArrayList<Song>> songsMap = new HashMap<Integer, ArrayList<Song>>();

        for (Song song : songs) {

            if (songsMap.containsKey(song.anoDeLancamento)) {
                songsMap.get(song.anoDeLancamento).add(song);

            } else {
                songsMap.put(song.anoDeLancamento, new ArrayList<Song>());
                songsMap.get(song.anoDeLancamento).add(song);
            }
        }
        return songsMap;
    }

    public static HashMap<String, ArrayList<Artista>> createHashMapTags() {
        return  new HashMap<String, ArrayList<Artista>>();
    }

    public static HashMap<Integer, ArrayList<Song>> createHashMapArtists(Artista[] artists) {

        HashMap<Integer, ArrayList<Artista>> artistaMap = new HashMap<Integer, ArrayList<Artista>>();

        for (Artista i : artists) {

            if (artistaMap.containsKey())

        }

    }


    public static void loadFiles() throws IOException {
        ignoredSongs = 0;
        okSongs = 0;

        ignoredArtistas = 0;
        okArtistas = 0;

        ignoredTemaMusical = 0;
        okTemaMusical = 0;

        arraySong.clear();
        arrayArtistas.clear();
        arrayDetails.clear();

        try {
            File ficheiro = new File(ficheiroSongs);
            FileInputStream fis = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis);

            while (leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String dados[] = linha.split("@");

                if (dados.length == 3) {
                    String id = dados[0].trim();
                    String nome = dados[1].trim();
                    int anoLancamento = Integer.parseInt(dados[2].trim());

                    arraySong.add(new Song(id, nome, anoLancamento));
                    okSongs++;

                } else {
                    ignoredSongs++;
                }
            }
            infoSongs = new ParseInfo(okSongs, ignoredSongs);
        }
        catch (IOException e) {
            System.out.println("Erro");
        }

        try {
            File ficheiro1 = new File(ficheiroDetails);
            FileInputStream fis1 = new FileInputStream(ficheiro1);
            Scanner leitorFicheiro1 = new Scanner(fis1);

            while (leitorFicheiro1.hasNextLine()) {

                String linha = leitorFicheiro1.nextLine();
                String dadosTemaMusical[] = linha.split("@");

                if (dadosTemaMusical.length == 7) {

                    String id = dadosTemaMusical[0].trim();
                    int duracao = Integer.parseInt(dadosTemaMusical[1].trim());
                    int intLetraExplicita = Integer.parseInt(dadosTemaMusical[2].trim());

                    boolean letraExplita;
                    letraExplita = intLetraExplicita == 1;

                    int popularidade = Integer.parseInt(dadosTemaMusical[3].trim());
                    float dancabilidade = Float.parseFloat(dadosTemaMusical[4].trim());
                    float vivacidade = Float.parseFloat(dadosTemaMusical[5].trim());
                    float volumeMedio = Float.parseFloat(dadosTemaMusical[6].trim());

                    okTemaMusical++;
                    arrayDetails.add(new Song(id, duracao, letraExplita, popularidade, dancabilidade, vivacidade, volumeMedio));
                } else {
                    ignoredTemaMusical++;
                }
            }
            infoTemaMusical = new ParseInfo(okTemaMusical, ignoredTemaMusical);
        } catch (IOException e) {
            System.out.println("Erro");
        }

        try {

            File ficheiro2 = new File(ficheiroArtists);
            FileInputStream fis2 = new FileInputStream(ficheiro2);
            Scanner leitorFicheiro2 = new Scanner(fis2);

            while(leitorFicheiro2.hasNextLine()) {

                String linha = leitorFicheiro2.nextLine();
                String dadosArtistas[] = linha.split("@");

                if (dadosArtistas.length == 2) {

                    String id = dadosArtistas[0].trim();
                    String nome = dadosArtistas[1].trim();

                    Artista artistaObj = new Artista(id, nome);
                    okArtistas++;
                    arrayArtistas.add(artistaObj);

                } else {
                    ignoredArtistas++;
                }

            }
            infoArtistas = new ParseInfo(okArtistas, ignoredArtistas);
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }

    public static ArrayList<Song> getSongs() {
        return arraySong;
    }

    public static ParseInfo getParseInfo(String fileName) {
        if (fileName.equals(ficheiroSongs)) {
            return infoSongs;

        } else if (fileName.equals(ficheiroDetails)) {
            return infoTemaMusical;

        } else if (fileName.equals(ficheiroArtists)) {
            return infoArtistas;

        } else {
            return null;
        }
    }
}


class Song {

    String id;
    String titulo;
    Artista[] artistasEnvolvidos;
    int anoDeLancamento;
    int duracaoTempo;
    boolean letraExplicita;
    int popularidade;
    float grauDancabilidade;
    float grauVivacidade;
    float volumeMedio;

    public Song(String ID, String titulo, Artista[] artistasEnvolvidos, int anoDeLancamento, int duracaoTempo,
                boolean letraExplicita, int popularidade, float grauDancabilidade, float grauVivacidade, float volumeMedio) {
        this.id = ID;
        this.titulo = titulo;
        this.artistasEnvolvidos = artistasEnvolvidos;
        this.anoDeLancamento = anoDeLancamento;
        this.duracaoTempo = duracaoTempo;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }

    public Song(String ID, String titulo, int anoDeLancamento) {
        this.id = ID;
        this.titulo = titulo;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Song(String ID, int duracaoTempo, boolean letraExplicita, int popularidade,
                float grauDancabilidade, float grauVivacidade, float volumeMedio) {
        this.id = ID;
        this.duracaoTempo = duracaoTempo;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }

    public String toString() {
        return id + " | " + titulo + " | " + anoDeLancamento;
    }

}

class Artista {

    String id;
    String nome;

    public Artista(String ID, String Nome) {
        this.id = ID;
        this.nome = Nome;
    }

    public String toString() {
        return id + " | " + nome;
    }
}

class ParseInfo {
    int linhasOk;
    int linhasIgnoradas;

    public ParseInfo(int linhasOk, int linhasIgnoradas) {
        this.linhasOk = linhasOk;
        this.linhasIgnoradas = linhasIgnoradas;
    }

    public String toString() {
        return "OK: " + linhasOk + ", Ignored: " + linhasIgnoradas;
    }
}

class Pair {
    String nome;
    int num;

    public Pair(String nome, int num) {
        this.nome = nome;
        this.num = num;
    }
}