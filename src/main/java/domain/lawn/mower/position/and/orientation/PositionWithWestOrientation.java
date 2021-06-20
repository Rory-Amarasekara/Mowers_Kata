package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionAndOrientationFactory;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PositionWithWestOrientation extends PositionAndOrientation {

    public PositionWithWestOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionAndOrientation moveForward() {
        return PositionAndOrientationFactory.createPositionWithWestOrientation(position.getXAxisPosition() - 1, position.getYAxisPosition());
    }

    @Override
    public PositionAndOrientation turnLeft() {
        return PositionAndOrientationFactory.createPositionWithSouthOrientation(position.getXAxisPosition(), position.getYAxisPosition());

    }

    @Override
    public PositionAndOrientation turnRight() {
        return PositionAndOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
