package com.example.nmcarrol_travelcalc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClaimSummary extends Activity {
	private Claim c;
	private ArrayList<Expense> Exp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_view);
		
		 c = (Claim)getIntent().getSerializableExtra("Sum");
		 Exp = c.getExp();
		 Iterator<Expense> it = Exp.iterator();
		 Hashtable<String, Double> numbers= new Hashtable<String, Double>();
		 while(it.hasNext())
		 {
		     Expense obj = it.next();
		     double amt = Double.parseDouble(obj.getAmount());
		     if(numbers.contains(obj.getCurrency())){
		     numbers.put(obj.getCurrency(), (numbers.get(obj.getCurrency())+amt));
		     }
		 }
		 
		 
		EditText tt = (EditText)findViewById(R.id.editText1);
		tt.setText(c.getName()+" Spending: \n");
		
			 
	}
	
	public void shareClaim(View view){
		EditText tt = (EditText)findViewById(R.id.editText1);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setData(Uri.parse("mailto:"));
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"nmcarrol@ualberta.ca"});
		intent.putExtra(Intent.EXTRA_SUBJECT, c.getName()+" Claim: \n");
		intent.putExtra(Intent.EXTRA_TEXT   , tt.getText().toString());
		try {
		    startActivity(Intent.createChooser(intent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
}
