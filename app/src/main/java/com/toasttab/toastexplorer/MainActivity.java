package com.toasttab.toastexplorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private World world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b = (Button)findViewById(R.id.initMe);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                world = new World();
                world.initFromFile(getResources());
                renderRoom(world.getFirstRoom());
            }
        });

    }

    private void renderRoom(Room room) {
        TextView txtRoomName = (TextView)findViewById(R.id.txtRoomName);
        txtRoomName.setText(room.getName());

        ImageView imageView = (ImageView)findViewById(R.id.imgRoom);
        int resID = getResources().getIdentifier(room.getImageKey(), "drawable", getPackageName());
        if (resID != 0) {
            imageView.setImageDrawable(getResources().getDrawable(resID));
        }

        LinearLayout routeContainer = (LinearLayout)findViewById(R.id.routeContainer);
        routeContainer.removeAllViews();

        for (Route route : room.getRoutes()) {
            Button routeButton = new Button(this);
            routeButton.setText(route.getText());
            routeButton.setTag(route.getId());
            routeButton.setOnClickListener(this);
            routeContainer.addView(routeButton);

        }
    }

    @Override
    public void onClick(View view) {
        Integer newRoomId = (Integer)view.getTag();
        Room newRoom = world.getRoom(newRoomId);
        renderRoom(newRoom);
    }


}
