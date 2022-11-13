import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Demo {


    public static void main(String[] args){
        VehicleRentalSystem vehicleRentalSystem=new VehicleRentalSystem(new CheapestAllotmentStrategy());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];
            if(Objects.equals(commands[0], "addBranch")){
                vehicleRentalSystem.addBranch(commands[1]);
            }else if(Objects.equals(commands[0], "allocatePrice")){
                String vehicleType=commands[2];
                vehicleRentalSystem.allocatePrice(commands[1],VehicleType.valueOf(vehicleType),Integer.parseInt(commands[3]));
            }else if(Objects.equals(commands[0], "addVehicle")){
                String vehicleType=commands[2];
                vehicleRentalSystem.addVehicle(commands[1],VehicleType.valueOf(vehicleType),commands[3]);
            }else if(Objects.equals(commands[0], "bookVehicle")){
                String vehicleType=commands[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String startDateTimeString = scanner.nextLine();
                String endDateTimeString = scanner.nextLine();
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);
                vehicleRentalSystem.bookVehicle(VehicleType.valueOf(vehicleType), startDateTime,endDateTime);
            }else if(Objects.equals(commands[0], "viewVehicleInventory")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String startDateTimeString = scanner.nextLine();
                String endDateTimeString = scanner.nextLine();
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);
                vehicleRentalSystem.viewVehicleInventory(startDateTime,endDateTime);
            }else{
                break;
            }
        }
    }
}
