package domain.lawn.mower.position.and.orientation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class PositionAndOrientation {

    protected final Position position;

    protected PositionAndOrientation(Position position) {
        this.position = position;
    }

    public abstract PositionAndOrientation moveForward();

    public abstract PositionAndOrientation turnLeft();

    public abstract PositionAndOrientation turnRight();

    public int getXAxisPosition() {
        return position.getXAxisPosition();
    }

    public int getYAxisPosition() {
        return position.getYAxisPosition();
    }

    public void setXAxisPosition(int xAxisPosition) {
        position.setXAxisPosition(xAxisPosition);
    }

    public void setYAxisPosition(int yAxisPosition) {
        position.setYAxisPosition(yAxisPosition);
    }
}
