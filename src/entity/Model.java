package entity;

public class Model extends AbstractEntity {
    private String name;
    private String year;
    private TransmissionType transmissionType;
    private FuelType fuelType;
    private VehicleType vehicleType;
    private Brand brand;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return brand +
                " " +
                name +
                " | " +
                transmissionType +
                " | " +
                fuelType +
                " | " +
                vehicleType;
    }

    public enum FuelType {
        GASOLINE, DIESEL, LPG, ELECTRIC;

        public static FuelType fromString(String str) {
            try {
                return FuelType.valueOf(str.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid fuelType type: " + str);
            }
        }
    }

    public enum TransmissionType {
        AUTOMATIC, MANUAL;

        public static TransmissionType fromString(String str) {
            try {
                return TransmissionType.valueOf(str.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid transmission type: " + str);
            }
        }
    }

    public enum VehicleType {
        SEDAN, HATCHBACK, STATION_WAGON, SUV, COUPE, MPV;

        public static VehicleType fromString(String str) {
            try {
                return VehicleType.valueOf(str.toUpperCase().replace(" ", "_"));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid vehicle type: " + str);
            }
        }
    }

}
