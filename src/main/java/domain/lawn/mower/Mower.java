package domain.lawn.mower;

import domain.lawn.mower.position.and.orientation.PositionAndOrientation;
import lombok.ToString;

@ToString
public class Mower {

    private final MowerId mowerId;
    private PositionAndOrientation positionAndOrientation;
    private NavigationPlan navigationPlan;

    private Mower(MowerId mowerId, PositionAndOrientation positionAndOrientation, NavigationPlan navigationPlan) {
        this.mowerId = mowerId;
        this.positionAndOrientation = positionAndOrientation;
        this.navigationPlan = navigationPlan;
    }

    public static Mower create(MowerId mowerId, PositionAndOrientation positionAndOrientation, NavigationPlan navigationPlan) {
        return new Mower(mowerId, positionAndOrientation, navigationPlan);
    }

    public MowerId getMowerId() {
        return mowerId;
    }

    public PositionAndOrientation getPositionAndOrientation() {
        return positionAndOrientation;
    }

    public Mower followNavigationPlan() {
        navigationPlan.getMowerMovements().forEach(this::move);
        return this;
    }

    private void move(MowerMovement mowerMovement) {
        switch (mowerMovement) {
            case FORWARD:
                positionAndOrientation = positionAndOrientation.moveForward();
                break;
            case LEFT_TURN:
                positionAndOrientation = positionAndOrientation.turnLeft();
                break;
            case RIGHT_TURN:
                positionAndOrientation = positionAndOrientation.turnRight();
                break;
        }
    }

}