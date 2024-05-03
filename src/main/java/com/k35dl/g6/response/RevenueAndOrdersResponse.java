package com.k35dl.g6.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RevenueAndOrdersResponse {
    private List<Long> revenue;
    private List<Long> orders;
    private List<LocalDate> dates;
}
