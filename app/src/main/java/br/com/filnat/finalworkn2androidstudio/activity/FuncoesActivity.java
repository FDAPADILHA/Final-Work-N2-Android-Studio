package br.com.filnat.finalworkn2androidstudio.activity;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.filnat.finalworkn2androidstudio.R;

public class FuncoesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPower, buttonPowerOn, buttonPowerOff;
    public static final int RESULT_ENABLE = 11;
    private DevicePolicyManager devicePolicyManager;
    private ActivityManager activityManager;
    private ComponentName compName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcoes);
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        compName = new ComponentName(this, AdminPower.class);

        initView();

    }

    private void initView() {
        buttonPower = findViewById(R.id.buttonPower);
        buttonPowerOn = findViewById(R.id.buttonPowerOn);
        buttonPowerOff = findViewById(R.id.buttonPowerOff);
        buttonPower.setOnClickListener(this);
        buttonPowerOn.setOnClickListener(this);
        buttonPowerOff.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isActive = devicePolicyManager.isAdminActive(compName);
        buttonPowerOff.setVisibility(isActive ? View.VISIBLE : View.GONE);
        buttonPowerOn.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonPower) {
            boolean active = devicePolicyManager.isAdminActive(compName);

            if (active) {
                devicePolicyManager.lockNow();
            } else {
                Toast.makeText(this, "Você precisa de permissão para executar essa função!", Toast.LENGTH_SHORT).show();
            }

        } else if (view == buttonPowerOn) {

            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Por que você precisa de permissão?");
            startActivityIfNeeded(intent, RESULT_ENABLE);
                /*startActivityForResult*/
        } else if (view == buttonPowerOff) {
            devicePolicyManager.removeActiveAdmin(compName);
            buttonPowerOff.setVisibility(View.GONE);
            buttonPowerOn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch(requestCode) {
            case RESULT_ENABLE:
                    if(resultCode == Activity.RESULT_OK){
                        Toast.makeText(FuncoesActivity.this, "Você precisa de permissão para executar essa função!",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(FuncoesActivity.this, "Problema para configurar a permissão!",Toast.LENGTH_LONG).show();
                    }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
