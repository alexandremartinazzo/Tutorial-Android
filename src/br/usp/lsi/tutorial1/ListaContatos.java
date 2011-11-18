package br.usp.lsi.tutorial1;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListaContatos extends ListActivity{
	
	private DBHelp dbHelper;
	 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.listacontatos);
		dbHelper = new DBHelp(this);
		dbHelper.open();
		
		//Preenche a lista
		Cursor cursor = dbHelper.listaContatos();
		startManagingCursor(cursor);

		String[] from = new String[] {DBHelp.NOME, DBHelp.TELEFONE};
		int[] to = new int[] {android.R.id.text1, android.R.id.text2};	
		
		SimpleCursorAdapter contatos = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item, cursor, from, to);
		setListAdapter(contatos);				
		registerForContextMenu(getListView());
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
	    Cursor item = dbHelper.buscaContato(id);
	    
	    String nome = item.getString(item.getColumnIndex(dbHelper.NOME));
	    String tel = item.getString(item.getColumnIndex(dbHelper.TELEFONE));
	    
        Toast.makeText(this,
            "Você selecionou\n" + nome +": "+ tel,
            Toast.LENGTH_SHORT).show();
    }

}
