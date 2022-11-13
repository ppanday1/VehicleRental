import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CheapestAllotmentStrategy extends VehicleAllotmentStartegy{

    @Override
    public boolean allocatVehicle(Map<String, Branch> branches, Allotment allotment, VehicleType vehicleType) {
        BranchService branchService=new BranchService();
        VehicleService vehicleService=new VehicleService();
        double pricing=Integer.MAX_VALUE;
        String branchName="";
        for(Map.Entry<String,Branch>branch:branches.entrySet()){

            Branch currentBranch=branch.getValue();
            if(!branch.getValue().getRentalPrice().containsKey(vehicleType)){
                continue;
            }
            if(branchService.isVehicleAvailable(currentBranch,allotment.getStartTime(),allotment.getEndTime(),vehicleType)){
                if(branchService.getPricing(currentBranch,vehicleType)<pricing){
                    pricing=branchService.getPricing(currentBranch,vehicleType);
                    branchName=branch.getKey();
                }
            }
        }
        if(Objects.equals(branchName, "")){
            System.out.println("Can't Book Vehicle");
            return false;
        }
        Branch correctBranch = branches.get(branchName);
        List<Vehicle> vehicles =correctBranch.getVehicles();
        for(Vehicle vehicle: vehicles){
            if(vehicleService.isVehicleVailable(vehicle,allotment.getStartTime(),allotment.getEndTime())){
                vehicleService.bookVehicle(vehicle,allotment.getStartTime(),allotment.getEndTime());
                System.out.println("Branch" +correctBranch.getBranchName()+" BookedVehicle with id "+vehicle.getVehicleId());
                return true;
            }
        }
        return false;
    }
}
