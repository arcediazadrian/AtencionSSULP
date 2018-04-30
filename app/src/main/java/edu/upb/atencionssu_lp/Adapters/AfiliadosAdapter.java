package edu.upb.atencionssu_lp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.upb.atencionssu_lp.Modelos.Afiliado;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 4/30/2018.
 */

public class AfiliadosAdapter extends RecyclerView.Adapter<AfiliadosAdapter.ViewHolder> {
    private ArrayList<Afiliado> datos;
    private Context context;

    public AfiliadosAdapter(Context context) {
        datos = new ArrayList<Afiliado>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_afiliado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Afiliado afiliado = datos.get(position);
        holder.nombreTextView.setText(afiliado.getNombre());
        holder.idTextView.setText(afiliado.getId());
        Glide.with(context).load(afiliado.getImagenPerfilURL()).into(holder.imagenPerfilImageView);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void colocarDatos(ArrayList<Afiliado> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public void addAfiliado(Afiliado afiliado) {
        datos.add(afiliado);
        notifyDataSetChanged();
    }

    public void clear() {
        datos.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView idTextView;
        ImageView imagenPerfilImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            idTextView = (TextView) itemView.findViewById(R.id.idTextView);
            imagenPerfilImageView = (ImageView) itemView.findViewById(R.id.imagenPerfilImageView);
        }
    }
}
