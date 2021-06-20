package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;

public class PositionWithEastOrientation extends PositionWithOrientation {

    public PositionWithEastOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionWithOrientation moveForward(int lawnXAxisSize, int lawnYAxisSize) {

        if (getXAxisPosition() < lawnXAxisSize) {
            return PositionWithOrientationFactory.createPositionWithEastOrientation(position.getXAxisPosition() + 1, position.getYAxisPosition());
        } else {
            return this;
        }
    }

    @Override
    public PositionWithOrientation turnLeft() {
        return PositionWithOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }

    @Override
    public PositionWithOrientation turnRight() {
        return PositionWithOrientationFactory.createPositionWithSouthOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
