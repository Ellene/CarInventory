public class Car {

    private final String Vendor;
    private final String model;

    public Car(String vendor, String model) {
        Vendor = vendor;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (Vendor != null ? !Vendor.equals(car.Vendor) : car.Vendor != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Vendor != null ? Vendor.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    public String getVendor() {
        return Vendor;
    }

    public String getModel() {
        return model;
    }
}
