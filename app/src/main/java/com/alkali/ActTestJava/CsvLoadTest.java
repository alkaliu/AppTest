package com.alkali.ActTestJava;

import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import de.siegmar.fastcsv.reader.*;

public class CsvLoadTest extends AppCompatActivity {
    private List<CsvRecord> mCsvRecords;
    GridView mCsvView;

    public CsvLoadTest() {
    }
    private void loadCsv() {

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(CsvSourceProvider.fromResources(getResources().openRawResource(R.raw.log_data)))) {
            mCsvRecords = csv.stream().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCsv();

        setContentView(R.layout.activity_grid_test);
        mCsvView = findViewById(R.id.csvTable);
        ArrayList<CsvRecord> courseModelArrayList = new ArrayList<CsvRecord>(mCsvRecords.stream().toList());
        CsvRecordAdapter adapter = new CsvRecordAdapter(this, courseModelArrayList);
        mCsvView.setNumColumns(courseModelArrayList.get(0).getFieldCount());
        mCsvView.setAdapter(adapter);
    }
}
