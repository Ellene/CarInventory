import java.math.BigDecimal;

/**
 * Created by vincent on 31/07/14.
 */
public class CarsComputation {

  public static final CarsComputation NEUTRAL = new CarsComputation(null, 0, null);
  private final String completeModel;

  private int maxCO2;

  private BigDecimal minConsoMixte;


  public CarsComputation(String completeModel, int maxCO2, BigDecimal minConsoMixte) {
    this.completeModel = completeModel;
    this.maxCO2 = maxCO2;
    this.minConsoMixte = minConsoMixte;
  }

  public int getMaxCO2() {
    return maxCO2;
  }

  public void setMaxCO2(int maxCO2) {
    this.maxCO2 = maxCO2;
  }

  public BigDecimal getMinConsoMixte() {
    return minConsoMixte;
  }

  public void setMinConsoMixte(BigDecimal minConsoMixte) {
    this.minConsoMixte = minConsoMixte;
  }

  public String getCompleteModel() {
    return completeModel;
  }

  public CarsComputation aggregate(CarsComputation anotherCarsComputation) {
    if (this.equals(NEUTRAL)) {
      return anotherCarsComputation;
    }

    return new CarsComputation(completeModel, Math.max(this.getMaxCO2(), anotherCarsComputation.getMaxCO2()), this.getMinConsoMixte().min(anotherCarsComputation.getMinConsoMixte()));
  }

  public enum CO2Emission {
    LOW_CO2,
    MEDIUM_CO2,
    HIGH_CO2,
    DEFAULT_CO2
  }

  public CO2Emission getCO2Emission() {
    if (maxCO2 < 50) {
      return CO2Emission.LOW_CO2;
    } else if (maxCO2 > 200) {
      return CO2Emission.HIGH_CO2;
    } else if (maxCO2 > 100){
      return CO2Emission.MEDIUM_CO2;
    } else {
      return CO2Emission.DEFAULT_CO2;
    }
  }
}
