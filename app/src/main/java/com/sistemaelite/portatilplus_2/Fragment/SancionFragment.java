package com.sistemaelite.portatilplus_2.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.sistemaelite.portatilplus_2.R;

import java.io.File;
import java.io.FileNotFoundException;

public class SancionFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String TAG = "SancionFragment";
    private static final String ENDPOINT = "https://portatilplusapi.onrender.com/admin/sancion";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sancion, container, false);

        Button btnPdf = view.findViewById(R.id.btnpdf);
        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    fetchDataAndCreatePdf();
                }
            }
        });

        return view;
    }

    private void fetchDataAndCreatePdf() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ENDPOINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        createPdf(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error al obtener datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Volley error: ", error);
            }
        });

        queue.add(stringRequest);
    }

    private void createPdf(String content) {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
        File dir = new File(pdfPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "sanciones.pdf");

        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Sanciones"));
            document.add(new Paragraph(content));

            document.close();
            Toast.makeText(getContext(), "PDF generado correctamente en " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al generar el PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchDataAndCreatePdf();
            } else {
                Toast.makeText(getContext(), "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
