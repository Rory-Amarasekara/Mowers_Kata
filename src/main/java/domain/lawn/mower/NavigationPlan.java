package domain.lawn.mower;

import java.util.Arrays;
import java.util.List;

public class NavigationPlan {

    private final List<MowerMovement> mowerMovements;

    private NavigationPlan(MowerMovement... mowerMovements) {
        this.mowerMovements = Arrays.asList(mowerMovements);
    }

    public static NavigationPlan create(MowerMovement... mowerMovements) {
        return new NavigationPlan(mowerMovements);
    }

    public List<MowerMovement> getMowerMovements() {
        return mowerMovements;
    }
}
