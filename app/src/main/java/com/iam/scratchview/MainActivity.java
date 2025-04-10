package com.iam.scratchview;

import static android.view.View.INVISIBLE;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cooltechworks.views.ScratchImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    CardView scratchCard;
    ScratchImageView scratchView;
    ImageView imgScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        scratchCard = findViewById(R.id.scratchCard);
        scratchView = findViewById(R.id.scratchView);
        imgScale = findViewById(R.id.imgScale);

        Animation scaleX = AnimationUtils.loadAnimation(this,R.anim.scale_x);
        imgScale.startAnimation(scaleX);

        scratchView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                imgScale.clearAnimation();
                imgScale.setVisibility(INVISIBLE);
                scratchCard.setAnimation(scaleX);
                Toast.makeText(MainActivity.this, "Prize Reveled", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Congratulations 🎉! You have won 999 Rs.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
                if (percent > 0.6f) { // After 60% automatic whole reveled
                    siv.reveal();
                }
            }
        });

    }
}