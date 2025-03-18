package model.service;

import model.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getUserStock(String user_id);
}
