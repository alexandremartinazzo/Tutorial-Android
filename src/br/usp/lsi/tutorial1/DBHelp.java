package br.usp.lsi.tutorial1;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelp extends SQLiteOpenHelper{
	
	public static final String ID = "_id";
	public static final String NOME = "nome";
	public static final String TELEFONE = "telefone";
	private static final String TABELA = "tutorial1";	
	private static final String NOME_DB = "dadosaplicacao";
	private static final int VERSAO_DB = 1;
	private static final String CREATE_DB = "create table "+ TABELA + "("+ ID +  " integer primary key autoincrement, "
			+ NOME + " text not null,"+ TELEFONE +" text not null);";
	//private Context context;
	private SQLiteDatabase database;
	
	public DBHelp(Context context, String name, CursorFactory factory, int version) {
		super(context, NOME_DB, null, VERSAO_DB);
	}
	
	public DBHelp(Context c){
		super(c, NOME_DB, null, VERSAO_DB);
		//this.context = c;		
	}
	
	
	public void open() throws SQLException {
		this.database = getWritableDatabase();
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DB);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelp.class.getName(), "Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS tutorial1");
		onCreate(db);
	}
		
	/**
	 * Grava contato na base da dados do android
	 */
	public long criaContato(String nome, String tel) {
		ContentValues valores = new ContentValues();
		valores.put(NOME, nome);
		valores.put(TELEFONE, tel);
		return database.insert(TABELA, null, valores);
	}
	
	/**
	 * Lê todos os contatos da base de dados
	 */
	public Cursor buscaContatos() {
		return database.query(TABELA, new String[] {ID,	NOME, TELEFONE }, null, null, null, null, null);
	}

	
}
