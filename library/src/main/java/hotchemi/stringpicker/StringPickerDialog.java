package hotchemi.stringpicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class StringPickerDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.string_picker_dialog, null, false);

        final StringPicker stringPicker = (StringPicker) view.findViewById(R.id.stringpicker);
        final Bundle params = getArguments();
        if (params == null) {
            throw new RuntimeException("values is not set!");
        }

        final String[] values = params.getStringArray("STRING_PICKER_VALUES");
        stringPicker.setValues(values);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("StringPickerDialog");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), values[stringPicker.getCurrent()], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.setView(view);
        return builder.create();
    }

}