package model.dao;

import model.dto.StockDTO;

import java.util.List;
import java.util.Optional;

public interface StockDAO {
    <T> Optional<List<StockDTO>> checkUserStock(Integer user_id);
}
