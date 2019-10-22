package edu.tamu.csce315_908_t4.gui.backend.arguments;

import edu.tamu.csce315_908_t4.gui.backend.Tconst;

public class ChoiceArg implements IArgument {
    public final Tconst seriesTitle;

    public ChoiceArg(Tconst seriesTitle) {
        this.seriesTitle = seriesTitle;
    }
}
