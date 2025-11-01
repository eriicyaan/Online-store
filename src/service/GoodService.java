package service;

import dao.GoodDao;
import dto.GoodDto;
import entity.Good;
import exception.GoodNotFoundException;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.GoodMapper;
import validator.GoodValidator;
import validator.ValidationResult;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodService {
    private static final GoodService INSTANCE = new GoodService();
    private final GoodDao goodDao = GoodDao.getInstance();
    private final GoodMapper mapper = GoodMapper.getInstance();
    private final GoodValidator validator = GoodValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();


    public void addGood(GoodDto goodDto, HttpServletRequest req) throws ValidationException, ServletException, IOException {
        ValidationResult valid = validator.isValid(goodDto);
        if(valid.isValid()) {
            Good good = mapper.mapFrom(goodDto);
            imageService.loadImage(req.getPart("image"));
            goodDao.save(good);
        }
        else {
            throw new ValidationException(valid.getErrors());
        }

    }

    public List<GoodDto> getAllGoods() {
        return goodDao.findAll().stream()
                .map(mapper::mapTo)
                .toList();
    }

    public double getCost(int goodId) throws GoodNotFoundException {
        Optional<Good> maybeGood = goodDao.findById(goodId);
        if(maybeGood.isPresent()) {
            return maybeGood.get().getCost();
        }
        throw new GoodNotFoundException();
    }

    public static GoodService getInstance() {
        return INSTANCE;
    }
}
