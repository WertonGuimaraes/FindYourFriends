package com.findyourfriends.activitys;

import java.util.List;

import com.les.findyourfriends.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class UsuarioAdapter extends BaseAdapter {

    private List<Usuario> mUsuario;
    private LayoutInflater mInflater;

    public UsuarioAdapter(Context context, List<Usuario> usuario) {
        mInflater = LayoutInflater.from(context);
        mUsuario = usuario;
    }

    @Override
    public int getCount() {
        return mUsuario.size();
    }

    @Override
    public Object getItem(int index) {
        return mUsuario.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.usuario_adapter_item, null);
        Usuario usuario = mUsuario.get(posicao);
        
        TextView tvNome = (TextView) view.findViewById(R.id.nomeUsuario);
        tvNome.setText(usuario.getNome());
        
        return view;
    }
}