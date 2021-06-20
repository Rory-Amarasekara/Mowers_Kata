package domain.lawn.mower.position.and.orientation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class PositionWithOrientation {

    protected final Position position;

    protected PositionWithOrientation(Position position) {
        this.position = position;
    }

    public abstract PositionWithOrientation moveForward(int lawnXAxisSize, int lawnYAxisSize);

    public abstract PositionWithOrientation turnLeft();

    public abstract PositionWithOrientation turnRight();

    public int getXAxisPosition(){
        return position.getXAxisPosition();
    }

    public int getYAxisPosition(){
        return position.getYAxisPosition();
    }
}
