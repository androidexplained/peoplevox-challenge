package com.sample.randomscanner.data;

import com.sample.randomscanner.domain.BarcodeRepository;
import com.sample.randomscanner.domain.model.Barcode;
import com.sample.randomscanner.domain.model.BarcodeColor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kotlin.random.Random;

public final class RandomBarcodeRepository implements BarcodeRepository {
    private final List<String> barcodes;
    private final List<String> types;
    private final List<String> colors;

    public RandomBarcodeRepository(List<String> barcodes, List<String> types, List<String> colors) {
        this.barcodes = barcodes;
        this.types = types;
        this.colors = colors;
    }

    @Override
    public final Barcode get() {
        String randomBarcode = barcodes.get(Random.Default.nextInt(0, barcodes.size()));
        String randomType = types.get(Random.Default.nextInt(0, types.size()));
        String randomColor = colors.get(Random.Default.nextInt(0, colors.size()));
        return new Barcode(randomBarcode, randomType, BarcodeColor.valueOf(randomColor), getDate());
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatter = SimpleDateFormat.getDateTimeInstance();
        return formatter.format(date);
    }
}
