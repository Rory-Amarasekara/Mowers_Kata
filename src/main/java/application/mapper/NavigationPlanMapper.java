package application.mapper;

import domain.lawn.mower.MowerMovement;
import domain.lawn.mower.NavigationPlan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NavigationPlanMapper {

    static NavigationPlan toDomain(String navigationPlan) {
        MowerMovement[] mowerMovements = Arrays.asList(navigationPlan.split(""))
                .parallelStream()
                .map(MowerMovementMapper::toDomain)
                .toArray(MowerMovement[]::new);
        return NavigationPlan.create(mowerMovements);
    }
}
