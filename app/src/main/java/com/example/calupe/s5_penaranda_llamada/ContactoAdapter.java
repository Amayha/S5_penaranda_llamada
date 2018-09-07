package com.example.calupe.s5_penaranda_llamada;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {

    ArrayList<Contacto> contactos = null;
    Context contexto;
    static final int Request_phone_call = 1;
    static LayoutInflater inflater = null;


    public ContactoAdapter(Context activity){
        this.contexto = activity;
        contactos = new ArrayList<>();
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return contactos.size();
    }

    @Override
    public Object getItem(int i) {

        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    //Generar un renglon por objeto
    //position = posicion del arreglo

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View renglon = inflater.inflate(R.layout.lista_tele,null);
        TextView item_nombre = renglon.findViewById(R.id.item_nombre);
        final TextView item_telefono = renglon.findViewById(R.id.item_telefono);

        ImageButton item_llamar = renglon.findViewById(R.id.item_llamar);
        //Para poder identificar donde se hace click en el evento OnclickLisitener
        item_llamar.setTag(position);

        ImageButton item_eliminar = renglon.findViewById(R.id.item_eliminar);
        item_eliminar.setTag(position);


        item_nombre.setText(contactos.get(position).getNombre());
        item_telefono.setText(contactos.get(position).getTelefono());


        item_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String telefono = contactos.get((int) view.getTag()).getTelefono();
                String dial;
                if(telefono.trim().length() > 0){
                    dial = "tel:" + telefono;
                }else{
                    //No existe numero para llamar
                    return;
                }
                Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse(dial));

                //llamar.setData(Uri.parse("tel: " + telefono));

                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    if(ContextCompat.checkSelfPermission((Activity)contexto, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) contexto, new String[]{Manifest.permission.CALL_PHONE},Request_phone_call);
                    }else {
                        contexto.startActivity(llamar);
                    }
                }

            }
        });

        item_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactos.remove((int) v.getTag());
                notifyDataSetChanged();
            }
        });
        return renglon;
    }


    public void agregarContacto(Contacto contacto){
        contactos.add(contacto);
        notifyDataSetChanged();
    }




}
