package domain.lawn.mower.position.and.orientation;

import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;

public class PositionWithNorthOrientation extends PositionWithOrientation {

    public PositionWithNorthOrientation(Position position) {
        super(position);
    }

    @Override
    public PositionWithOrientation moveForward(int lawnXAxisSize, int lawnYAxisSize) {

        if (getYAxisPosition() < lawnYAxisSize) {
            return PositionWithOrientationFactory.createPositionWithNorthOrientation(position.getXAxisPosition(), position.getYAxisPosition() + 1);
        } else {
            return this;
        }
    }

    @Override
    public PositionWithOrientation turnLeft() {
        return PositionWithOrientationFactory.createPositionWithWestOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }

    @Override
    public PositionWithOrientation turnRight() {
        return PositionWithOrientationFactory.createPositionWithEastOrientation(position.getXAxisPosition(), position.getYAxisPosition());
    }
}
