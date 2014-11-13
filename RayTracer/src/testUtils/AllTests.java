package testUtils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ppmTest.PPMLibraryTest;
import sceneObjectsTest.SphereTest;
import sceneTest.HitpointDataTest;
import sceneTest.SceneTest;
import sceneTest.VectorTest;
import sceneTest.ViewpointTest;

@RunWith(Suite.class)
@SuiteClasses({ PPMLibraryTest.class, HitpointDataTest.class, SceneTest.class, VectorTest.class, ViewpointTest.class, SphereTest.class })
public class AllTests {

}
