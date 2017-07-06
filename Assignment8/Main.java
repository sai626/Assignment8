package Assignment8;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zemoso on 6/7/17.
 */
public class Main {

    private Map<Character,Integer> map;
    private BufferedWriter writer;
    private BufferedReader reader;

    /**
     * The constructor. It initializes the map, and the BufferedReader and BufferedWriter objects
     * to be used in subsequent operations.
     * @param fileName The file whose character count is needed
     * @throws IOException Any problem while initializing BufferedReader and BufferedWriter will
     * result in IOException.
     */
    public Main(String fileName) throws IOException {
        map=new HashMap<>();
        writer=new BufferedWriter(new FileWriter("Count.txt"));
        reader=new BufferedReader(new FileReader(fileName));
    }

    /**
     * This method tracks the character count and accordingly updates it in map.
     * @throws IOException Failure to read the file results in the Exception.
     */
    public void count() throws IOException {
        String s;
        int temp;
        s=reader.readLine();
        while (s!=null){
            for (char ch:s.toCharArray()){
                temp = map.getOrDefault(ch, 0);

                temp++;
                map.put(ch,temp);
            }
            s=reader.readLine();
        }
    }

    /**
     * This method writes the data in the map to a file.
     * @throws IOException Failure to write the data to specified file will result in this
     * Exception being thrown
     */
    public void write() throws IOException {
        writer.write("Character \tCount\n");
        writer.write("---------------------------\n");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            writer.write(entry.getKey()+"  \t\t"+entry.getValue()+"\n");
        }
    }

    /**
     * This method performs the final cleanup. It closes the open BufferedReader
     * and BufferedWriter Connections.
     * @throws IOException Any error while closing the connections will result in this
     * Exception being thrown.
     */
    public void closeConnections() throws IOException {
        reader.close();
        writer.close();
    }


    /**
     * The main method. It specifies the input file, calls appropriate functions and
     * closes connections when done.
     * @param args args[0] indicates the filename of the input file.
     */
    public static void main(String args[]){

        if(args.length!=0) {
            try {
                Main m = new Main(args[0]);
                System.out.println("Counting Characters");
                m.count();
                System.out.println("Writing character count");
                m.write();
                System.out.println("Closing Connections");
                m.closeConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("No input file specified");
        }
    }
}
