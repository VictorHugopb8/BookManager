package com.digivox.seletiva.bookmanager.bookmanager.book.services;

import com.digivox.seletiva.bookmanager.bookmanager.book.dto.DashboardDTO;

import java.util.List;

@FunctionalInterface
public interface DashboardService {
    List<DashboardDTO> getDashboardInfo(String type);
}
