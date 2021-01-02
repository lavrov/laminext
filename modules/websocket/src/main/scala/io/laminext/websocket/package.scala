package io.laminext

import org.scalajs.dom
import org.scalajs.dom.raw

package object websocket {

  private[websocket] type WebSocketInitialize       = dom.WebSocket => Unit
  private[websocket] type WebSocketSend[Send]       = (dom.WebSocket, Send) => Unit
  private[websocket] type WebSocketReceive[Receive] = raw.MessageEvent => Either[Throwable, Receive]

  def url[Receive, Send](url: String): WebSocketBuilder =
    new WebSocketBuilder(url)

  def path[Receive, Send](path: String): WebSocketBuilder = {
    val wsProtocol = if (dom.document.location.protocol == "https:") "wss" else "ws"
    url(s"$wsProtocol://${dom.document.location.host}$path")
  }

}
