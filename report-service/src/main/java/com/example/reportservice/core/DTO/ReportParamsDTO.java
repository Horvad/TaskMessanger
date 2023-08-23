package com.example.reportservice.core.DTO;

import java.time.LocalDate;
import java.util.UUID;

public class ReportParamsDTO {
    private String user;
    private LocalDate from;
    private LocalDate to;

    public ReportParamsDTO() {
    }

    public ReportParamsDTO(String user,
                           LocalDate from,
                           LocalDate to) {
        this.user = user;
        this.from = from;
        this.to = to;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
