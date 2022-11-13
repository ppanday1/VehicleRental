import java.util.*;

public class Branch {
    private String branchName;
    private HashMap<VehicleType,Integer> vehicleCount;
    private HashMap<VehicleType,Double>rentalPrice;
    private HashMap<VehicleType,List<Allotment>> vehicleAllotments;
    private List<Vehicle>vehicles;

    public Branch(String branchName) {
        this.vehicles=new ArrayList<>();
        this.branchName=branchName;
        this.vehicleCount=new HashMap<>();
        this.rentalPrice=new HashMap<>();
        this.vehicleAllotments=new HashMap<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public HashMap<VehicleType, Integer> getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(HashMap<VehicleType, Integer> vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public HashMap<VehicleType, Double> getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(HashMap<VehicleType, Double> rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public HashMap<VehicleType, List<Allotment>> getVehicleAllotments() {
        return vehicleAllotments;
    }

    public void setVehicleAllotments(HashMap<VehicleType, List<Allotment>> vehicleAllotments) {
        this.vehicleAllotments = vehicleAllotments;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
