package com.cookandroid.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//activity_main.xml 의 recyclerview 에 넣을 데이터를 어뎁터로 설정
//MainActivity 에서 받아온 데이터를 가공하여 넘겨준다
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {
    private List<Phone> phoneList;

    //받아온 데이터(리스트)를 멤버변수리스트에 넣는다(저장)
    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    //상속받은(커스텀)MyViewHolder 를 설정한다
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txId,txName, txTel; //데이터를 추가할 id 를 선언하고

        //바인딩
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txId= itemView.findViewById(R.id.txId);
            this.txName= itemView.findViewById(R.id.txName);
            this.txTel= itemView.findViewById(R.id.txTel);
        }
    }
    //insert

    //list

    //update

    //remove

    //뷰로 사용할 xml 를 설정하고 MyViewHolder 변수에 넣고 변수를 반환한다
    @NonNull
    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_list,parent,false);

        MyViewHolder viewHolder= new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.MyViewHolder holder, int position) {
        //넘겨받은 데이터(리스트)의 현재 값을 객체로 받아서
        Phone phone= phoneList.get(position);

        //받은 데이터의 값을 id 값에 맞게 넣는다
        holder.txId.setText(Long.toString(phone.getId()));
        holder.txName.setText(phone.getName());
        holder.txTel.setText(phone.getTel());
    }

    //데이터가 있으면 데이터가 있는 만큼의 개수를 반환하고 없으면 0를 반환한다
    @Override
    public int getItemCount() {
        return phoneList== null? 0:phoneList.size();
    }
}
