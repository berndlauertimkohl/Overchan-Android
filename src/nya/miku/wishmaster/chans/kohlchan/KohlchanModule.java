/*
 * Overchan Android (Meta Imageboard Client)
 * Copyright (C) 2014-2016  miku-nyan <https://github.com/miku-nyan>
 *     
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nya.miku.wishmaster.chans.kohlchan;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import nya.miku.wishmaster.R;
import nya.miku.wishmaster.api.AbstractVichanModule;
import nya.miku.wishmaster.api.interfaces.CancellableTask;
import nya.miku.wishmaster.api.interfaces.ProgressListener;
import nya.miku.wishmaster.api.models.BoardModel;
import nya.miku.wishmaster.api.models.SendPostModel;
import nya.miku.wishmaster.api.models.SimpleBoardModel;
import nya.miku.wishmaster.api.models.UrlPageModel;
import nya.miku.wishmaster.api.util.ChanModels;

public class KohlchanModule extends AbstractVichanModule {
    private static final String CHAN_NAME = "kohlchan.net";
    private static final SimpleBoardModel[] BOARDS = new SimpleBoardModel[] {
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "b", "b", "VIP", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "int", "int", "VIP", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "ernst", "ernst", "VIP", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "helmut", "helmut", "VIP", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "a", "Animu", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "c", "Computer&Technik", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "d", "Drogen", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "danisch", "Danisch", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "e", "Essen&Trinken", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "f", "Fahrzeuge", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "fb", "Frag Bernd", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "fit", "Fitness", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "l", "Literatur", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "mali", "Kreatives&Malen", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "med", "Medizin", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "n", "Natur", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "ng", "NG", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "pol", "Politik&Philosophie", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "prog", "Programmieren", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "s", "Schönes", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "tv", "TV&VHS&Musik", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "v", "Videospiele", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "we", "Weltschmerz", "Allgemein", false),
            ChanModels.obtainSimpleBoardModel(CHAN_NAME, "x", "X-Factor", "Allgemein", false),

    };

    public KohlchanModule(SharedPreferences preferences, Resources resources) {
        super(preferences, resources);
    }
    
    @Override
    public String getChanName() {
        return CHAN_NAME;
    }
    
    @Override
    public String getDisplayingName() {
        return "Kohlchan";
    }
    
    @Override
    public Drawable getChanFavicon() {
        return ResourcesCompat.getDrawable(resources, R.drawable.favicon_kohlchan, null);
    }
    
    @Override
    protected String getUsingDomain() {
        return "kohlchan.net";
    }

    @Override
    protected boolean canHttps() {
        return true;
    }
    
    @Override
    protected SimpleBoardModel[] getBoardsList() {
        return BOARDS;
    }
    
    @Override
    public BoardModel getBoard(String shortName, ProgressListener listener, CancellableTask task) throws Exception {
        BoardModel model = super.getBoard(shortName, listener, task);
        model.attachmentsMaxCount = 4;
        model.allowCustomMark = true;
        model.customMarkDescription = "Spoiler";
        return model;
    }
    
    @Override
    public String sendPost(SendPostModel model, ProgressListener listener, CancellableTask task) throws Exception {
        super.sendPost(model, listener, task);
        return null;
    }
    
    @Override
    public UrlPageModel parseUrl(String url) throws IllegalArgumentException {
        return super.parseUrl(url.replaceAll("-\\w+.*html", ".html"));
    }

    @Override
    public String fixRelativeUrl(String url) {
        if (url.startsWith("?/")) url = url.substring(1);
        return super.fixRelativeUrl(url);
    }
}
