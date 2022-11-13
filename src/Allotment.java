import java.time.LocalDateTime;
import java.util.Comparator;

public class Allotment {
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Allotment(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public static class AllotmentComparator implements Comparator<Allotment> {


        @Override
        public int compare(Allotment o1, Allotment o2) {
            if(o1.getStartTime().compareTo(o2.getStartTime())==0){
                return o1.endTime.compareTo(o2.endTime);
            }
            return o1.startTime.compareTo(o2.startTime);
        }
    }
}
