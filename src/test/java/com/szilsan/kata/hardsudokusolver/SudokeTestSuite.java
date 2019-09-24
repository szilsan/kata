package com.szilsan.kata.hardsudokusolver;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({com.szilsan.kata.hardsudokusolver.TestMatrixValidation.class, TestCell.class})
public class SudokeTestSuite {
}
