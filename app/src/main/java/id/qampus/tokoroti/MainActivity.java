package id.qampus.tokoroti;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity=0;
    int prices=0;
    int priceAt=3000;
    public void setQty(View view){
        setQuantity(18*3+4/(2+2)-1);
        displayText(quantity);
    }

    private void setQuantity(int quantiti){
        quantity=quantiti;
    }

    public void plusQty(View view){
        setQuantity(quantity+1);
        displayText(quantity);
    }

    public void minusQty(View view){
        if(quantity<=0){
            Toast.makeText(MainActivity.this,"Barang sudah 0", Toast.LENGTH_LONG).show();
        }
        else{
            setQuantity(quantity-1);
            displayText(quantity);
        }
    }
    private void Names(){
        EditText namesField = (EditText)findViewById(R.id.name);
        String names = namesField.getText().toString();
        Toast.makeText(MainActivity.this, "Pesanan atas nama "+names + " Jumlah " +quantity +" Harga "+ prices, Toast.LENGTH_LONG).show();
    }

    private void SendMail(){
        EditText namesField = (EditText)findViewById(R.id.name);
        String names = namesField.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+names));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Faktur");
        intent.putExtra(Intent.EXTRA_TEXT,"Hallo, pesanan kamu :" +
                "\n Jumlah: "+quantity
                +"\n Harga: "+prices
                +"\n\n Selamat berbelanja kembali");
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    private void setPrices(int price){
        displayText(quantity);
        prices=quantity*price;
        displayPrice(prices);
        Names();
        SendMail();
    }

    public void setPrc(View view){
        setPrices(priceAt);
    }

    private void displayPrice(int prices){
        TextView priceTextView = (TextView)findViewById(R.id.priceView);
        priceTextView.setText(java.text.NumberFormat.getCurrencyInstance().format(prices));
    }


    private void displayText(int quantity) {
        TextView quantityTextView = (TextView)findViewById(R.id.quanityValue);
        quantityTextView.setText("" + quantity);
    }

}
