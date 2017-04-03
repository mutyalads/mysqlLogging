package com.rt.workautomation.tests

/**
  * Created by mutyalart on 4/3/17.
  */




import org.scalatest.FunSuite

class StandardJunitTest extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}



import org.scalatest.FlatSpec


class ForUnitTestingUsingFlatSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}


import org.scalatest._

class FetureSpecForAcceptanceTestingBDD extends FeatureSpec with GivenWhenThen {

  info("As a TV set owner")
  info("I want to be able to turn the TV on and off")
  info("So I can watch TV when I want")
  info("And save energy when I'm not watching TV")

  feature("TV power button") {
    scenario("User presses power button when TV is off") {

      Given("a TV set that is switched off")
      val tv = new TVSet
      assert(!tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch on")
      assert(tv.isOn)
    }

    scenario("User presses power button when TV is on") {

      Given("a TV set that is switched on")
      val tv = new TVSet
      tv.pressPowerButton()
      assert(tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch off")
      assert(!tv.isOn)
    }
  }
  class TVSet {
    private var on: Boolean = false
    def isOn: Boolean = on
    def pressPowerButton() {
      on = !on
    }
  }



  import org.scalatest.junit.AssertionsForJUnit
  import scala.collection.mutable.ListBuffer
  import org.junit.Assert._
  import org.junit.Test
  import org.junit.Before

  class JunitExample extends AssertionsForJUnit {

    var sb: StringBuilder = _
    var lb: ListBuffer[String] = _

    @Before def initialize() {
      sb = new StringBuilder("ScalaTest is ")
      lb = new ListBuffer[String]
    }

    @Test def verifyEasy() { // Uses JUnit-style assertions
      sb.append("easy!")
      assertEquals("ScalaTest is easy!", sb.toString)
      assertTrue(lb.isEmpty)
      lb += "sweet"
      try {
        "verbose".charAt(-1)
        fail()
      }
      catch {
        case e: StringIndexOutOfBoundsException => // Expected
      }
    }

    @Test def verifyFun() { // Uses ScalaTest assertions
      sb.append("fun!")
      assert(sb.toString === "ScalaTest is fun!")
      assert(lb.isEmpty)
      lb += "sweeter"
      intercept[StringIndexOutOfBoundsException] {
        "concise".charAt(-1)
      }
    }
  }




}




