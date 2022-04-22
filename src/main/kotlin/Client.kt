import kotlinx.browser.document
import react.create
import react.dom.render

val APP_ID = "APP_ID"
fun main() {
    val container = document.createElement("div")
    container.setAttribute("id", APP_ID)

    document.body!!.appendChild(container)

    val welcome = App.create {

    }
    render(welcome, container)
    val warning = document.getElementById("webpack-dev-server-client-overlay-div")
    warning!!.setAttribute("style", "visibility: hidden;")
}