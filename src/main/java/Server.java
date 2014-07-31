import au.com.bytecode.opencsv.CSVReader;
import net.codestory.http.WebServer;
import net.codestory.http.templating.Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class Server {

  public static final String DATA_FILE = "mars-2014-complete.csv";
  public static final char SEPARATOR = ';';

  public static void main(String[] args) throws IOException {

    Reader reader = new InputStreamReader(getSystemResourceAsStream(DATA_FILE));
    CSVReader csvReader = new CSVReader(reader, SEPARATOR);

    // TODO best mixte emission

    Collection<CarsComputation> carsComputations = csvReader.readAll().stream()
            .skip(1)
            .map(line -> toCar(line))
            .collect(groupingBy(Car::getCompleteModel, TreeMap::new, reducing(CarsComputation.NEUTRAL, a -> a.toCarsComputation(), (a, b) -> a.aggregate(b))))
            .values();


    new WebServer(routes -> routes
            .get("/index", Model.of("cars", carsComputations))
    ).start();
  }

  private static Car toCar(String[] line) {
    String vendor = line[0];
    String model = line[1];
    String engineType = line[6];
    boolean isHybride = "oui".equals(line[7]);
    int co2 = line[14].equals("") ? 0 : Integer.parseInt(line[14]);


    BigDecimal consoMixte = "".equals(line[13]) ? BigDecimal.ZERO : new BigDecimal(line[13].replace(",", "."));

    return new Car(vendor, model, engineType,
            isHybride,
            co2,
            consoMixte);
  }
}
