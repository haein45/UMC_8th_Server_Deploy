package umc.spring.service.FoodCategoryService;

import java.util.List;

public interface FoodCategoryService {
    boolean existsAllByIds(List<Long> ids);
}
