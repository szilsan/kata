package com.szilsan.kata.hardsudokusolver;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.szilsan.kata.hardsudokusolver"})
@ExcludeTags("ignored")
@ExcludeClassNamePatterns({"^.*Solving$"})
public class SudokeTestSuite {
}
