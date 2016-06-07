package no.hyper.current.cloudinarysample.richTextEditor;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import no.hyper.current.cloudinarysample.R;

public class RichTextEditorActivity extends AppCompatActivity {

    private EditText editor;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_editor);
        editor = (EditText) findViewById(R.id.main_editor);
        button = (Button) findViewById(R.id.insert);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendImage();
            }
        });
    }

    private void appendImage() {
        Drawable d = ResourcesCompat.getDrawable(getApplicationContext().getResources(), android.R.drawable.ic_menu_preferences, null);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);

        editor.getText().setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
