package domain.lawn.mower;

import domain.lawn.mower.position.and.orientation.Position;
import domain.lawn.mower.position.and.orientation.PositionWithOrientation;
import lombok.ToString;

import java.util.Optional;

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

    Position getPosition() {
        return positionWithOrientation.getPosition();
    }

    private void move(MowerMovement mowerMovement) {
        switch (mowerMovement) {
            case FORWARD:
                positionWithOrientation = positionWithOrientation.moveForward();
                break;
            case LEFT_TURN:
                positionWithOrientation = positionWithOrientation.turnLeft();
                break;
            case RIGHT_TURN:
                positionWithOrientation = positionWithOrientation.turnRight();
                break;
        }
    }

    int getRemainingNumberOfMovements() {
        return navigationPlan.getMowerMovements().size();
    }

    Optional<Position> getNextRequestedPosition() {
        return navigationPlan.getNextMovement()
                .map(mowerMovement -> {
                    if (MowerMovement.FORWARD.equals(mowerMovement)) {
                        return positionWithOrientation.moveForward().getPosition();
                    } else {
                        return null;
                    }
                });
    }

    boolean requiresNewPosition() {
        return getNextRequestedPosition().isPresent();
    }

    public void applyNextMowerMovement() {
        navigationPlan.getNextMovement().ifPresent(this::move);
        navigationPlan.removeOldestMovement();
    }

    public void cancelNextMovement(){
        navigationPlan.removeOldestMovement();
    }
}