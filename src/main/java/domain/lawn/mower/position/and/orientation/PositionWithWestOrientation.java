package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PositionWithWestOrientation extends PositionWithOrientation {

    public PositionWithWestOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionWithOrientation moveForward(int lawnXAxisSize, int lawnYAxisSize) {
        if (getXAxisPosition() > 0) {
            return PositionWithOrientationFactory.createPositionWithWestOrientation(position.getXAxisPosition() - 1, position.getYAxisPosition());
        } else {
            return this;
        }
    }

    @Override
    public PositionWithOrientation turnLeft() {
        return PositionWithOrientationFactory.createPositionWithSouthOrientation(position.getXAxisPosition(), position.getYAxisPosition());

    }

    @Override
    public PositionWithOrientation turnRight() {
        return PositionWithOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
