import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class VehicleRentalSystem {
    HashMap<String,Branch> branchList;
    HashSet<String>vehicleIds;
    VehicleAllotmentStartegy vehicleAllotmentStartegy;
    BranchService branchService;

    public VehicleRentalSystem(VehicleAllotmentStartegy vehicleAllotmentStartegy) {
        this.branchList=new HashMap<>();
        this.vehicleAllotmentStartegy=vehicleAllotmentStartegy;
        branchService=new BranchService();
        vehicleIds=new HashSet<>();
    }

    public boolean addBranch(String branchName){
        if(branchList.containsKey(branchName)){
            System.out.println("BranchName Already Exists");
            return false;
        }
        branchList.put(branchName,new Branch(branchName));
        System.out.println("Branch "+branchName+" added");
        return true;
    }

    public boolean allocatePrice(String branchName,VehicleType vehicleType,double pricing){
        if(!branchList.containsKey(branchName)){
            System.out.println("Branch not present");
            return false;
        }
        Branch branch=branchList.get(branchName);
        return branchService.updatePricing(branch,vehicleType,pricing);
    }

    public boolean addVehicle(String vehicleId, VehicleType vehicleType,String branchName){
        if(!branchList.containsKey(branchName)){
            System.out.println("Branch not present");
            return false;
        }
        if(vehicleIds.contains(vehicleId)){
            System.out.println("Can't add vehicle: vehicleID Already Added");
            return false;
        }
        Branch branch=branchList.get(branchName);
        return branchService.addVehicle(branch,vehicleType,vehicleId);
    }

    public boolean bookVehicle(VehicleType vehicleType, LocalDateTime startTime, LocalDateTime endTime){
        return vehicleAllotmentStartegy.allocatVehicle(branchList,new Allotment(startTime,endTime),vehicleType);
    }

    public void viewVehicleInventory(LocalDateTime startTime,LocalDateTime endTime){
        for(Map.Entry<String,Branch>branchMap : branchList.entrySet()){
            Branch branch=branchMap.getValue();
            branchService.printStatus(branch,startTime,endTime);
        }
    }
}
