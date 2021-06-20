package domain.lawn.mower.position.and.orientation.factory;

import domain.lawn.mower.position.and.orientation.*;

public class PositionAndOrientationFactory {

    private PositionAndOrientationFactory(){}

    public static PositionWithNorthOrientation createPositionWithNorthOrientation(int xAxisPosition, int yAxisPosition){
        return new PositionWithNorthOrientation(Position.create(xAxisPosition, yAxisPosition));
    }

    public static PositionWithSouthOrientation createPositionWithSouthOrientation(int xAxisPosition, int yAxisPosition){
        return new PositionWithSouthOrientation(Position.create(xAxisPosition, yAxisPosition));
    }

    public static PositionWithEastOrientation createPositionWithEastOrientation(int xAxisPosition, int yAxisPosition){
        return new PositionWithEastOrientation(Position.create(xAxisPosition, yAxisPosition));
    }

    public static PositionWithWestOrientation createPositionWithWestOrientation(int xAxisPosition, int yAxisPosition){
        return new PositionWithWestOrientation(Position.create(xAxisPosition, yAxisPosition));
    }
}
