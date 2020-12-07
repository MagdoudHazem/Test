package com.example.testora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testora.Entity.MyAdapter;
import com.example.testora.Entity.myitems;
import com.example.testora.Entity.data;

import com.example.testora.services.ServiceItems;
import com.google.android.gms.vision.CameraSource;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private List<myitems> userList = new ArrayList<myitems>();
    public ImageView add;
    private ZXingScannerView scannerView;
    private EditText macadress;
    private EditText ipadress;
    private EditText model;
private myitems mItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItem =new myitems();
        add = findViewById(R.id.btnAdd);
        RecyclerView simpleList = findViewById(R.id.list);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceItems.url).addConverterFactory(GsonConverterFactory.create()).build();
        ServiceItems s=retrofit.create(ServiceItems.class);
        Call<List<myitems>> call=s.getAll();
        //Log.e("avanif",MyAdapter.my.getTitle());
        if (data.ac)
        {
            Log.e("grrrrrr","2e5dem");
            AlertDialog.Builder mbuilder1 = new AlertDialog.Builder(MainActivity.this);
            final View mview1 = getLayoutInflater().inflate(R.layout.update_dialogue, null);
            mbuilder1.setView(mview1);
            final AlertDialog dialog1 = mbuilder1.create();
            dialog1.show();

        }
        call.enqueue(new Callback<List<myitems>>() {
            @Override
            public void onResponse(Call<List<myitems>> call, Response<List<myitems>> response) {
                Log.e("lissst",response.body().toString());
                for (myitems p :response.body()){
                    userList.add(p);
                }

            }

            @Override
            public void onFailure(Call<List<myitems>> call, Throwable t) {

            }
        });
      // userList=database.itemsDao().getAll();
        simpleList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        simpleList.setAdapter(new MyAdapter( this,userList));
        simpleList.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("list",database.itemsDao().getAll().toString());
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
                final View mview = getLayoutInflater().inflate(R.layout.add_dialogue, null);
                final EditText title = (EditText) mview.findViewById(R.id.title);
                macadress = (EditText) mview.findViewById(R.id.macadresse);
                ipadress = (EditText) mview.findViewById(R.id.ipadress);
                model = (EditText) mview.findViewById(R.id.model);
macadress.setText(mItem.getMacAdress());
model.setText(mItem.getModel());
                final TextView ajouter = (TextView) mview.findViewById(R.id.add);
                TextView cancel = (TextView) mview.findViewById(R.id.cancel);
                scannerView = mview.findViewById(R.id.camerapreview);
                final CameraSource cameraSource;

                ajouter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("items",mItem.toString());
                        macadress.setText(mItem.getMacAdress());
                        model.setText(mItem.getModel());
                        mItem.setEtat("Active");
                        DateFormat date = new SimpleDateFormat("MMM dd yyyy, h:mm");
                        String dateFormatted = date.format(Calendar.getInstance().getTime());
                        mItem.setDate(dateFormatted);
                        mItem.setTitle(title.getText().toString());
                        mItem.setIpAdress(ipadress.getText().toString());
                        if (title.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"remplir tout les champs",Toast.LENGTH_SHORT).show();

                        }
                        else if (ipadress.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"remplir tout les champs",Toast.LENGTH_SHORT).show();

                        } else
                            {
                                Retrofit retrofit=new Retrofit.Builder().baseUrl(ServiceItems.url).addConverterFactory(GsonConverterFactory.create()).build();
                                ServiceItems s=retrofit.create(ServiceItems.class);
                                Call<myitems> call=s.createitems(mItem);
                                call.enqueue(new Callback<myitems>() {
                                    @Override
                                    public void onResponse(Call<myitems> call, Response<myitems> response) {
                                        userList.add(mItem);
                                        RecyclerView simpleList = findViewById(R.id.list);
                                        simpleList.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                                        simpleList.setAdapter(new MyAdapter( MainActivity.this,userList));

                                    }

                                    @Override
                                    public void onFailure(Call<myitems> call, Throwable t) {

                                    }
                                });



                            }

                    }
                });
                Dexter.withActivity((Activity) mview.getContext()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(MainActivity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();


                mbuilder.setView(mview);
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.e("value", rawResult.toString());
        String[] parts = rawResult.toString().split(";");
        final String maca = parts[0];
        final String model = parts[1];
        mItem.setMacAdress(maca);
        mItem.setModel(model);
    }
}
