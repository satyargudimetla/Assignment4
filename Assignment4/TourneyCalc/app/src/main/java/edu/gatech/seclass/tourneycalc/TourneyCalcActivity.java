package edu.gatech.seclass.tourneycalc;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Dialog;
import android.graphics.Color;

public class TourneyCalcActivity extends AppCompatActivity {
    private EditText txtEntranceFee;
    private EditText txtEntranceNumber;
    private EditText housePercentage;
    private EditText txtHouseCut;
    private EditText txtFirstPrizeVal;
    private EditText txtSecondPrizeVal;
    private EditText txtThirdPrizeVal;
    public static final double firstPercent = 50;
    public static final double secondPercent = 30;
    public static final double thirdPercent = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney_calc);

        txtEntranceFee = (EditText)findViewById(R.id.entranceFee);
        txtEntranceNumber = (EditText)findViewById(R.id.entrantsNumber);
        housePercentage = (EditText)findViewById(R.id.housePercentage);
        txtHouseCut = (EditText)findViewById(R.id.houseCutValue);
        txtFirstPrizeVal = (EditText)findViewById(R.id.firstPrizeValue);
        txtSecondPrizeVal = (EditText)findViewById(R.id.secondPrizeValue);
        txtThirdPrizeVal = (EditText)findViewById(R.id.thirdPrizeValue);
    }

    public void handleClick(View view) {

        switch (view.getId()){

            case R.id.buttonCalculate:
                String getEntranceFeeValue = txtEntranceFee.getText().toString();
                String getEntrantNumberValue = txtEntranceNumber.getText().toString();
                String getHousePercentValue = housePercentage.getText().toString();
                Log.d("CheckReturnVal",String.valueOf(chkValueRange(getHousePercentValue)));

                if (ChkIntValue(getEntranceFeeValue) && ChkIntGtThreeValue(getEntrantNumberValue) && chkValueRange(getHousePercentValue)){
                    Integer getProductVal = getIntValue(getEntranceFeeValue) * getIntValue(getEntrantNumberValue);
                    String getHouseCutVal = getHouseCut(getProductVal, getHousePercentValue);
                    String getPortionTxt = String.valueOf( getProductVal - getIntValue(getHouseCutVal));
                    Log.d("MyActivity", getPortionTxt);

                    txtHouseCut.setText(getHouseCutVal);

                    String getFirstPrizePortionVal = "";

                    txtFirstPrizeVal.setText(getPortion(getPortionTxt, firstPercent));
                    txtSecondPrizeVal.setText(getPortion(getPortionTxt ,secondPercent));
                    txtThirdPrizeVal.setText(getPortion(getPortionTxt ,thirdPercent));
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "";
                    if(!ChkIntValue(getEntranceFeeValue)){
                        text = "Invalid Fee!";
                    }
                    if(!ChkIntGtThreeValue(getEntrantNumberValue)){
                        text = "Invalid Number of Entrants!";
                    }
                    if(!chkValueRange(getHousePercentValue)){
                        text = "Invalid House Percentage!";
                    }
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,text, duration);
                    //Log.d("Gravity", String.valueOf(txtEntranceFee.getGravity()));
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                    toast.show();
                }
                break;
        }

    }

    public String getHouseCut(Integer sFeeVal, String sPercentVal){
        return String.valueOf(Math.round(sFeeVal * getIntValue(sPercentVal)/100));
    }

    public String getPortion(String iInputVal, Double iPercent){
        Log.d("iInputVal", iInputVal);
        Log.d("iPercent", String.valueOf(iPercent));
        Double retFinal = getIntValue(iInputVal) * iPercent/100;
        Log.d("ReturnValue", String.valueOf(retFinal));
       return String.valueOf(retFinal);
    }
    public int getIntValue(String iInput){
        return Integer.parseInt(iInput);
    }

    public Boolean ChkIntValue(String iInput){
        try
        {
            if(Integer.parseInt(iInput) >= 0);
                return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public Boolean ChkIntGtThreeValue(String iInput){
        try
        {
            if(Integer.parseInt(iInput) > 3);
                return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public Boolean chkValueRange(String iInput){
        try
        {
            if(Integer.parseInt(iInput) > 0 && Integer.parseInt(iInput) < 101);
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }


}
