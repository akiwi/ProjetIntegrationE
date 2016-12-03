package be.ephec.groupe3.carewatch.Admin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import be.ephec.groupe3.carewatch.R;

/**
 * Created by Julien on 3/12/2016.
 */

public class EditInfActivity  extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit);

        Bundle extras = getIntent().getExtras();

        EditText name = (EditText) findViewById(R.id.ET_edit_nameUser);
        name.setText(extras.getString("nom"));

    }
}
