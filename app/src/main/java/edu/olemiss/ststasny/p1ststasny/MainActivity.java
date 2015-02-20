package edu.olemiss.ststasny.p1ststasny;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity
{
    private Spinner S_tipSelection;
    private Button B_btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Handle the menu.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnSpinnerItemSelection()
    {
        S_tipSelection = (Spinner) findViewById(R.id.S_tipSelection);
        S_tipSelection.setOnItemSelectedListener(new SelectedListener2());
    }

    //Get the selected dropdown list value
    public void addListenerOnButton()
    {
        S_tipSelection = (Spinner) findViewById(R.id.S_tipSelection);
        S_tipSelection = (Spinner) findViewById(R.id.S_tipSelection);
        B_btnSubmit = (Button) findViewById(R.id.B_btnSubmit);

        B_btnSubmit.setOnClickListener(
            new View.OnClickListener()
            {
                //When Calculate button is clicked
                @Override
                public void onClick(View v)
                    {
                    DecimalFormat precision = new DecimalFormat("0.00");

                    //Gets selected item and sets it to selItem
                    String selItem = String.valueOf(S_tipSelection.getSelectedItem());

                    //Toast tip percentage selected after the Calculate button is clicked
                    Toast.makeText(MainActivity.this,
                            "OnClickListener : " +
                                    "\nSpinner 1 : " + selItem, Toast.LENGTH_SHORT).show();

                    //Gets amount entered and sets it to amount
                    EditText ET_enterAmount = (EditText) findViewById(R.id.ET_enterAmount);
                    double amount = Double.parseDouble(ET_enterAmount.getText().toString());

                    //Post tip total
                    TextView TV_tipTotalEdit = (TextView) findViewById(R.id.TV_tipTotalEdit);
                    TV_tipTotalEdit.setText(" $" + precision.format(calculateTip(amount, selItem)));

                    //Post final total
                    TextView TV_finalTotalEdit = (TextView) findViewById(R.id.TV_finalTotalEdit);
                    TV_finalTotalEdit.setText(" $" + precision.format(calculateTotal(amount, selItem)));

                    //Toast tip total
                    Toast.makeText(MainActivity.this,
                            "Result : " +
                                    "\nTip Total : " + precision.format(calculateTip(amount, selItem)), Toast.LENGTH_SHORT).show();
                    //Toast final total
                    Toast.makeText(MainActivity.this,
                            "Result : " +
                                    "\nFinal Total : " + precision.format(calculateTotal(amount, selItem)), Toast.LENGTH_SHORT).show();
            }
            });
    }

    //This method calculates the final total using the tip percentage and the amount entered
    public double calculateTotal(double subTotal, String S_tipSelection)
    {
        double finalTotal = ((Double.parseDouble(S_tipSelection.toString()) / 100.0) * subTotal) + subTotal;
        return finalTotal;
    }

    //This method calculates the tip total using the tip percentage and the amount entered
    public double calculateTip(double subTotal, String S_tipSelection)
    {
        double tipTotal = ((Double.parseDouble(S_tipSelection.toString()) / 100.0) * subTotal);
        return tipTotal;
    }
}
