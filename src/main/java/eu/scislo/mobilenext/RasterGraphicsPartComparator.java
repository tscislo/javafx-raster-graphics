package eu.scislo.mobilenext;

import java.util.Comparator;

public class RasterGraphicsPartComparator implements Comparator<RasterGraphicsPart> {

    public int compare(RasterGraphicsPart a, RasterGraphicsPart b) {
        if (a.getMeanRedPart() > b.getMeanRedPart()) {
            return -1;
        } else if (a.getMeanRedPart() < b.getMeanRedPart()) {
            return 1;
        } else {
            return 0;
        }
    }

}
