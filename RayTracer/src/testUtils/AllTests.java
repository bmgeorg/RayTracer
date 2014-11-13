package testUtils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sceneTest.HitpointDataTest;
import sceneTest.SceneTest;
import sceneTest.VectorTest;
import sceneTest.ViewpointTest;

@RunWith(Suite.class)
@SuiteClasses({ HitpointDataTest.class, SceneTest.class, VectorTest.class, ViewpointTest.class })
public class AllTests {

}
