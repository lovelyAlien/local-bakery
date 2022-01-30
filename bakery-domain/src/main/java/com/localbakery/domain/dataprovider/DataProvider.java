package com.localbakery.domain.dataprovider;

import com.localbakery.domain.entity.Store;
import com.localbakery.domain.repository.StoreRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataProvider {
    private static final String STORE_FILE = "/data/breadstore_202201301137.csv";
    private static final String MENU_FILE = "classpath:data/completebreadmenu.csv";

    private final StoreRepository storeRepository;

    @PostConstruct
    public void init() throws CsvValidationException, IOException {
        fillStore();

    }

    private void fillStore() throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader(new ClassPathResource(STORE_FILE).getFile());
        CSVReader csvReader = new CSVReader(fileReader);
        csvReader.readNext(); // skip headers
        log.info("started to add store datas");
        while (csvReader.iterator().hasNext()) {
            Store store = assembleStore(csvReader.readNext());
            if (store != null) {
                storeRepository.save(store);
            }
        }
        log.info("finished. adding store datas");
    }

    private Store assembleStore(String[] source) {
        if (source == null)
            return null;
        Point point = new GeometryFactory().createPoint(new Coordinate(Double.parseDouble(source[StoreHeader.longitude.getOrdinal()]), Double.parseDouble(source[StoreHeader.latitude.getOrdinal()])));
        return Store.builder()
                .storeId(Long.valueOf(source[StoreHeader.ID.getOrdinal()]))
                .type(Store.storeType.BAKERY)
                .name(source[StoreHeader.NAME.getOrdinal()])
                .address(source[StoreHeader.ADDRESS.getOrdinal()])
                .homePageUrl(source[StoreHeader.HOME_PAGE_URL.getOrdinal()])
                .thumbnailImageUrl(source[StoreHeader.NAVER_THUMB_URL.getOrdinal()])
                .phoneNumber(source[StoreHeader.PHONE_NUMBER.getOrdinal()])
                .location(point)
                .modifiedBy("A")
                .createdBy("A")
                .build();
    }

    @RequiredArgsConstructor
    @Getter
    enum StoreHeader {
        NAME(0),
        ADDRESS(1),
        CITY_DO(2),
        CITY_GN_GU(3),
        longitude(4),
        latitude(5),
        AREA(6),
        HOME_PAGE_URL(7),
        PHONE_NUMBER(8),
        REPRESENT_MENU(9),
        PRICE(10),
        BASE_YMD(11),
        ID(12),
        NAVER_PLACE_URL(13),
        BUSINESS_HOURS(14),
        NAVER_PLACE_MENU(15),
        NAVER_THUMB_URL(16),
        ;
        private final int ordinal;
    }
}
