package com.kamlesh.bhavcopy.dao;

import com.kamlesh.bhavcopy.entity.CsvRecordEntity;
import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.repository.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordDao {

    private static final int BATCH_SIZE = 1000;
    private final RecordRepository recordRepository;

    public RecordDao(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<String> handleQuery(String queryType, String[] params) {
        String param1 = params.length > 0 ? params[0] : null;
        Double param2 = params.length > 1 ? Double.parseDouble(params[1]) : null;

        // Call the stored procedure using the repository
        return recordRepository.handleQuery(queryType, param1, param2);
    }

    @Transactional
    public void loadCsvFile(List<CsvRecord> csvRecords) {
        List<CsvRecordEntity> batchList = new ArrayList<>();
        int count = 0;

        for (CsvRecord csvRecord : csvRecords) {
            CsvRecordEntity row = new CsvRecordEntity();

            row.setMarket(csvRecord.getField("MARKET"));
            row.setSeries(csvRecord.getField("SERIES"));
            row.setSymbol(csvRecord.getField("SYMBOL"));
            row.setSecurity(csvRecord.getField("SECURITY"));
            row.setPrevClosePrice(Double.parseDouble(csvRecord.getField("PREV_CL_PR")));
            row.setOpenPrice(Double.parseDouble(csvRecord.getField("OPEN_PRICE")));
            row.setHighPrice(Double.parseDouble(csvRecord.getField("HIGH_PRICE")));
            row.setLowPrice(Double.parseDouble(csvRecord.getField("LOW_PRICE")));
            row.setClosePrice(Double.parseDouble(csvRecord.getField("CLOSE_PRICE")));
            row.setNetTradedValue(Double.parseDouble(csvRecord.getField("NET_TRDVAL")));
            row.setNetTradedQuantity(Double.parseDouble(csvRecord.getField("NET_TRDQTY")));
            row.setCorpInd(csvRecord.getField("CORP_IND"));
            row.setHigh52Week(Double.parseDouble(csvRecord.getField("HI_52_WK")));
            row.setLow52Week(Double.parseDouble(csvRecord.getField("LO_52_WK")));

            batchList.add(row);

            if (++count % BATCH_SIZE == 0) {
                recordRepository.saveAll(batchList);
                recordRepository.flush();
                batchList.clear();
            }
        }

        if (!batchList.isEmpty()) {
            recordRepository.saveAll(batchList);
            recordRepository.flush();
        }
    }
}
