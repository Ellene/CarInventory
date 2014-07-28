import java.math.BigDecimal;

public class Car {

  private final String vendor;
  private final String model;
  private final String engineType;
  private final boolean isHybride;
  private final int co2;
  private final BigDecimal consoMixte;


  public Car(String vendor, String model, String engineType, boolean isHybride, int co2, BigDecimal consoMixte) {
    this.vendor = vendor;
    this.model = model;
    this.engineType = engineType;
    this.isHybride = isHybride;
    this.co2 = co2;
    this.consoMixte = consoMixte;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Car car = (Car) o;

    if (vendor != null ? !vendor.equals(car.vendor) : car.vendor != null) return false;
    if (engineType != null ? !engineType.equals(car.engineType) : car.engineType != null) return false;
    if (model != null ? !model.equals(car.model) : car.model != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = vendor != null ? vendor.hashCode() : 0;
    result = 31 * result + (model != null ? model.hashCode() : 0);
    result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
    return result;
  }

  public String getVendor() {
    return vendor;
  }

  public String getModel() {
    return model;
  }

  public String getEngineType() {
    return engineType;
  }

  public boolean isHybride() {
    return isHybride;
  }

  public int getCo2() {
    return co2;
  }

  public BigDecimal getConsoMixte() {
    return consoMixte;
  }
}
