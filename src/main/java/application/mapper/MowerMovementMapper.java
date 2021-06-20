package application.mapper;

import domain.lawn.mower.MowerMovement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MowerMovementMapper {

    static MowerMovement toDomain(String mowerMovement) {
        switch (mowerMovement) {
            case "F":
                return MowerMovement.FORWARD;
            case "L":
                return MowerMovement.LEFT_TURN;
            case "R":
                return MowerMovement.RIGHT_TURN;
            default:
                throw new IllegalArgumentException("Mower movement " + mowerMovement + "is not recognized");
        }
    }
}
