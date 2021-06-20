package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionAndOrientationFactory;

public class PositionWithNorthOrientation extends PositionAndOrientation {

    public PositionWithNorthOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionAndOrientation moveForward() {
        return PositionAndOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition() + 1);
    }

    @Override
    public PositionAndOrientation turnLeft() {
        return PositionAndOrientationFactory.createPositionWithWestOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }

    @Override
    public PositionAndOrientation turnRight() {
        return PositionAndOrientationFactory.createPositionWithEastOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
