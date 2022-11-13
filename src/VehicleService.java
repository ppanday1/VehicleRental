import java.time.LocalDateTime;
import java.util.List;

public class VehicleService {

    public  Boolean isVehicleVailable(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime){
        List<Allotment> allotmentList=vehicle.getAllotmentList();
        for(Allotment allotment : allotmentList){

            if(allotment.getStartTime().compareTo(endTime)<=0 && allotment.getEndTime().compareTo(startTime)>=0 ){
                return false;
            }
        }
        return true;
    }

    public  Boolean bookVehicle(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime){
        List<Allotment> allotmentList=vehicle.getAllotmentList();
        Allotment allotment =new Allotment(startTime,endTime);
        allotmentList.add(allotment);
        allotmentList.sort(new Allotment.AllotmentComparator());
        return true;
    }
}
