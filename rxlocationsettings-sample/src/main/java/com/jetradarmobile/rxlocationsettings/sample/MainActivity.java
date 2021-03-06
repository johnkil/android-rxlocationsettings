package com.jetradarmobile.rxlocationsettings.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.jetradarmobile.rxlocationsettings.RxLocationSettings;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ensureLocationSettings();
      }
    });
  }

  private void ensureLocationSettings() {
    LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
        .addLocationRequest(LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY))
        .build();
    RxLocationSettings.with(this).ensure(locationSettingsRequest)
        .subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean enabled) {
            Toast.makeText(MainActivity.this, enabled ? "Enabled" : "Failed", Toast.LENGTH_LONG).show();
          }
        });
  }
}