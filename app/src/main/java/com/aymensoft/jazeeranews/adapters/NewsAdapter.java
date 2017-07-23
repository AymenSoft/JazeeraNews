package com.aymensoft.jazeeranews.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aymensoft.jazeeranews.R;
import com.aymensoft.jazeeranews.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News>{

    Context context;
    int resource;

    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
            holder.imgNews=(ImageView)view.findViewById(R.id.img_news);
            holder.tvAuthor=(TextView)view.findViewById(R.id.tv_author);
            holder.tvDescription=(TextView)view.findViewById(R.id.tv_description);
            view.setTag(holder);
        }else {
            holder=(Holder) view.getTag();
        }

        holder.tvAuthor.setText(getItem(position).getAuthor());
        holder.tvDescription.setText(getItem(position).getTitle());

        Picasso.with(context)
                .load(getItem(position).getUrlToImage())
                .fit()
                .into(holder.imgNews);

        return view;
    }

    private class Holder{
        ImageView imgNews;
        TextView tvAuthor, tvDescription;
    }
}
