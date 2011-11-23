package br.usp.lsi.tutorial1;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
		
		preencherLista();
		registerForContextMenu(getListView());
	}

    private void preencherLista() {
        //Preenche a lista
		Cursor cursor = dbHelper.listaContatos();
		startManagingCursor(cursor);

		String[] from = new String[] {DBHelp.NOME, DBHelp.TELEFONE};
		int[] to = new int[] {android.R.id.text1, android.R.id.text2};	
		
		SimpleCursorAdapter contatos = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item, cursor, from, to);
		setListAdapter(contatos);
    }
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
	    Cursor item = dbHelper.buscaContato(id);
	    
	    String nome = item.getString(item.getColumnIndex(dbHelper.NOME));
	    String tel = item.getString(item.getColumnIndex(dbHelper.TELEFONE));
	    
        Toast.makeText(this,
            "Você selecionou\n" + nome +": "+ tel,
            Toast.LENGTH_SHORT).show();
    }

	/**
	 * Exibe um menu de contexto para o evento de clique longo em ListView 
	 */
	public void onCreateContextMenu(ContextMenu menu, View v,
	        ContextMenuInfo menuInfo) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        Cursor item = dbHelper.buscaContato(info.id);
        
        String nome = item.getString(item.getColumnIndex(dbHelper.NOME));
        String tel = item.getString(item.getColumnIndex(dbHelper.TELEFONE));

        menu.setHeaderTitle(nome);

        String[] menuItems = {
                "Telefone: " + tel,
                getResources().getString(R.string.menu_apagar) };

        for (int i = 0; i < menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Cursor cursor = dbHelper.buscaContato(info.id);
        
	    String nome = cursor.getString(cursor.getColumnIndex(dbHelper.NOME));
	    String tel = cursor.getString(cursor.getColumnIndex(dbHelper.TELEFONE));
        
	    if (item.getTitle() == getResources().getString(R.string.menu_apagar)) {
	        Toast.makeText(this,
	                nome + " apagado da lista",
	                Toast.LENGTH_SHORT).show();
            dbHelper.apagaContato(info.id);
            preencherLista();
        } else {
            Toast.makeText(this,
                    "Você selecionou\n" + nome +": "+ tel,
                    Toast.LENGTH_SHORT).show();
        }
        return true;
	}
}
