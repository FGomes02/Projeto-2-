import java.util.*;

public class QueryFunctions {

    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }

    int COUNT_SONGS_YEAR(HashMap<Integer, ArrayList<Song>> songsMap, int ano) {

        if (songsMap.containsKey(ano)) {
            int size = songsMap.get(ano).size();
            System.out.println(size);
            return size;

        } else {
            System.out.println(0);
            return 0;
        }

    }

    int COUNT_DUPLICATE_SONGS_YEAR(HashMap<Integer, ArrayList<Song>> songsMap, int ano) {

        int count = 0;
        int freq;
        String name;
        ArrayList<String> titulosRepetidos = new ArrayList<String>();
        ArrayList<Song> array = songsMap.get(ano);

        for (int i = 0; i < array.size(); i++) {
            name = array.get(i).titulo;
            freq = Collections.frequency(array, name);

            if (freq > 1 && !titulosRepetidos.contains(name)) {
                count++;
                titulosRepetidos.add(name);
            }
        }
        return count;
    }

    String GET_ARTISTS_FOR_TAG(HashMap<String, ArrayList<Artista>> hashMapTag, String tag) {
        String result = "";
        ArrayList<Artista> array = hashMapTag.get(tag);

        if (array == null) {
            result = "No results";
        } else {

            for (Artista artista : array) {
                result += artista.nome + ";";
            }
            result = removeLastChar(result);
        }
        return result;
    }


    String GET_MOST_DANCEABLE(HashMap<Integer,ArrayList<Song>> songsMap, int anoInicio, int anoFim, int numResultados) {

        ArrayList<Song> array = new ArrayList<Song>();
        String result = "";

        for (int i = anoInicio; i < anoFim; i++) {
            array.addAll(songsMap.get(i));
        }

        Collections.sort(array, Comparator.comparingDouble((Song song) -> song.grauDancabilidade).reversed());

        for (int j = 0; j < numResultados; j++) {

            result += array.get(j).titulo + " : " + array.get(j).anoDeLancamento + " : " + array.get(j).grauDancabilidade + "\n";
        }

        return result;
    }


    String GET_UNIQUE_TAGS(HashMap<String, ArrayList<Artista>> hashMapTags) {
        ArrayList<Pair> array = new ArrayList<Pair>();

        for (Map.Entry<String, ArrayList<Artista>> entry : hashMapTags.entrySet()) {



        }

    }














}
