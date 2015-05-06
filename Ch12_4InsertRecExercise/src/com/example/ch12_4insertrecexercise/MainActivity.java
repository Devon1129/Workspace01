package com.example.ch12_4insertrecexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String DB_NAME = "Company";
	private static final int DBversion = 1;
//	private static final String TABLE_NAME = "Cus";
	private EditText etCusNo, etCusNa, etCusPho, etCusAdd;
	private Button btIns, btCancel;
	private CompDBHper dbHper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buildViews();
	}
	
	public void buildViews(){
		etCusNo = (EditText)findViewById(R.id.etIdNo);
		etCusNa = (EditText)findViewById(R.id.etIdNa);
		etCusPho = (EditText)findViewById(R.id.etIdPho);
		etCusAdd = (EditText)findViewById(R.id.etIdAdd);
		
		btIns = (Button)findViewById(R.id.btnIdAdd);
		btCancel = (Button)findViewById(R.id.btnIdReEnt);
		btIns.setOnClickListener(btInsListener);
		btCancel.setOnClickListener(btCancelListener);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(dbHper == null){
			dbHper = new CompDBHper(this, DB_NAME, null, DBversion);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close();
			dbHper = null;
		}
	}

	private OnClickListener btInsListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String CusNo = etCusNo.getText().toString().trim();
			String CusNa = etCusNa.getText().toString().trim();
			String CusPho = etCusPho.getText().toString().trim();
			String CusAdd = etCusAdd.getText().toString().trim();
			
			//注意:請加上判斷條件
			if(CusNo.equals("") || CusNa.equals("")){
				Toast.makeText(MainActivity.this,
					"請輸入欲新增的客戶資料!", 
					Toast.LENGTH_SHORT)
					.show();
				return;
			}
			
			String msg = null;
			long rowID = dbHper.insertRec(CusNo, CusNa, CusPho, CusAdd);
			if(rowID != -1){
				msg = "新增記錄 成功!\n" + "目前客戶資料表共有" + dbHper.RecCount() + "筆記錄!";
			}else{
				msg = "新增記錄 失敗!";
			}
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
	};

	private OnClickListener btCancelListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			etCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");
		}
	};
}
