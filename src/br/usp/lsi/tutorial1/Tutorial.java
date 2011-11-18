package br.usp.lsi.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Tutorial extends Activity {
    /** Called when the activity is first created. */
    
	private EditText textNome;
	private EditText textTel;
	private DBHelp dbHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dbHelper = new DBHelp(this);
        dbHelper.open();
        textNome = (EditText) findViewById(R.id.textNome);
        textTel = (EditText) findViewById(R.id.textTelefone);
    }
	
	public void onDestroy(){
		super.onDestroy();
		if(dbHelper != null){
			dbHelper.close();
		}
	}
    
    public void OKClickHandler(View view){
    	if(textNome.getText().toString() == "" || textTel.getText().toString() == ""){
    		Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
    	}else{
    		dbHelper.criaContato(textNome.getText().toString(), textTel.getText().toString());
    		textNome.setText("");
    		textTel.setText("");
    		Toast.makeText(this, "Contato salvo.", Toast.LENGTH_SHORT).show();
    	}    	
    }
    
    public void LimparClickHandler(View view){
    	textNome.setText("");
    	textTel.setText("");
    }
    
    public void AgendaClickHandler(View view){
    	startActivity(new Intent(this,ListaContatos.class));
    }
}