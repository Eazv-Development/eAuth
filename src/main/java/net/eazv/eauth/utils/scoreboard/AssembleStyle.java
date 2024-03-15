package net.eazv.eauth.utils.scoreboard;

import lombok.Getter;

@Getter
public enum AssembleStyle {

    MODERN(false, 1);

    private final boolean descending;
    private final int startNumber;

    /**
     * Assemble Style.
     *
     * @param descending whether the positions are going down or up.
     * @param startNumber from where to loop from.
     */
    AssembleStyle(boolean descending, int startNumber) {
        this.descending = descending;
        this.startNumber = startNumber;
    }

}
