package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;

public class PositionWithSouthOrientation extends PositionWithOrientation {

    public PositionWithSouthOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionWithOrientation forwardMovement() {
        return PositionWithOrientationFactory.createPositionWithSouthOrientation(position.getXAxisPosition(), position.getYAxisPosition() - 1);
    }

    @Override
    public PositionWithOrientation turnLeft() {
        return PositionWithOrientationFactory.createPositionWithEastOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }

    @Override
    public PositionWithOrientation turnRight() {
        return PositionWithOrientationFactory.createPositionWithWestOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
