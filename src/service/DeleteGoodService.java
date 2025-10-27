package service;

import dao.GoodDao;
import dto.GoodDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteGoodService {
    private static final DeleteGoodService INSTANCE = new DeleteGoodService();
    private final GoodDao goodDao = GoodDao.getInstance();

    public void deleteGood(int goodId) {
        goodDao.delete(goodId);
    }

    public static DeleteGoodService getInstance() {
        return INSTANCE;
    }
}
