package com.szilsan.kata.hardsudokusolver;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({com.szilsan.kata.hardsudokusolver.TestMatrixValidation.class, TestCell.class, TestCellPlayGround.class})
public class SudokeTestSuite {
}
