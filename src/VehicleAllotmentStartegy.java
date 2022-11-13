import java.util.Map;

public abstract class VehicleAllotmentStartegy {
    public abstract boolean allocatVehicle(Map<String,Branch> branches,Allotment allotment,VehicleType vehicleType);
}
