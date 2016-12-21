package fr.koolicar.koolicarapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jean-philippedescamps on 20/12/2016.
 */

public class Service extends AsyncTask<String, Void, List> {

    Context context;
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog == null) {
            initProgressDialog();
        }
        progressDialog.show();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Chargement en cours...");
        progressDialog.setCancelable(false);
    }

    @Override
    protected List doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (list == null) {
            Toast.makeText(context, "Aucun résultats trouvés", Toast.LENGTH_LONG);
        }
    }
}
