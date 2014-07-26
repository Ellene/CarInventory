import au.com.bytecode.opencsv.CSVReader;
import net.codestory.http.WebServer;
import net.codestory.http.templating.Model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.stream.Collectors.toList;

public class Server {

  public static final String DATA_FILE = "mars-2014-complete.csv";
  public static final char SEPARATOR = ';';

  public static void main(String[] args) throws IOException {

    Reader reader = new InputStreamReader(getSystemResourceAsStream(DATA_FILE));
    CSVReader csvReader = new CSVReader(reader, SEPARATOR);

    // TODO worst co2 emission

    List cars = csvReader.readAll().stream()
            .skip(1)
            .map(line -> {
                      String vendor = line[0];
                      String model = line[1];
                      String engineType = line[6];
                      boolean isHybride = "oui".equals(line[7]);
                      int co2 = line[14].equals("") ? 0 : Integer.parseInt(line[14]);

                      System.out.println("consoMixte : " + line[13]);

                      BigDecimal consoMixte = "".equals(line[13]) ? BigDecimal.ZERO : new BigDecimal(line[13].replace(",", "."));

                      return new Car(vendor, model, engineType,
                              isHybride,
                              co2,
                              consoMixte);
                    }
            )
            .sorted(new Comparator<Car>() {
              @Override
              public int compare(Car o1, Car o2) {
                return o2.getCo2() - o1.getCo2();
              }
            })
            .distinct()
            .collect(toList());

    new WebServer(routes -> routes
            .get("/index", Model.of("cars", cars))
    ).start();
  }
}
