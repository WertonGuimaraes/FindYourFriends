package com.findyourfriends.activitys;

import java.util.List;

import com.les.findyourfriends.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class ViewGroupActivity extends Activity{
	private Context mContext;
	private ImageButton editar, grupos, meusGrupos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_groups);
		mContext = getApplicationContext();
              
        meusGrupos = (ImageButton) findViewById(R.id.meusGrupos);        
        meusGrupos.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext, MeusGruposActivity.class);
				startActivity(i);
			}
		});
        
        editar = (ImageButton) findViewById(R.id.editar);
        editar.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext, EditarActivity.class);
				startActivity(i);
			}
		});
        
        new CapturaJSON().execute();
        
        
		
	}
	
	private class CapturaJSON extends AsyncTask<Void, Void, List<Grupo>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            
            dialog = ProgressDialog.show(ViewGroupActivity.this, "Aguarde", "Gerando lista de grupos.");
        }

        @Override
        protected List<Grupo> doInBackground(Void... params) {
            return getJSON();
        }

        @Override
        protected void onPostExecute(List<Grupo> result) {
            super.onPostExecute(result);
            
            ListView list = (ListView) findViewById(R.id.listGroups);
            GrupoAdapter adapter = new GrupoAdapter(getApplicationContext(), result);
            list.setAdapter(adapter);
            
            dialog.dismiss();
        }

        private List<Grupo> getJSON() {
            JSONParse parser = new JSONParse("http://23.227.167.93:8085/findYouFriends/grupo/listGroups");
            return parser.getGruposBD();
        }
    }
	
	
	
}


