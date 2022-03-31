package com.localbakery.domain.dataprovider;

import com.localbakery.domain.entity.Menu;
import com.localbakery.domain.entity.Store;
import com.localbakery.domain.repository.MenuRepository;
import com.localbakery.domain.repository.StoreRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataProvider {
    private static final String STORE_FILE = "classpath:data/breadstore_202201301137.csv";
    private static final String MENU_FILE = "classpath:data/completebreadmenu.csv";

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final ResourceLoader resourceLoader;

    @PostConstruct
    public void init() throws CsvException, IOException {
//        fillMenu();
//        fillStore();

    }

    private void fillMenu() throws IOException, CsvException {
        InputStreamReader isr = new InputStreamReader(resourceLoader.getResource(MENU_FILE).getInputStream());
        CSVReader csvReader = new CSVReader(isr);
        csvReader.readNext(); // skip headers
        List<String[]> allLines = csvReader.readAll();
        log.info("started to add menu datas");
        for (String[] line : allLines) {
            Menu menu = assembleMenu(line);
            if (menu != null) {
                menuRepository.save(menu);
            }
        }
        log.info("finished. adding menu datas");
    }

    private void fillStore() throws IOException, CsvException {
        InputStreamReader isr = new InputStreamReader(resourceLoader.getResource(STORE_FILE).getInputStream());
        CSVReader csvReader = new CSVReader(isr);
        csvReader.readNext(); // skip headers
        List<String[]> allLines = csvReader.readAll();
        log.info("started to add store datas");
        for (String[] line : allLines) {
            Store store = assembleStore(line);
            if (store != null) {
                storeRepository.save(store);
            }
        }
        log.info("finished. adding store datas");
    }

    private Long parsePrice(String price) {
        String str = price;
        if (price.contains("~")) {
            str = price.split("~")[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c - '0' <= 9 && c - '0' >= 0) {
                sb.append(c);
            }
        }
        return Long.valueOf(sb.toString());
    }

    private Menu assembleMenu(String[] sources) {
        if (sources == null)
            return null;
        String[] source = sources[0].split(";");
        String priceExp = source[MenuHeader.PRICE.getOrdinal()];
        Long price;
        if (priceExp.equals("\"변동")) {
            price = 0l;
        } else {
            price = parsePrice(priceExp);
        }
        return Menu.builder()
                .storeMenuId(Long.valueOf(source[MenuHeader.STORE_MENU_ID.getOrdinal()]))
                .storeId(Long.valueOf(source[MenuHeader.STORE_ID.getOrdinal()]))
                .name(source[MenuHeader.NAME.getOrdinal()])
                .price(price)
                .signature(true)
                .modifiedBy("A")
                .createdBy("A")
                .build();
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

    @RequiredArgsConstructor
    @Getter
    enum MenuHeader {
        STORE_MENU_ID(0),
        STORE_ID(1),
        NAME(2),
        PRICE(3),
        ;
        private final int ordinal;
    }
}
