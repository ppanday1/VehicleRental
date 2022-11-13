import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchService {
    public boolean addVehicle(Branch branch,VehicleType vehicleType,String vehicleId){
        Map<VehicleType,Integer> vehicleCount = branch.getVehicleCount();
        List<Vehicle> vehicles =branch.getVehicles();
        int countOfVehicle=1;
        if(vehicleCount.containsKey(vehicleType)){
            vehicleCount.put(vehicleType,vehicleCount.get(vehicleType)+countOfVehicle);
        }else{
            vehicleCount.put(vehicleType,countOfVehicle);
        }
        vehicles.add(new Vehicle(vehicleId,vehicleType));
        System.out.println("vehicle with vehicleId "+vehicleId+" added to branch "+branch.getBranchName());
        return true;
    }

    public boolean updatePricing(Branch branch,VehicleType vehicleType, double pricing){
        Map<VehicleType,Double>rentalPrice=branch.getRentalPrice();
        if(rentalPrice.containsKey(vehicleType)){
            System.out.println("Pricing Exists but changing");
        }
        rentalPrice.put(vehicleType,pricing);
        System.out.println("Pricing Updated For VehicleType "+vehicleType+" for branch "+branch.getBranchName()+" with pricing "+pricing);
        return true;
    }

    private int findAllotmentcount(Branch branch,LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType){
        Map<VehicleType,List<Allotment>> vehicleAllotments=branch.getVehicleAllotments();
        if(!vehicleAllotments.containsKey(vehicleType)){
            return 0;
        }
        List<Allotment>allotments=vehicleAllotments.get(vehicleType);
        int startInd=-1;
        int endInd=-1;
        for(int i=0;i<allotments.size();i++){
            if(allotments.get(i).getStartTime().compareTo(startTime)<=0 && startInd==-1){
                startInd=i;
            }
            if(allotments.get(i).getStartTime().compareTo(startTime)<=0){
                endInd=i;
            }
        }
        if(startInd==-1 || endInd==-1){
            return 0;
        }

        return endInd-startInd+1;
    }



    public boolean isVehicleAvailable(Branch branch,LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType){
        List<Vehicle>vehicles=branch.getVehicles();
        VehicleService vehicleService=new VehicleService();
        for(Vehicle vehicle:vehicles){
            if(vehicle.getVehicleType()==vehicleType&&vehicleService.isVehicleVailable(vehicle,startTime,endTime)){
                return true;
            }
        }
        return false;
    }


    public boolean allotVehicle(Branch branch, LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType){
        if(!isVehicleAvailable(branch,startTime,endTime, vehicleType)){
            System.out.println("Vehicle Not Available at this slot and type");
            return false;
        }
        Map<VehicleType,List<Allotment>> vehicleAllotments=branch.getVehicleAllotments();
        List<Allotment>allotments=new ArrayList<>();
        if(vehicleAllotments.containsKey(vehicleType))
            allotments=vehicleAllotments.get(vehicleType);
        allotments.add(new Allotment(startTime,endTime));
        allotments.sort(new Allotment.AllotmentComparator());
        vehicleAllotments.put(vehicleType,allotments);
        List<Vehicle>vehicles=branch.getVehicles();
        for(Vehicle vehicle: vehicles){
            if(vehicle.getVehicleType()==vehicleType && vehicle.getStatus()==VehicleStatus.Booked){
                System.out.println(vehicle.getVehicleId()+" from "+branch.getBranchName()+" booked" );
                vehicle.setStatus(VehicleStatus.Available);
            }
        }
        return true;
    }

    public double getPricing(Branch branch,VehicleType vehicleType){
        HashMap<VehicleType,Double> rentalPrice=branch.getRentalPrice();
        if(!rentalPrice.containsKey(vehicleType)){
            return -1;
        }
        return rentalPrice.get(vehicleType);
    }

    public void printStatus(Branch branch,LocalDateTime startTime,LocalDateTime endTime){
        System.out.println("Branch: "+branch.getBranchName());
        List<Vehicle>vehicles=branch.getVehicles();
        VehicleService vehicleService=new VehicleService();
        for(Vehicle vehicle : vehicles){
            String status="Available";
            if(!vehicleService.isVehicleVailable(vehicle,startTime,endTime)){
                status="Booked";
            }
            System.out.println(vehicle.getVehicleType()+" "+vehicle.getVehicleId()+" "+status);
        }
    }
}
