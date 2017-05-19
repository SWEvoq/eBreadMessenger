package swevoq.ebread.com.Libraries.FATTSLib;

import android.media.MediaPlayer;

/**
 * Created by Teslaru Nicolae on 27/04/2017.
 */

public class PlayerBread {
        private static PlayerBread mInstance = null;

        private MediaPlayer player;

        private PlayerBread(){
            player = new MediaPlayer();
        }

        public static PlayerBread getInstance(){
            if(mInstance == null)
            {
                mInstance = new PlayerBread();
            }
            return mInstance;
        }

        public MediaPlayer getPlayer(){
            return player;
        }
}
