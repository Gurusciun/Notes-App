package com.anirudhshirodkar.notes.data;

import android.provider.BaseColumns;

/**
 * Created by anirudhshirodkar on 6/17/17.
 */

public class NotesDbContract {
    private NotesDbContract() {
    }

    public static final class NotesEntry implements BaseColumns{
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

}


