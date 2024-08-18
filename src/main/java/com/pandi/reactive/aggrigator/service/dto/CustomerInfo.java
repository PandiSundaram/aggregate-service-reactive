package com.pandi.reactive.aggrigator.service.dto;

import java.util.List;

public record CustomerInfo(Integer id,
                           String name,
                           Integer balance,
                           List<Portfolio> porfolioList) {
}
