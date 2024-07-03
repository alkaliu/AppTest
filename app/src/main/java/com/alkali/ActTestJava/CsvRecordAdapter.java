package com.alkali.ActTestJava;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import de.siegmar.fastcsv.reader.CsvRecord;

public class CsvRecordAdapter extends ArrayAdapter<CsvRecord> {
    private Context mContext;
    private ArrayList<CsvRecord> mData;

    public CsvRecordAdapter(@NonNull Context context, ArrayList<CsvRecord> csvArrayList) {
        super(context, 0, csvArrayList);
        mContext = context;
        mData = csvArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("CsvRecordAdapter.", "getView: pos=" + position );
        TextView textView;

        if (convertView == null) {
            textView = new TextView(mContext);
            int record_len = mData.get(0).getFieldCount();
            int recNumber = position / record_len;
            int cellNumber = position % record_len;
            CsvRecord record = mData.get(recNumber);
            String cell_text = record.getField(cellNumber);
            Log.d("CsvRecordAdapter.", "getView: recNumber=" + recNumber + " cellNumber=" + cellNumber + " Data="+ cell_text );
            textView.setText(cell_text);
            if(position == 0) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
            }
        }
        else
        {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
