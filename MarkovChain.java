import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MarkovChain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner( new File("file.txt"));
        Hashtable <String, ArrayList> h = new Hashtable();

        while(s.hasNextLine()){ //reads in next line from text
            StringTokenizer st = new StringTokenizer(s.nextLine());
            ArrayList <String> words = new ArrayList<String>();
            while(st.hasMoreTokens()){
                words.add(st.nextToken());
            }

            for(int i= 0; i<words.size()-1; i++){
                if (!h.contains(words.get(i))) {
                    ArrayList <String> newValues = new ArrayList<String>();
                    newValues.add(words.get(i+1));
                    h.put(words.get(i), newValues);
                }
                else{
                    h.get(words.get(i)).add(words.get(i+1));
                }

            }

        }

        Scanner user = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Number of words: ");
        int length = user.nextInt(); //the number of words to print

        String sentence= "";

        ArrayList <String> keys = new ArrayList<String>(h.keySet());
        int keyIndex = r.nextInt(keys.size()); //choose a random keyIndex
        String key = keys.get(keyIndex);

        for(int i = 0; i<length; i++){
            if(keys.get(keyIndex+1)==null|| h.get(key) == null){
                keyIndex = r.nextInt(keys.size());
                key = keys.get(keyIndex);
            }

            sentence += key + " ";
            String value = h.get(key).get(r.nextInt(h.get(key).size()))+ " ";
            key = value;

        }

        System.out.println(sentence);// print


    }
}
