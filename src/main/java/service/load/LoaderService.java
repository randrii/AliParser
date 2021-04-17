package service.load;

import model.DealItem;

import java.util.List;

public interface LoaderService {

    List<DealItem> loadData(int offset);
}
