package com.example.learningplatform;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.Annotation;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpannedString emptyText = (SpannedString) getText(R.string.no_filters_selected);
        SpannableStringBuilder ssb = new SpannableStringBuilder(emptyText);
        final Annotation[] annotations = emptyText.getSpans(0, emptyText.length(), Annotation.class);
        if (annotations != null && annotations.length > 0) {
            for (int i = 0; i < annotations.length; i++) {
                final Annotation annotation = annotations[i];
                if (annotation.getKey().equals("src")) {
                    // image span markup
                    String name = annotation.getValue();
                    int id = getResources().getIdentifier(name, null, getPackageName());
                    if (id == 0) continue;
                    ssb.setSpan(new ImageSpan(this, id,
                                    ImageSpan.ALIGN_BASELINE),
                            emptyText.getSpanStart(annotation),
                            emptyText.getSpanEnd(annotation),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (annotation.getKey().equals("foregroundColor")) {
                    // foreground color span markup
                    String name = annotation.getValue();
                    int id = getResources().getIdentifier(name, null, getPackageName());
                    if (id == 0) continue;
                    ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, id)),
                            emptyText.getSpanStart(annotation),
                            emptyText.getSpanEnd(annotation),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }
}

