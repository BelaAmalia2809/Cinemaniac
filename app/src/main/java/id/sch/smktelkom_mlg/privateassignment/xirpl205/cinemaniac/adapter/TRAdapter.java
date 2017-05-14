package id.sch.smktelkom_mlg.privateassignment.xirpl205.cinemaniac.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl205.cinemaniac.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl205.cinemaniac.model.TopRated;

/**
 * Created by user on 14/5/2017.
 */

public class TRAdapter extends RecyclerView.Adapter<TRAdapter.ViewHolder> {
    ArrayList<TopRated> topRatedList;
    ITopRatedAdapter mITopRatedAdapter;
    Context context;

    public TRAdapter(Context context, ArrayList<TopRated> topRatedList) {

        this.topRatedList = topRatedList;
        this.context = context;
        mITopRatedAdapter = (ITopRatedAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_1, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TopRated topRated = topRatedList.get(position);
        holder.tvJudul.setText(topRated.title);
        holder.tvDeskripsi.setText(topRated.overview);
        holder.tvDate.setText(topRated.release_date);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + topRated.poster_path)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        if (topRatedList != null)
            return topRatedList.size();
        return 0;

    }

    public interface ITopRatedAdapter {
        void doWatchTR(String title, String overview);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvJudul;
        TextView tvDeskripsi;
        TextView tvDate;
        ImageButton ibWatch;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDate = (TextView) itemView.findViewById(R.id.textViewDate);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewOverview);
            ibWatch = (ImageButton) itemView.findViewById(R.id.buttonWatch);

            ibWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TopRated topRated = topRatedList.get(getAdapterPosition());
                    mITopRatedAdapter.doWatchTR(topRated.title, topRated.overview);
                }
            });

        }
    }
}
