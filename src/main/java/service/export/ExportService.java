package service.export;

import model.DealItem;

import java.util.List;

public interface ExportService {

    void export(List<DealItem> items);
}
