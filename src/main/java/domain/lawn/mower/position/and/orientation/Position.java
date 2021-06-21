package domain.lawn.mower.position.and.orientation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Position {

    private int xAxisPosition;

    private int yAxisPosition;

    private Position(int xAxisPosition, int yAxisPosition) {
        this.xAxisPosition = xAxisPosition;
        this.yAxisPosition = yAxisPosition;
    }

    public static Position create(int xAxisPosition, int yAxisPosition) {
        return new Position(xAxisPosition, yAxisPosition);
    }

    public int getYAxisPosition() {
        return yAxisPosition;
    }

    public int getXAxisPosition() {
        return xAxisPosition;
    }
}
