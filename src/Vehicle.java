import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private VehicleStatus status;
    private List<Allotment> allotmentList;

    public Vehicle(String vehicleId,VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType=vehicleType;
        this.status=VehicleStatus.Booked;
        this.allotmentList=new ArrayList<>();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public List<Allotment> getAllotmentList() {
        return allotmentList;
    }

    public void setAllotmentList(List<Allotment> allotmentList) {
        this.allotmentList = allotmentList;
    }
}
