package mapper;

import dto.GoodDto;
import entity.Good;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodMapper implements Mapper<GoodDto, Good> {
    private static final GoodMapper INSTANCE = new GoodMapper();

    @Override
    public Good mapFrom(GoodDto obj) {
        return Good.builder()
                .name(obj.getName())
                .cost(Double.parseDouble(obj.getCost()))
                .imagePath(obj.getImagePath())
                .build();
    }

    public GoodDto mapTo(Good obj) {
        return GoodDto.builder()
                .id(obj.getId())
                .name(obj.getName())
                .cost("" + obj.getCost())
                .imagePath(obj.getImagePath())
                .build();
    }

    public Good mapFrom(ResultSet resultSet) {
        try {
            return Good.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .cost(resultSet.getDouble("cost"))
                    .imagePath(resultSet.getString("image_path"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static GoodMapper getInstance() {
        return INSTANCE;
    }
}
