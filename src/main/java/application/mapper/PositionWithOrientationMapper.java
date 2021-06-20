package application.mapper;

import domain.lawn.mower.position.and.orientation.PositionWithNorthOrientation;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import domain.lawn.mower.position.and.orientation.PositionWithSouthOrientation;
import domain.lawn.mower.position.and.orientation.PositionWithWestOrientation;
import domain.lawn.mower.position.and.orientation.factory.PositionWithOrientationFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PositionWithOrientationMapper {

    static PositionWithOrientation toDomain(int xAxisPosition, int yAxisPosition, String orientation) {
        switch (orientation) {
            case "N":
                return PositionWithOrientationFactory.createPositionWithNorthOrientation(xAxisPosition, yAxisPosition);
            case "S":
                return PositionWithOrientationFactory.createPositionWithSouthOrientation(xAxisPosition, yAxisPosition);
            case "E":
                return PositionWithOrientationFactory.createPositionWithEastOrientation(xAxisPosition, yAxisPosition);
            case "W":
                return PositionWithOrientationFactory.createPositionWithWestOrientation(xAxisPosition, yAxisPosition);
            default:
                throw new IllegalArgumentException("unknown orientation : " + orientation);
        }
    }

    static String fromDomain(PositionWithOrientation positionWithOrientation) {
        return positionWithOrientation.getXAxisPosition()
                + " "
                + positionWithOrientation.getYAxisPosition()
                + " "
                + convertOrientationToString(positionWithOrientation);
    }

    private static String convertOrientationToString(PositionWithOrientation positionWithOrientation) {
        if (positionWithOrientation instanceof PositionWithNorthOrientation) {
            return "N";
        } else if (positionWithOrientation instanceof PositionWithSouthOrientation) {
            return "S";
        } else if (positionWithOrientation instanceof PositionWithWestOrientation) {
            return "W";
        } else {
            return "E";
        }
    }
}
