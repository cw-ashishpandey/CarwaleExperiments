package com.example.ashishpandey.androidsupportdesignapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ashish.pandey on 6/24/2015.
 */

    public class CanaroTextView extends TextView {
        public CanaroTextView(Context context) {
            this(context, null);
        }

        public CanaroTextView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public CanaroTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setTypeface(App.canaroExtraBold);
        }

    }
