package domain.lawn;

import domain.lawn.mower.MowerId;
import domain.lawn.mower.Mowers;
import domain.lawn.mower.position.and.orientation.PositionAndOrientation;
import lombok.ToString;

import java.util.Map;

@ToString
public class Lawn {

    private int xAxisSize;
    private int yAxisSize;
    private Mowers mowers;

    private Lawn(int xAxisSize, int yAxisSize, Mowers mowers) {
        this.xAxisSize = xAxisSize;
        this.yAxisSize = yAxisSize;
        this.mowers = mowers;
    }

    public static Lawn create(int xAxisSize, int yAxisSize, Mowers mowers) {
        return new Lawn(xAxisSize, yAxisSize, mowers);
    }

    public Map<MowerId, PositionAndOrientation> followMowerNavigationPlansAndGetFinalPositionAndOrientation() {
       return mowers.followMowerNavigationPlansAndGetFinalPositionAndOrientation();
    }
}
