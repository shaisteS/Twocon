package winep.ir.twocon.Presenter.LevelPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.twocon.DataModel.Level;
import winep.ir.twocon.Presenter.QuestionPage.QuestionActivity;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class LevelRecyclerViewAdapter extends RecyclerView.Adapter<LevelRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Level> allLevel;
    private Context context;

    public LevelRecyclerViewAdapter(Context context, ArrayList<Level> levels){
        this.context=context;
        allLevel=levels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_recycler_view_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.levelNumber.setText(Integer.toString(allLevel.get(position).getLevelNumber()));
        if (allLevel.get(position).getLevelStatus()==0)
            Utilities.getInstance().customView(holder.levelNumber, Color.GREEN,Color.GREEN);
        else if (allLevel.get(position).getLevelStatus()==1) {
            Utilities.getInstance().customView(holder.levelNumber, Color.RED, Color.RED);
            holder.itemView.setClickable(false);
        }
        else if (allLevel.get(position).getLevelStatus()==2) {
            Utilities.getInstance().customView(holder.levelNumber, Color.GRAY, Color.GRAY);
            holder.itemView.setClickable(false);
        }

        holder.levelTotalQuestionNumber.setText(Integer.toString(allLevel.get(position).getLevelTotalQuestion()));
        holder.levelReadyQuestionNumber.setText(Integer.toString(allLevel.get(position).getLevelReadyQuestion()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allLevel.get(position).getLevelStatus()==0){
                    Intent intent=new Intent(context, QuestionActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allLevel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView levelNumber;
        private TextView levelReadyQuestionNumber;
        private TextView levelTotalQuestionNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            levelNumber=(TextView)itemView.findViewById(R.id.txt_level_number);
            levelReadyQuestionNumber=(TextView)itemView.findViewById(R.id.txt_ready);
            levelTotalQuestionNumber=(TextView)itemView.findViewById(R.id.txt_total);
        }
    }
}
