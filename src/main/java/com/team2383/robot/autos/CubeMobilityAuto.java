package com.team2383.robot.autos;

import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.team2383.robot.commands.FeederVoltageCommand;
import com.team2383.robot.commands.blizzard.BlizzardCommand;
import com.team2383.robot.commands.blizzard.BlizzardPresets;
import com.team2383.robot.subsystems.drivetrain.DrivetrainSubsystem;
import com.team2383.robot.subsystems.elevator.ElevatorSubsystem;
import com.team2383.robot.subsystems.feeder.FeederSubsystem;
import com.team2383.robot.subsystems.wrist.WristSubsystem;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CubeMobilityAuto extends SequentialCommandGroup {
    public CubeMobilityAuto(DrivetrainSubsystem drivetrain, ElevatorSubsystem elevator, WristSubsystem wrist,
            FeederSubsystem feeder, String pathName, SwerveAutoBuilder autoBuilder) {

        addCommands(
                new InstantCommand(() -> {
                    drivetrain.forceOdometry(new Pose2d(1.89, 1.06, Rotation2d.fromDegrees(180)));
                }),
                new BlizzardCommand(elevator, wrist, BlizzardPresets.MIDDLE).withTimeout(3),
                new FeederVoltageCommand(feeder, () -> -0.5).withTimeout(0.5),
                new BlizzardCommand(elevator, wrist, BlizzardPresets.GROUND_INTAKE).withTimeout(3),
                new FullAutoCommand(drivetrain, "Path1", autoBuilder));
    }
}
