package com.alaory.wallmewallpaper

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.alaory.wallmewallpaper.api.Reddit_Api
import com.alaory.wallmewallpaper.api.Reddit_Api_Contorller
import com.alaory.wallmewallpaper.api.wallhaven_api
import com.alaory.wallmewallpaper.settings.Reddit_settings
import com.alaory.wallmewallpaper.settings.wallhaven_settings
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class wallmewallpaper : Application() {

    companion object{

        var num_post_in_Column = 2;
        var last_orein = Configuration.ORIENTATION_PORTRAIT;



        fun checkorein(){
            when(Resources.getSystem().configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT ->{
                    num_post_in_Column = 2;
                    last_orein = Configuration.ORIENTATION_PORTRAIT;
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    num_post_in_Column = 4;
                    last_orein = Configuration.ORIENTATION_LANDSCAPE;
                }
                Configuration.ORIENTATION_UNDEFINED -> {
                    num_post_in_Column = 2;
                    last_orein = Configuration.ORIENTATION_UNDEFINED;
                }
                else -> {}
            }
        }

        /*
            TODO add support for fullscreen and non fullscreen
         */

        fun HideSystemBar(window: Window){
            if(doFullscreen || true) {//not now :(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.setDecorFitsSystemWindows(false)
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.attributes.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
            }else{

            }
        }

        var doFullscreen = true;


        val executor: ExecutorService = Executors.newFixedThreadPool(2);
        val EBACKUP_CODE = 81723;
    }

    override fun onCreate() {
        super.onCreate();

        Reddit_Api.showfav = getSharedPreferences("settings",Context.MODE_PRIVATE).getBoolean("show_fav",true);
        doFullscreen = this.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("fullscreenapp",true);
        Reddit_Api.prefswords = this.getSharedPreferences("reddit_source_block",Context.MODE_PRIVATE).getString("sources","").toString();
    }
}