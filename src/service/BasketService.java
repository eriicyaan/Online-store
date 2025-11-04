package service;

import dao.BasketDao;
import dto.BasketDto;
import dto.GoodDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.BasketMapper;
import mapper.GoodMapper;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketService {
    private static final BasketService INSTANCE = new BasketService();
    private final BasketDao basketDao = BasketDao.getInstance();
    private final GoodMapper goodMapper = GoodMapper.getInstance();
    private final BasketMapper basketMapper = BasketMapper.getInstance();

    public List<GoodDto> getAllGoods(int userId) throws SQLException {

        return basketDao.getAllAddedGoods(userId)
                .stream()
                .map(goodMapper::mapTo)
                .toList();
    }

    public void addGood(BasketDto basketDto) {
        basketDao.save(basketMapper.mapFrom(basketDto));

    }

    public void deleteGood(int userId, int goodId) {
        basketDao.delete(userId, goodId);
    }

    public static BasketService getInstance() {
        return INSTANCE;
    }
}
