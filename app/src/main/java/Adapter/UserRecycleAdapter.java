package Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jatinderkumar.spla.R;

import java.util.List;

import Model.User;

/**
 * Created by Jatinder Kumar on 02-11-2017.
 */

public class UserRecycleAdapter  extends  RecyclerView.Adapter<UserRecycleAdapter.UserViewHolder>{

    private List<User> userList ;

    public UserRecycleAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserRecycleAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.user_recycle,parent,false);
        return  new UserViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(UserRecycleAdapter.UserViewHolder holder, int position) {

        holder.textViewName.setText( userList.get( position ).getName() );
        holder.textViewEmail.setText( userList.get( position ).getEmail() );
        holder.textViewPhone.setText( userList.get( position ).getPhone_number() );
        holder.textViewPassword.setText( userList.get( position ).getPassword() );

    }

    @Override
    public int getItemCount() {

        Log.v( UserRecycleAdapter.class.getSimpleName(),""+userList.size() );
        return userList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewName,textViewEmail,textViewPhone,textViewPassword;

        public UserViewHolder(View itemView) {
            super( itemView );
            textViewName =(TextView) itemView.findViewById(R.id.textViewName);
            textViewEmail =(TextView) itemView.findViewById(R.id.textViewEmail);
            textViewPhone =(TextView) itemView.findViewById(R.id.textViewPhone);
            textViewPassword =(TextView) itemView.findViewById(R.id.textViewPassword);
        }
    }
}
