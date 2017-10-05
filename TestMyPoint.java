/*
Questions :

  Q1.b : Les getters n'ont pas etes testes au prealable. Il faudrait les tester.
  Q2.b : Non car il faut aussi bien couvrir les cas d'echec et que ceux de succes. Donc au moins de tests par methode.
  Q3.a : Il faudrait re-effectuer les calculs dans le test ce qui n'aide pas pour la verification.

 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestMyPoint {
  protected MyPoint myPoint;
  protected MyPoint myPointValue;
  protected MyPoint referencePoint;

  @Before
  public void setUp(){
    myPoint = new MyPoint();
    referencePoint = new MyPoint(2d, 6d);
    myPointValue = new MyPoint(5d,4.4d);
  }

  @After
  public void tearDown(){

  }

  @Test
  public void testConst1() {
    myPoint = new MyPoint();
    assertEquals(0d, myPoint.getX(), 0.0001);
    assertEquals(0d, myPoint.getY(), 0.0001);
  }

  @Test
  public void testConst2() {
    assertEquals(5d, myPointValue.getX(), 0.0001);
    assertEquals(4.4d, myPointValue.getY(), 0.0001);
  }


  @Test
  public void testGetSetX() {
    myPoint.setX(4d);
    assertEquals(4d, myPoint.getX(), 0.0001);
  }
  @Test
  public void testGetSetY() {
    myPoint.setY(4d);
    assertEquals(4d, myPoint.getY(), 0.0001);
  }

  @Test
  public void testSetXNaN() {
    myPoint.setX(Double.NaN);
    assertEquals(0d, myPoint.getX(), 0.0001);
  }
  @Test
  public void testSetYNaN() {
    myPoint.setY(Double.NaN);
    assertEquals(0d, myPoint.getY(), 0.0001);
  }

  @Test
  public void testConst3() {
    myPoint = new MyPoint(referencePoint);
    assertEquals(referencePoint.getX(), myPoint.getX(), 0.0001);
    assertEquals(referencePoint.getY(), myPoint.getY(), 0.0001);
  }
  @Test
  public void testConst3Null() {
    myPoint = new MyPoint(null);
    assertEquals(0d, myPoint.getX(), 0.0001);
    assertEquals(0d, myPoint.getY(), 0.0001);
  }

  @Test
  public void testScale() {
    Double factor = 5d;
    MyPoint scaledPoint = new MyPoint(myPointValue.getX() * factor, myPointValue.getY() * factor);
    assertEquals(scaledPoint.getX(), myPointValue.scale(factor).getX(), 0.0001);
    assertEquals(scaledPoint.getY(), myPointValue.scale(factor).getY(), 0.0001);
  }

  @Test
  public void testHorizontalSymmetry() {
    MyPoint symmetricPoint = myPointValue.horizontalSymmetry(referencePoint);
    Double expectedX = 2d * referencePoint.getX() - myPointValue.getX();
    Double expectedY = myPointValue.getY();
    assertEquals(expectedX, symmetricPoint.getX(), 0.0001);
    assertEquals(expectedY, symmetricPoint.getY(), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalSymmetryNull() {
    myPointValue.horizontalSymmetry(null);
  }

  @Test
  public void testComputeAngleNotNull() {
    boolean isNan = Double.isNaN(myPointValue.computeAngle(referencePoint));
    assertFalse(isNan);
  }

  @Test
  public void testComputeAngleNull() {
    boolean isNan = Double.isNaN(myPointValue.computeAngle(null));
    assertTrue(isNan);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCentralSymmetryNULL ( ) {
    new MyPoint(10 ,20).centralSymmetry(null);
  }

  @Test
  public void testSetPoint() {
    Random rand1 = mock(Random.class);
    when(rand1.nextInt()).thenReturn(37);

    Random rand2 = mock(Random.class);
    when(rand2.nextInt()).thenReturn(28);

    myPoint.setPoint(rand1, rand2);

    assertEquals(37, myPoint.getX(), 0.0001);
    assertEquals(28, myPoint.getY(), 0.0001);
  }

  @Test
  public void testITranslation() {
    ITranslation translator = mock(ITranslation.class);
    when(translator.getTx()).thenReturn(10);
    when(translator.getTy()).thenReturn(13);

    myPoint.translate(translator);

    assertEquals(10, myPoint.getX(), 0.0001);
    assertEquals(13, myPoint.getY(), 0.0001);
  }
}
