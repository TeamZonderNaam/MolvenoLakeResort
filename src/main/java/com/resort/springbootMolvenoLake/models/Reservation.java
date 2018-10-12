package com.resort.springbootMolvenoLake.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public abstract class Reservation
{
    public Reservation(){}
    public Reservation(LocalDateTime start, LocalDateTime end)
    {
        this.start = start;
        this.end = end;
        this.startYear = start.getYear();
        this.startMonth = start.getMonthValue();
        this.startDay = start.getDayOfMonth();
        this.startHour = start.getHour();
        this.startMinute = start.getMinute();
        this.endYear = end.getYear();
        this.endMonth = end.getMonthValue();
        this.endDay = end.getDayOfMonth();
        this.endHour = end.getHour();
        this.endMinute = end.getMinute();
    }
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd-MM-yyyy@HH:mm");//.withLocale(Locale.CHINA);
    @JsonFormat(pattern="dd-MM-yyyy@HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern="dd-MM-yyyy@HH:mm")
    private LocalDateTime end;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String notes;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addNotes(String notes) {
        this.notes += "\n" + notes;
    }

    private Integer startYear;
    private Integer startMonth;
    private Integer startDay;
    private Integer startHour;
    private Integer startMinute;
    private Integer endYear;
    private Integer endMonth;
    private Integer endDay;
    private Integer endHour;
    private Integer endMinute;


    // getters for calendar library
    public Integer getStartYear()
    {
        return start.getYear();
    }
    public void setStartYear(Integer year) { start = LocalDateTime.of(startYear = year, getStartMonth(), getStartDay(), getStartHour(), getStartMinute()); }
    public Integer getStartMonth()
    {
        return start.getMonthValue();
    }
    public void setStartMonth(Integer month){ start = LocalDateTime.of(getStartYear(), startMonth = month, getStartDay(), getStartHour(), getStartMinute());  }
    public Integer getStartDay()
    {
        return start.getDayOfMonth();
    }
    public void setStartDay(Integer day){ start = LocalDateTime.of(getStartYear(), getStartMonth(), startDay = day, getStartHour(), getStartMinute());  }
    public Integer getStartHour()
    {
        return start.getHour();
    }
    public void setStartHour(Integer hour){ start = LocalDateTime.of(getStartYear(), getStartMonth(), getStartDay(), startHour = hour, getStartMinute()); }
    public Integer getStartMinute()
    {
        return start.getMinute();
    }
    public void setStartMinute(Integer minute){ start = LocalDateTime.of(getStartYear(), getStartMonth(), getStartDay(), getStartHour(), startMinute = minute); }

    public Integer getEndYear()
    {
        return end.getYear();
    }
    public void setEndYear(Integer year){ end = LocalDateTime.of(endYear = year, getEndMonth(), getEndDay(), getEndHour(), getEndMinute()); }
    public Integer getEndMonth()
    {
        return end.getMonthValue();
    }
    public void setEndMonth(Integer month){ end = LocalDateTime.of(getEndYear(), endMonth = month, getEndDay(), getEndHour(), getEndMinute()); }
    public Integer getEndDay()
    {
        return end.getDayOfMonth();
    }
    public void setEndDay(Integer day){ end = LocalDateTime.of(getEndYear(), getEndMonth(), endDay = day, getEndHour(), getEndMinute()); }
    public Integer getEndHour(){ return end.getHour(); }
    public void setEndHour(Integer hour){ end = LocalDateTime.of(getEndYear(), getEndMonth(), getEndDay(), endHour = hour, getEndMinute()); }
    public Integer getEndMinute(){ return end.getMinute(); }
    public void setEndMinute(Integer minute){ end = LocalDateTime.of(getEndYear(), getEndMonth(), getEndDay(), getEndHour(), endMinute = minute); }
}
