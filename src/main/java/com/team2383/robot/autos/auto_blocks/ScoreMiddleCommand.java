package com.team2383.robot.autos.auto_blocks;

import com.team2383.robot.commands.FeederVoltageCommand;
import com.team2383.robot.commands.blizzard.BlizzardCommand;
import com.team2383.robot.commands.blizzard.BlizzardPresets;
import com.team2383.robot.subsystems.elevator.ElevatorSubsystem;
import com.team2383.robot.subsystems.feeder.FeederSubsystem;
import com.team2383.robot.subsystems.wrist.WristSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreMiddleCommand extends SequentialCommandGroup {
    public ScoreMiddleCommand(ElevatorSubsystem elevator, WristSubsystem wrist, FeederSubsystem feeder,
            Boolean isCube) {
        addCommands(
                new BlizzardCommand(elevator, wrist, BlizzardPresets.MIDDLE).withTimeout(2),
                new FeederVoltageCommand(feeder, () -> 0.75, isCube).withTimeout(0.5),
                new BlizzardCommand(elevator, wrist, BlizzardPresets.CONE_CHUTE).withTimeout(2),
                new FeederVoltageCommand(feeder, () -> 0, isCube).withTimeout(0.5));
    }

}
