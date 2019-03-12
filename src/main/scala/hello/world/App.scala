package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}
import org.scalajs.dom.raw.Event

@react class App extends Component {
  type Props = Unit

  case class State(
    optionString: Option[Int],
    iteration: Int,
    message: String
  )

  override def initialState = State(
    optionString = Some(123),
    iteration    = 0,
    message      = ""
  )

  def handleClick(e: Event): Unit = {
    val newOptionVal = if (state.iteration % 3 == 0) None else state.optionString.map(_ + 1)
    val message      = s"1. [ITERATION ${state.iteration}] Updating optionString value from: ${state.optionString} ===> ${newOptionVal}"
    println(message)

    setState(_.copy(
      optionString = newOptionVal,
      iteration    = state.iteration + 1,
      message      = message
    ), () => {
      println(s"2. [ITERATION ${state.optionString}] optionString value is ${state.optionString} in setState callback")
    })

    println(s"3. [ITERATION ${state.optionString}] optionString value is ${state.optionString} after setState updates")
  }

  def render() = {
    div(className := "App")(
      button(
        onClick := {
          s => handleClick(s)
        }
      )("UPDATE STATE"),
    
      h3(s"Last Message"),
      p(s"    => ${state.message}"),
      br(),
      br(),
      h3(s"CurrentState:"),
      p(s"   => On iteration ${state.iteration}: the state is ${state.optionString}")
    )
  }
}
