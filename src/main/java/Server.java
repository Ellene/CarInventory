import au.com.bytecode.opencsv.CSVReader;
import net.codestory.http.WebServer;
import net.codestory.http.templating.Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Server {

    public static final String DATA_FILE = "mars-2014-complete.csv";
    public static final char SEPARATOR = ';';

    public static void main(String[] args) throws IOException {

        Reader reader = new InputStreamReader(getSystemResourceAsStream(DATA_FILE));
        CSVReader csvReader = new CSVReader(reader, SEPARATOR);
        
        List cars = csvReader.readAll().stream()
                .skip(1)
                .map(line -> new Car(line[0], line[1]))
                .collect(toList());

        new WebServer(routes -> routes
                .get("/index", Model.of("cars", cars))
        ).start();
    }
}
