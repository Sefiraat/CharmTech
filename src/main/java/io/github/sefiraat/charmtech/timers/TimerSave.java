package io.github.sefiraat.charmtech.timers;

import io.github.sefiraat.charmtech.CharmTech;

import java.util.TimerTask;

public class TimerSave extends TimerTask {

    public final CharmTech parent;

    public TimerSave(CharmTech parent) {
        this.parent = parent;
    }

    public void run() {
        parent.saveCharmItemsConfig();
    }
}