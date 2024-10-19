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
            CsvRecordEntity record = new CsvRecordEntity();

            record.setMarket(csvRecord.getField("MARKET"));
            record.setSeries(csvRecord.getField("SERIES"));
            record.setSymbol(csvRecord.getField("SYMBOL"));
            record.setSecurity(csvRecord.getField("SECURITY"));
            record.setPrevClosePrice(Double.parseDouble(csvRecord.getField("PREV_CL_PR")));
            record.setOpenPrice(Double.parseDouble(csvRecord.getField("OPEN_PRICE")));
            record.setHighPrice(Double.parseDouble(csvRecord.getField("HIGH_PRICE")));
            record.setLowPrice(Double.parseDouble(csvRecord.getField("LOW_PRICE")));
            record.setClosePrice(Double.parseDouble(csvRecord.getField("CLOSE_PRICE")));
            record.setNetTradedValue(Double.parseDouble(csvRecord.getField("NET_TRDVAL")));
            record.setNetTradedQuantity(Double.parseDouble(csvRecord.getField("NET_TRDQTY")));
            record.setCorpInd(csvRecord.getField("CORP_IND"));
            record.setHigh52Week(Double.parseDouble(csvRecord.getField("HI_52_WK")));
            record.setLow52Week(Double.parseDouble(csvRecord.getField("LO_52_WK")));

            batchList.add(record);

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
