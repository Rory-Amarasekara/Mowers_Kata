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

    public abstract PositionWithOrientation forwardMovement();

    public abstract PositionWithOrientation turnLeft();

    public abstract PositionWithOrientation turnRight();

    public Position getPosition(){
        return position;
    }

    public int getXAxisPosition(){
        return position.getXAxisPosition();
    }

    public int getYAxisPosition(){
        return position.getYAxisPosition();
    }
}
