package marvelousboomerangmoose.shoppingwithfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class SalesReportActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
    }

    /**
     * Takes the user back to the home screen.
     * @param v the button being clicked
     */
    public void cancelOnClick(View v) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    /**
     * Add item button that allows user to enter in the item of interest if successful information
     * is populated in the appropriate fields.
     * Will take the user back to ItemListActivity if successful.
     * @param v - button to click
     */
    public void saleReportedOnClick(View v){
        String name =((EditText) findViewById(R.id.itemName)).getText().toString();
        String price = ((EditText) findViewById(R.id.itemSalePrice)).getText().toString();
        String location = ((EditText) findViewById(R.id.locationAddress)).getText().toString();
        String storeName = ((EditText) findViewById(R.id.storeName)).getText().toString();
        String quantity = ((EditText) findViewById(R.id.quantityAmount)).getText().toString();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        if (name.matches("")||price.matches("")||location.matches("")||storeName.matches("")||quantity.matches("")){
            alertDialogBuilder.setMessage("Please enter name, price, and location.").setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alertDialogBuilder.setTitle("Invalid Input");
            alertDialogBuilder.show();
            return;

        }
        Product p = new Product(name, Double.parseDouble(price), location, storeName, Integer.parseInt(quantity));
        ProductActivity.reportSale(p);
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sales_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}