package domain.lawn;

import domain.lawn.mower.MowerId;
import domain.lawn.mower.Mowers;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.ToString;

import java.util.Map;

@ToString
public class LawnWithMowers {

    private int xAxisSize;
    private int yAxisSize;
    private Mowers mowers;

    private LawnWithMowers(int xAxisSize, int yAxisSize, Mowers mowers) {
        this.xAxisSize = xAxisSize;
        this.yAxisSize = yAxisSize;
        this.mowers = mowers;
    }

    public static LawnWithMowers create(int xAxisSize, int yAxisSize, Mowers mowers) {
        return new LawnWithMowers(xAxisSize, yAxisSize, mowers);
    }

    public Map<MowerId, PositionWithOrientation> followMowerNavigationPlansAndGetFinalPositionAndOrientation() {
       return mowers.followMowerNavigationPlansAndGetFinalPositionAndOrientation();
    }
}
