package guru.springframework.spring6restmvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.bean.CsvToBeanBuilder;

import guru.springframework.spring6restmvc.model.BeerCSVRecord;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {

    @Override
    public List<BeerCSVRecord> convertCSV(File file) {
        List<BeerCSVRecord> beerCSVRecords;
        try {
            beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(file))
                .withType(BeerCSVRecord.class)
                .build().parse();
            
            return beerCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
