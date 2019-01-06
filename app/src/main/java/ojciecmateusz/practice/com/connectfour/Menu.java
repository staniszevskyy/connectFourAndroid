package ojciecmateusz.practice.com.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button singleButton = (Button) findViewById(R.id.singleButton);
        Button bo3Button = (Button) findViewById(R.id.bo3Button);
        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                intent.putExtra("bo3", false);
                startActivity(intent);
            }
        });

        bo3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                intent.putExtra("bo3", true);
                startActivity(intent);
            }
        });

    }
}
