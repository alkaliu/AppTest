package com.alkali.ActTestJava;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;

public class CsvLineDataProvider {
    private final String TIME_FIELD_NAME = "Time";
    private final String RPM_FIELD_NAME = "RPM";
    private List<String> mHeaders;
    private List<NamedCsvRecord> mCsvRecords;


    public CsvLineDataProvider(String src) {
        try (CsvReader<NamedCsvRecord> csv = CsvReader.builder().ofNamedCsvRecord(src)) {
            mCsvRecords = csv.stream().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!mCsvRecords.isEmpty()) {
            NamedCsvRecord labels = mCsvRecords.get(0);
            mHeaders = labels.getHeader();
        }
    }

    public List<String> headers() {
        return mHeaders;
    }

    public List<String> getSignalData(String headerName) {
        List<String> values = new ArrayList<>();

        if (mHeaders.contains(headerName)) {
            mCsvRecords.forEach(namedCsvRecord -> {
                values.add(namedCsvRecord.getField(headerName));
            });
        }

        return values;
    }

    public List<Integer> getSignalDataAsInt(String headerName) {
        List<Integer> values = new ArrayList<>();

        if (mHeaders.contains(headerName)) {
            mCsvRecords.forEach(namedCsvRecord -> {
                values.add(Integer.parseInt(namedCsvRecord.getField(headerName)));
            });
        }

        return values;
    }

    public ArrayList<Entry> getSignalTimeData(String headerName) {
        ArrayList<Entry> values = new ArrayList<>();

        if (mHeaders.contains(headerName)) {
            mCsvRecords.forEach(namedCsvRecord -> {
                values.add(new Entry( Float.parseFloat(namedCsvRecord.getField(TIME_FIELD_NAME)),
                                      Float.parseFloat(namedCsvRecord.getField(headerName))
                                    )
                );
//                Log.d("CsvLineDataProvider.", "getSignalTimeData: added Entry=" + values.get(values.size() - 1).toString() );
            });
        }

        return values;
    }
}
