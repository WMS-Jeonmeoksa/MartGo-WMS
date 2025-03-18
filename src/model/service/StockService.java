package model.service;

import model.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getUserStock(int user_id);
}
