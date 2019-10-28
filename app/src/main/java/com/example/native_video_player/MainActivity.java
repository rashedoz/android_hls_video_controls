package com.example.native_video_player;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int HI_BITRATE_720 = 2097152;
        int HI_BITRATE_480 = 768000;
        int MI_BITRATE_360 = 282624;
        int MI_BITRATE_240 = 153600;
        int LO_BITRATE_144 = 97280;

        PlayerView playerView = findViewById(R.id.video_player);

        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        DefaultTrackSelector.Parameters defaultTrackParam = trackSelector.buildUponParameters()
                .setMaxVideoBitrate(LO_BITRATE_144)
                .setForceHighestSupportedBitrate(true)
                .build();
        trackSelector.setParameters(defaultTrackParam);

        //////////// Initialize video with 144 Resolution ///////////////
        /*DefaultTrackSelector.Parameters parametersAtStart = trackSelector.buildUponParameters()
                .setMaxVideoBitrate(LO_BITRATE_144)
                .setForceHighestSupportedBitrate(true)
                .build();
        trackSelector.setParameters(parametersAtStart);*/


        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        playerView.setPlayer(player);

        DefaultDataSourceFactory fac = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));

        HlsMediaSource videoSource = new HlsMediaSource.Factory(fac).createMediaSource(
                Uri.parse("https://rashed-test-s3.s3.ap-south-1.amazonaws.com/Tests/Lvideo1569663696/Lvideo1569663696.m3u8")
        );

        player.prepare(videoSource);

        //Auto start video when source is ready
        player.setPlayWhenReady(true);



        //////// Next Button Click /////////////
        findViewById(R.id.nextBTn).setOnClickListener(v -> {
            HlsMediaSource videoSource2 = new HlsMediaSource.Factory(fac).createMediaSource(
                    Uri.parse("https://rashed-test-s3.s3.ap-south-1.amazonaws.com/Tests/phy03/phy03.m3u8")
            );
             DefaultTrackSelector.Parameters parametersAtStart = trackSelector.buildUponParameters()
                .setMaxVideoBitrate(MI_BITRATE_240)
                .setForceHighestSupportedBitrate(true)
                .build();
             trackSelector.setParameters(parametersAtStart);

            player.prepare(videoSource2);
        });

        //////// Next Button Click video 3/////////////
        findViewById(R.id.video3).setOnClickListener(v -> {
            HlsMediaSource videoSource3 = new HlsMediaSource.Factory(fac).createMediaSource(
                    Uri.parse("https://rashed-test-s3.s3.ap-south-1.amazonaws.com/Tests/s2/s2.m3u8")
            );

            DefaultTrackSelector.Parameters parametersAtStart = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(MI_BITRATE_360)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parametersAtStart);

            player.prepare(videoSource3);
        });


        findViewById(R.id.quality_lo_140).setOnClickListener(v -> {
            DefaultTrackSelector.Parameters parameters = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(LO_BITRATE_144)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parameters);
        });

        findViewById(R.id.quality_mi_244).setOnClickListener(v -> {
            DefaultTrackSelector.Parameters parameters = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(MI_BITRATE_240)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parameters);
        });

        findViewById(R.id.quality_mi_360).setOnClickListener(v -> {
            DefaultTrackSelector.Parameters parameters = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(MI_BITRATE_360)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parameters);
        });

        findViewById(R.id.quality_hi_480p).setOnClickListener(v -> {
            DefaultTrackSelector.Parameters parameters = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(HI_BITRATE_480)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parameters);
        });

        findViewById(R.id.quality_hi_720p).setOnClickListener(v -> {
            DefaultTrackSelector.Parameters parameters = trackSelector.buildUponParameters()
                    .setMaxVideoBitrate(HI_BITRATE_720)
                    .setForceHighestSupportedBitrate(true)
                    .build();
            trackSelector.setParameters(parameters);
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
