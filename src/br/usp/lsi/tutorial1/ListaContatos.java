package br.usp.lsi.tutorial1;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class ListaContatos extends ListActivity{
	
	private DBHelp dbHelper;
	 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.listacontatos);
		dbHelper = new DBHelp(this);
		dbHelper.open();
		
		//Preenche a lista
		Cursor cursor = dbHelper.buscaContatos();
		startManagingCursor(cursor);

		String[] from = new String[] {DBHelp.NOME, DBHelp.TELEFONE};
		int[] to = new int[] {android.R.id.text1, android.R.id.text2};	
		
		SimpleCursorAdapter contatos = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item, cursor, from, to);
		setListAdapter(contatos);				
		registerForContextMenu(getListView());
	}
	
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (dbHelper != null) {
//			dbHelper.close();
//		}
//	}

}
