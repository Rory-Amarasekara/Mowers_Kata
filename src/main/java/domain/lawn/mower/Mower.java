package domain.lawn.mower;

import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.ToString;

@ToString
public class Mower {

    private final MowerId mowerId;
    private PositionWithOrientation positionWithOrientation;
    private NavigationPlan navigationPlan;

    private Mower(MowerId mowerId, PositionWithOrientation positionWithOrientation, NavigationPlan navigationPlan) {
        this.mowerId = mowerId;
        this.positionWithOrientation = positionWithOrientation;
        this.navigationPlan = navigationPlan;
    }

    public static Mower create(MowerId mowerId, PositionWithOrientation positionWithOrientation, NavigationPlan navigationPlan) {
        return new Mower(mowerId, positionWithOrientation, navigationPlan);
    }

    MowerId getMowerId() {
        return mowerId;
    }

    PositionWithOrientation getPositionWithOrientation() {
        return positionWithOrientation;
    }

    Mower followNavigationPlan(int lawnXAxisSize, int lawnYAxisSize) {
        navigationPlan.getMowerMovements().forEach(mowerMovement -> move(mowerMovement, lawnXAxisSize, lawnYAxisSize));
        return this;
    }

    private void move(MowerMovement mowerMovement, int lawnXAxisSize, int lawnYAxisSize) {
        switch (mowerMovement) {
            case FORWARD:
                positionWithOrientation = positionWithOrientation.moveForward(lawnXAxisSize, lawnYAxisSize);
                break;
            case LEFT_TURN:
                positionWithOrientation = positionWithOrientation.turnLeft();
                break;
            case RIGHT_TURN:
                positionWithOrientation = positionWithOrientation.turnRight();
                break;
        }
    }

}