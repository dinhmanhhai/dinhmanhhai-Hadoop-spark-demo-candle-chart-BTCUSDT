import websocket
import datetime

def on_message(ws, message):
    print()
    print(str(datetime.datetime.now()) + ": ")
    f = open("demofile2.txt", "a")
    f.write(message + '\n')
    f.close()


def on_error(ws, error):
    print(error)

def on_close(close_msg):
    print("### closed ###" + close_msg)

def streamKline(currency, interval):
    websocket.enableTrace(False)
    socket = f'wss://stream.binance.com:9443/ws/{currency}@kline_{interval}'
    ws = websocket.WebSocketApp(socket,
                                on_message=on_message,
                                on_error=on_error,
                                on_close=on_close)

    ws.run_forever()

streamKline('btcusdt', '1s')
