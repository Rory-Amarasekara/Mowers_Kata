package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionAndOrientationFactory;

public class PositionWithEastOrientation extends PositionAndOrientation {

    public PositionWithEastOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionAndOrientation moveForward() {
        return PositionAndOrientationFactory.createPositionWithEastOrientation(position.getXAxisPosition() + 1, position.getYAxisPosition());
    }

    @Override
    public PositionAndOrientation turnLeft() {
        return PositionAndOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }

    @Override
    public PositionAndOrientation turnRight() {
        return PositionAndOrientationFactory.createPositionWithSouthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
