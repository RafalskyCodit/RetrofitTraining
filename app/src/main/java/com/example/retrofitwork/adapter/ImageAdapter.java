package com.example.retrofitwork.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofitwork.R;
import com.example.retrofitwork.model.Hit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private List<Hit> hits;
    private Context context;

    public ImageAdapter(Context context){
        hits = new ArrayList<>();
        this.context = context;
    }

    public void updateHits(List<Hit> newHits){
        hits = newHits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return new ImageHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int position) {
        imageHolder.bind(hits.get(position));
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView likes;
        private TextView favourites;
        private TextView views;
        private TextView downloads;
        private TextView user;

        public ImageHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.image_item, viewGroup, false));
            init();
        }

        private void init() {
            image = itemView.findViewById(R.id.image_request);
            likes = itemView.findViewById(R.id.likes);
            favourites = itemView.findViewById(R.id.favourites);
            views = itemView.findViewById(R.id.views);
            downloads = itemView.findViewById(R.id.downloads);
            user = itemView.findViewById(R.id.user_name);
        }

        public void bind(Hit hit){
            Picasso.get().load(hit.getLargeImageURL()).error(R.mipmap.ic_launcher).into(image);
            likes.setText(String.format(context.getString(R.string.likes), hit.getLikes()));
            downloads.setText(String.format(context.getString(R.string.downloads), hit.getDownloads()));
            favourites.setText(String.format(context.getString(R.string.favourites), hit.getFavorites()));
            views.setText(String.format(context.getString(R.string.views), hit.getViews()));

            user.setText(hit.getUser());
        }
    }
}
