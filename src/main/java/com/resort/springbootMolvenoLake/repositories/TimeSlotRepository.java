package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Time;
import com.resort.springbootMolvenoLake.models.TimeSlot;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeSlotRepository {

    private List<TimeSlot> timeSlotList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Time startTime;
        TimeSlot timeSlot;
        for (int i = 7; i < 21; i++) {
            for (int j = 0; j <= 45; j += 15) {
                startTime = new Time(i, j);
                timeSlot = new TimeSlot(startTime);
                timeSlot.setAvailable(true);
                timeSlotList.add(timeSlot);
            }
        }
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }
}
